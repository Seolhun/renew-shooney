package hi.cord.com.content.main.content.service;

import hi.cord.com.content.main.comment.domain.Comment;
import hi.cord.com.content.main.comment.domain.CommentRepository;
import hi.cord.com.content.main.content.domain.BlogContent;
import hi.cord.com.content.main.content.domain.BlogContentRepository;
import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.file.domain.FileDataRepository;
import hi.cord.com.common.domain.pagination.Pagination;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "shunTransactionManager", noRollbackFor = {NullPointerException.class})
public class BlogContentServiceImpl implements BlogContentService {
    private static final Logger LOG = LoggerFactory.getLogger(BlogContentServiceImpl.class);
    private BlogContentRepository blogContentRepository;
    private FileDataRepository fileDataRepository;
    private CommentRepository commentRepository;

    @Autowired
    public BlogContentServiceImpl(BlogContentRepository blogContentRepository, FileDataRepository fileDataRepository, CommentRepository commentRepository) {
        this.blogContentRepository = blogContentRepository;
        this.fileDataRepository = fileDataRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public BlogContent insert(BlogContent blogContent) {
        return blogContentRepository.save(blogContent);
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
    public Pagination<BlogContent> findAll(BlogContent blogContent, Pageable pageable) {
        // Call Repository        
        Page<BlogContent> contents = blogContentRepository.findAll(pageable);
        Pagination<BlogContent> pagination = new Pagination<>();
        for (BlogContent dbBlogContent : contents) {
            List<FileData> fileList = new ArrayList<>();
            Hibernate.initialize(fileList);
            dbBlogContent.setFiles(fileList);

            List<Comment> commentList = new ArrayList<>();
            Hibernate.initialize(commentList);
            dbBlogContent.setComments(commentList);
        }

        pagination.setList(contents.getContent());
        pagination.setTotalCount(contents.getTotalElements());
        pagination.setPageIndex(pageable.getPageNumber());
        pagination.setPageSize(pageable.getPageSize());
        return pagination;
    }

    @Override
    public BlogContent findById(String id) {
        BlogContent blogContent = blogContentRepository.findById(id);
        List<FileData> fileList = fileDataRepository.findAll();
        List<Comment> commentList = commentRepository.findAll();
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
        BlogContent blogContent = blogContentRepository.findByIdxAndCreatedByNickname(idx, nickname);
        if(blogContent != null){
            List<FileData> fileList = fileDataRepository.findAll();
            List<Comment> commentList = commentRepository.findAll();
            if (fileList != null) {
                blogContent.setFiles(fileList);
            }
            if (commentList != null) {
                blogContent.setComments(commentList);
            }
        }
        return blogContent;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findById(id);
        if (dbBlogContent == null) {
            return false;
        }

        blogContentRepository.delete(dbBlogContent.getId());
        return true;
    }

    @Override
    public boolean deleteById(long idx, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findByIdxAndCreatedByNickname(idx, accessBy);
        if (dbBlogContent == null) {
            return false;
        }

        blogContentRepository.delete(dbBlogContent.getId());
        return true;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public BlogContent updateById(BlogContent blogContent, String accessBy) {
        BlogContent dbBlogContent = blogContentRepository.findById(blogContent.getId());
        if (dbBlogContent != null) {
            dbBlogContent.setTitle(blogContent.getTitle());
            dbBlogContent.setContent(blogContent.getContent());
        }
        return dbBlogContent;
    }

    @Override
    public long count(BlogContent blogContent) {
        return blogContentRepository.countBy(blogContent);
    }

    @Override
    public long getIdxByNickname(String nickname) {
        BlogContent blogContent = blogContentRepository.findFirstByCreatedByNicknameOrderByIdxDesc(nickname);
        if (blogContent == null) {
            return 1;
        }

        return blogContent.getIdx() + 1;
    }
}
