package Board.model.service;

import static common.Template.getSqlSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import Board.model.dao.BoardDAO;
import Board.model.vo.Board;
import Board.model.vo.PageInfo;
import Board.model.vo.Reply;
import employee.model.vo.Employee;

public class BoardService {
	
	private BoardDAO bDAO = new BoardDAO();

	public int getListCount() {
		SqlSession session = getSqlSession();
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
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		} 
		session.close();
		return result;
	}

	public int updateBoard(Board b) {
		SqlSession session = getSqlSession();
		int result = bDAO.updateBoard(session, b);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	//게시판 상세보기 + 조회수 추가(내 글은 일회만 증가하는 걸로)
	public Board selectBoard(int bId, Employee loginUser) {
		SqlSession session = getSqlSession();
		Board b = bDAO.selectBoard(session, bId);
		if(b!=null) {
			if(loginUser != null && b.getEmpNo() != loginUser.getEmpNo()) {
				int result = bDAO.updateCount(session, bId);
				if(result > 0) {
					session.commit();
					b.setCount(b.getCount() + 1);
				} else {
					session.rollback();
				}
			}
		}
		
		session.close();
		return b;
	}

	//게시글 삭제하기
	public int deleteBoard(int bId) {
		SqlSession session = getSqlSession();
		int result = bDAO.deleteBoard(session, bId);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}
	//댓글 상세보기
	public ArrayList<Reply> selectReplyList(int bId) {
		SqlSession session = getSqlSession();
		ArrayList<Reply> list = bDAO.selectReplyList(session, bId);
		return list;
	}
//검색 : 전체 게시글 개수 찾기(현재 페이지를 이용해서)
	public int getSearchListCount(HashMap<String, String> map) {
		SqlSession session = getSqlSession();
		int result = bDAO.getSearchListCount(session, map);
		session.close();
		return result;
	}

	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi) {
		SqlSession session = getSqlSession();
		ArrayList<Board>list = bDAO.selectSearchList(session, map, pi);
		session.close();
		return list;
	}

	public int insertReply(Reply r) {
		SqlSession session = getSqlSession();
		int result = bDAO.insertReply(session, r);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		} session.close();
		return result;
	}

	
}
