package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
	}

	public UserVo getUser(String id) {
		return sqlSession.selectOne("user.getById", id);
	}

	public UserVo getUser(UserVo vo) {
		return sqlSession.selectOne("user.getByVo", vo);
	}
}
