package kr.co.itcen.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.web.Pagination;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	public void add(String blogId) {
		CategoryVo categoryVo = new CategoryVo();
		
		categoryVo.defaultSetting(blogId);
		categoryDao.insert(categoryVo);
	}

	public Boolean add(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}
	
	public List<CategoryVo> getList(String userId) {
		return categoryDao.getList(userId);
	}
	
	public List<CategoryVo> getListContainCount(String userId, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("pagination", pagination);
		
		return categoryDao.getListContainCount(map);
	}

	public int getListCnt(String userId) {
		return categoryDao.getListCnt(userId);
	}

	public Boolean delete(Long categoryNo, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", categoryNo);
		map.put("userId", userId);
		
		postDao.setCategoryDefault(map);
		return categoryDao.delete(categoryNo);
	}
}
