package hi.cord.com.content.main.content.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.CommonService;
import hi.cord.com.content.main.comment.domain.Comment;
import hi.cord.com.content.main.comment.service.CommentService;
import hi.cord.com.content.main.content.domain.BlogContent;
import hi.cord.com.content.main.content.domain.BlogContentRepository;
import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.file.service.FileDataService;
import hi.cord.com.content.main.spam.domain.Spam;
import hi.cord.com.content.main.spam.service.SpamService;
import hi.cord.com.content.main.tag.service.TagService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Blog content service.
 */
@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class BlogContentServiceImpl implements BlogContentService {
    private static final Logger LOG = LoggerFactory.getLogger(BlogContentServiceImpl.class);
    private BlogContentRepository blogContentRepository;
    private FileDataService fileDataService;
    private CommentService commentService;
    private CommonService commonService;
    private TagService tagService;
    private SpamService spamService;

    @Autowired
    public BlogContentServiceImpl(BlogContentRepository blogContentRepository, FileDataService fileDataService, CommentService commentService, CommonService commonService, TagService tagService, SpamService spamService) {
        this.blogContentRepository = blogContentRepository;
        this.fileDataService = fileDataService;
        this.commentService = commentService;
        this.commonService = commonService;
        this.tagService = tagService;
        this.spamService = spamService;
    }

    /**
     * Process
     * 1. Having Spam contents?
     * 2. Having Image or File Image??
     * 3. Having Tags?
     * 4. Save BlogContent
     * 5. Send Content into AWS Lambda to analysis Keywords async.
     */
    @Override
    public BlogContent insert(BlogContent blogContent) {
        String content = blogContent.getContent();

        // 1. Having Spam
        // 2. Having Image or File Image?
        content = this.beforeInsertValidation(content);
        blogContent.setContent(content);

        // 3. Having Tags
        tagService.saveIterable(blogContent.getTags());

        // 4. Save BlogContent
        blogContentRepository.save(blogContent);

        // 5. Send Content into AWS to analysis Keywords async.
        return blogContent;
    }

    @Override
    public List<BlogContent> findByList() {
        return blogContentRepository.findAll();
    }

    @Override
    public Page<BlogContent> findByPage(BlogContent blogContent, Pageable pageable) {
        return blogContentRepository.findAll(pageable);
    }

    @Override
    public Pagination<BlogContent> findAll(Example<BlogContent> blogContentExample, Pageable pageable) {
        // Call Repository        
        Page<BlogContent> contents = blogContentRepository.findAll(blogContentExample, pageable);

        Pagination<BlogContent> pagination = new Pagination<>();
        for (BlogContent dbBlogContent : contents) {
            List<FileData> fileList = new ArrayList<>();
            Hibernate.initialize(fileList);
            dbBlogContent.setFiles(fileList);

            List<Comment> commentList = new ArrayList<>();
            Hibernate.initialize(commentList);
            dbBlogContent.setComments(commentList);
        }

        pagination.setItems(contents.getContent());
        pagination.setTotalCount(contents.getTotalElements());
        pagination.setPageIndex(pageable.getPageNumber());
        pagination.setPageSize(pageable.getPageSize());
        return pagination;
    }

    @Override
    public BlogContent findById(String id) {
        BlogContent blogContent = blogContentRepository.findById(id);
        List<FileData> fileList = fileDataService.findByList();
        List<Comment> commentList = commentService.findByList();
        if (fileList != null) {
            blogContent.setFiles(fileList);
        }
        if (commentList != null) {
            blogContent.setComments(commentList);
        }
        return blogContent;
    }

    @Override
    public BlogContent findByIdx(long idx) {
        return null;
    }

    @Override
    public BlogContent findById(long id) {
        return null;
    }

    @Override
    public BlogContent findByIdx(long idx, String nickname) {
        BlogContent blogContent = blogContentRepository.findByIdxAndBaseCreatedByCreatedByNickname(idx, nickname);
        if (blogContent != null) {
            LOG.info("r : blogContent : {}", blogContent.toString());
            List<FileData> fileList = fileDataService.findByList();
            List<Comment> commentList = commentService.findByList();
            blogContent.setFiles(fileList);
            blogContent.setComments(commentList);
        }
        return blogContent;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findById(id);
        this.deleteBlogContent(dbBlogContent, accessBy);
        // blogContentRepository.delete(dbBlogContent.getId());
        return true;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findByIdxAndBaseCreatedByCreatedByNickname(idx, accessBy);
        this.deleteBlogContent(dbBlogContent, accessBy);
        // blogContentRepository.delete(dbBlogContent.getId());
        return true;
    }

    @Override
    public BlogContent updateById(BlogContent blogContent, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findById(blogContent.getId());
        this.updatedBlogContent(dbBlogContent, blogContent, accessBy);
        return dbBlogContent;
    }

    @Override
    public BlogContent updateByIdx(BlogContent blogContent, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findByIdxAndBaseCreatedByCreatedByNickname(blogContent.getIdx(), accessBy);
        this.updatedBlogContent(dbBlogContent, blogContent, accessBy);
        return dbBlogContent;
    }

    @Override
    public long count(BlogContent blogContent) {
        return blogContentRepository.countBy(blogContent);
    }

    @Override
    public long getIdxByNickname(String nickname) {
        BlogContent blogContent = blogContentRepository.findFirstByBaseCreatedByCreatedByNicknameOrderByIdxDesc(nickname);
        if (blogContent == null) {
            return 1;
        }

        return blogContent.getIdx() + 1;
    }

    private String beforeInsertValidation(String blogContent) {
        // 1. Having Spam
        List<Spam> spamList = spamService.findByList();
        if (spamList != null) {
            for (Spam spam : spamList) {
                blogContent = blogContent.replaceAll(spam.getContent(), "");
            }
        }

        // 2. Having Image or File Image?
        List<String> images = commonService.extractImgSrc(blogContent);
        for (String img : images) {
            // fileDataService.insert()
        }

        return blogContent;
    }

    private void updatedBlogContent(BlogContent dbBlogContent, BlogContent targetContent, String accessBy) {
        if (dbBlogContent != null) {
            dbBlogContent.setTitle(targetContent.getTitle());
            dbBlogContent.setContentType(targetContent.getContentType());
            dbBlogContent.setContent(targetContent.getContent());
            dbBlogContent.setTags(targetContent.getTags());
            dbBlogContent.getBaseModifiedBy().setModifiedByNickname(accessBy);
        }
    }

    private void deleteBlogContent(BlogContent dbBlogContent, String accessBy) {
        if (dbBlogContent != null) {
            dbBlogContent.setActive(false);
            dbBlogContent.getBaseModifiedBy().setModifiedByNickname(accessBy);
        }
    }
}
