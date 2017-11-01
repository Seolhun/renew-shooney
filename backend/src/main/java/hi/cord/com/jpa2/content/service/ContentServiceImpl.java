package hi.cord.com.jpa2.content.service;

import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.domain.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger LOG = LoggerFactory.getLogger(ContentServiceImpl.class);
	private ContentRepository contentRepository;
	
	@Autowired
	public ContentServiceImpl(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}

	@Override
	public Content insert(Content content) {
		LOG.info("param : {}", content.toString());
		return contentRepository.save(content);
	}

    @Override
    public List<Content> findByList() {
        return contentRepository.findAll();
    }

    @Override
    public Page<Content> findByPage(Content content, Pageable pageable) {
        LOG.info("param : "+ content.toString());
        return contentRepository.findAll(pageable);
    }

    @Override
    public Content findById(String id) {
	    return contentRepository.findById(id);
    }

    @Override
    public Content findById(long id) {
	    return null;
    }

    @Override
    public Content findByIdx(long idx) {
        return contentRepository.findByIdx(idx);
    }

    @Override
    public boolean deleteById(String id) {
        Content dbContent = contentRepository.findById(id);
        LOG.info("return : "+ dbContent);
        if (dbContent == null) {
            return false;
        }

        contentRepository.delete(dbContent.getId());
        return true;
    }

    @Override
    public boolean deleteById(long idx) {
        Content dbContent = contentRepository.findByIdx(idx);
        LOG.info("return : "+ dbContent);
        if (dbContent == null) {
            return false;
        }

        contentRepository.delete(dbContent.getId());
        return true;
    }

    @Override
    public boolean deleteByIdx(long idx) {
        return false;
    }

    @Override
    public Content updateById(Content content) {
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
}
