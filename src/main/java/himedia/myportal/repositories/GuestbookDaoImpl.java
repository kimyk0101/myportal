package himedia.myportal.repositories;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import himedia.myportal.exceptions.GuestbookDaoException;
import himedia.myportal.repositories.vo.GuestbookVo;

@Repository
public class GuestbookDaoImpl implements GuestbookDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<GuestbookVo> selectAll() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.selectAll");
		return list;
	}

	@Override
	public int insert(GuestbookVo vo) {
		int insertedCount = 0;
		try {
			insertedCount = sqlSession.insert("guestbook.insert", vo);
			//	PersistenceException OR SQLException
		} catch (Exception e) {
			//	예외 전환
			throw new GuestbookDaoException("방명록 기록 중 에러 발생", vo);
		}
		return insertedCount;
	}

	@Override
	public int delete(GuestbookVo vo) {
		int deletedCount = 0;
		try {
			deletedCount = sqlSession.delete("guestbook.delete", vo);
		} catch (Exception e) {
			//	예외 전환
			throw new GuestbookDaoException("방명록 기록 중 에러 발생", vo);
		}
		return deletedCount;
	}
	
}
