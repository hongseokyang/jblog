package kr.co.itcen.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(CategoryVo categoryVo) {
		return sqlSession.insert("category.insert", categoryVo)==1;
	}
	
	public List<CategoryVo> getList(String userId) {
		return sqlSession.selectList("category.selectList", userId);
	}
	
	public List<CategoryVo> getListContainCount(Map<String, Object> map) {
		return sqlSession.selectList("category.selectListContainCount", map);
	}

	public int getListCnt(String userId) {
		return sqlSession.selectOne("category.selectListCnt", userId);
	}

	public Boolean delete(Long categoryNo) {
		return sqlSession.delete("category.delete", categoryNo)==1;
	}
	
}
