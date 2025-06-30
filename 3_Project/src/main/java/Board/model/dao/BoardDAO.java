package Board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import Board.model.vo.Board;
import Board.model.vo.PageInfo;
import Board.model.vo.Reply;

public class BoardDAO {
	public int getListCount(SqlSession session) {
		return session.selectOne("boardMapper.getListCount");
	}
	
	public ArrayList<Board> selectBoardList(SqlSession session, PageInfo pi){
		/*
		 * RowBound작동방법 : 
		 * 1. 쿼리로 전체 게시물을 가져온다.
		 * 2. 로우바운드에 지정한 오프셋과 리밋으로 전체게시글 중 n개를 건너뛰고 가져올건지를 정함
		 * 
		 * 
		 */
	
		//offset : 건너뛸 게시글 개수 / limit : 선택할 게시글 개수
		int offset = (pi.getCurrentPage() -1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		ArrayList<Board>list = (ArrayList)session.selectList("boardMapper.selectBoardList", null, rowBounds);
	

		return list;
	}
	
	//게시글 작성
	public int insertBoard(SqlSession session, Board b) {
		return session.insert("boardMapper.insertBoard",b);
	}

	//게시글 수정
	public int updateBoard(SqlSession session, Board b) {
		return session.update("boardMapper.updateBoard", b);
	}

	//게시글삭제
	public int deleteBoard(SqlSession session, int bId) {
		return session.update("boardMapper.deleteBoard",bId);
	}

	//게시판 상세보기
	public Board selectBoard(SqlSession session, int bId) {
		return session.selectOne("boardMapper.selectBoard",bId);
	}

	//조회수 증가시키기
	public int updateCount(SqlSession session, int bId) {
		return session.update("boardMapper.updateCount", bId);
	}
	//댓글 상세보기
	public ArrayList<Reply> selectReplyList(SqlSession session, int bId) {
		return (ArrayList)session.selectList("boardMapper.selectReplyList", bId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
