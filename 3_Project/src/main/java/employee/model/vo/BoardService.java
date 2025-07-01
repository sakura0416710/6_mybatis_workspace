package employee.model.vo;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import common.model.vo.PageInfo;
import employee.model.dao.BoardDAO;

public class BoardService {
	
	private BoardDAO bDAO = new BoardDAO();

	public int getListCount() {
		SqlSession session () = getSqlSession();
		int listCount = bDAO.getListCount(session);
		session.close();
		return listCount;
	}
	
	public ArrayList<Board> selectBoardList(PageInfo pi){
		SqlSession session = getSqlSession();
		ArrayList<Board> list = bDAO.selectBoardList(session, pi);
		session.close();
		return list;
	}

	public int insertBoard(Board b) {
		SqlSession session = getSqlSession();
		int result = bDAO.insertBoard(session, b);
		if(reuslt > 0) {
			session.commit();
		} else {
			session.rollback();
		} 
		session.close();
		return result;
	}
	
}
