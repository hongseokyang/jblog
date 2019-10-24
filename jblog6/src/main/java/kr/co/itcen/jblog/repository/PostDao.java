package kr.co.itcen.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertPost(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}

	public void setCategoryDefault(Map<String, Object> map) {
		sqlSession.update("post.updateCategoryDefault", map);
	}

	public List<PostVo> getList(Map<String, Object> map) {
		return sqlSession.selectList("post.selectList", map);
	}

	public PostVo getPost(Map<String, Object> map) {
		return sqlSession.selectOne("post.selectPost", map);
	}

}
