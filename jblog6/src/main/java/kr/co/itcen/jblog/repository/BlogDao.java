package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;

	public void updateBasic(BlogVo blogVo) {
		sqlSession.update("blog.updateBasic", blogVo);
	}

	public void insertBasic(BlogVo blogVo) {
		System.out.println(blogVo);
		sqlSession.insert("blog.insertBasic", blogVo);
	}

	public BlogVo selectBasic(String userId) {
		return sqlSession.selectOne("blog.selectBasic", userId);
	}

}
