package kr.co.itcen.jblog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.util.Fileupload;
import kr.co.itcen.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private Fileupload fileUpload;
	
	public void add(String userId) {
		BlogVo blogVo = new BlogVo();
		
		blogVo.defaultSetting(userId);
		blogDao.insertBasic(blogVo);
	}
	
	public void restore(BlogVo blogVo, MultipartFile multipartFile) {
		blogDao.updateBasic(fileUpload.restore(blogVo, multipartFile));
	}
	
	public BlogVo getBasic(String userId) {
		return blogDao.selectBasic(userId);
	}

	public Object getAll(String userId, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("categoryNo", categoryNo);
		paramMap.put("postNo", postNo);

		map.put("categoryList", categoryDao.getList(userId));
		map.put("post", postDao.getPost(paramMap));
		map.put("postList", postDao.getList(paramMap));

		return map;
	}

}

