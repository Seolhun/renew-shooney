package hi.cord.com.jpa2.content.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.comment.domain.CommentRepository;
import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.domain.ContentRepository;
import hi.cord.com.jpa2.file.domain.FileData;
import hi.cord.com.jpa2.file.domain.FileDataRepository;
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
public class ContentServiceImpl implements ContentService {
    private static final Logger LOG = LoggerFactory.getLogger(ContentServiceImpl.class);
    private ContentRepository contentRepository;
    private FileDataRepository fileDataRepository;
    private CommentRepository commentRepository;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository, FileDataRepository fileDataRepository, CommentRepository commentRepository) {
        this.contentRepository = contentRepository;
        this.fileDataRepository = fileDataRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Content insert(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public List<Content> findByList() {
        return contentRepository.findAll();
    }

    @Override
    public Page<Content> findByPage(Content content, Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    @Override
    public Pagination<Content> findAll(Content content, Pageable pageable) {
        // Call Repository        
        Page<Content> contents = contentRepository.findAll(pageable);
        
        
        Pagination<Content> pagination = new Pagination<>();
        for (Content dbContent : contents) {
            List<FileData> fileList = new ArrayList<>();
            Hibernate.initialize(fileList);
            dbContent.setFiles(fileList);

            List<Comment> commentList = new ArrayList<>();
            Hibernate.initialize(commentList);
            dbContent.setComments(commentList);
        }

        pagination.setList(contents.getContent());
        pagination.setTotalCount(contents.getTotalElements());
        pagination.setPageIndex(pageable.getPageNumber());
        pagination.setPageSize(pageable.getPageSize());
        return pagination;
    }

    @Override
    public Content findById(String id) {
        Content content = contentRepository.findById(id);
        List<FileData> fileList = fileDataRepository.findAll();
        List<Comment> commentList = commentRepository.findAll();
        if (fileList != null) {
            content.setFiles(fileList);
        }
        if (commentList != null) {
            content.setComments(commentList);
        }
        return content;
    }

    @Override
    public Content findByIdx(long idx) {
        return null;
    }

    @Override
    public Content findById(long id) {
        return null;
    }

    @Override
    public Content findByIdx(long idx, String nickname) {
        Content content = contentRepository.findByIdxAndCreatedByEntityNickname(idx, nickname);
        List<FileData> fileList = fileDataRepository.findAll();
        List<Comment> commentList = commentRepository.findAll();
        if (fileList != null) {
            content.setFiles(fileList);
        }
        if (commentList != null) {
            content.setComments(commentList);
        }
        return content;
    }

    @Override
    public boolean deleteById(String id, String accessBy) {
        Content dbContent = contentRepository.findById(id);
        if (dbContent == null) {
            return false;
        }

        contentRepository.delete(dbContent.getId());
        return true;
    }

    @Override
    public boolean deleteById(long idx, String accessBy) {
        Content dbContent = contentRepository.findByIdxAndCreatedByEntityNickname(idx, accessBy);
        if (dbContent == null) {
            return false;
        }

        contentRepository.delete(dbContent.getId());
        return true;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        return false;
    }

    @Override
    public Content updateById(Content content, String accessBy) {
        Content dbContent = contentRepository.findById(content.getId());
        if (dbContent != null) {
            dbContent.setTitle(content.getTitle());
            dbContent.setContent(content.getContent());
        }
        return dbContent;
    }

    @Override
    public long count(Content content) {
        return contentRepository.countBy(content);
    }

    @Override
    public long getIdxByNickname(String nickname) {
        Content content = contentRepository.findFirstByCreatedByEntityNicknameOrderByIdxDesc(nickname);
        if (content == null) {
            return 1;
        }

        return content.getIdx() + 1;
    }
}
