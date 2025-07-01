package employee.model.dao;

import java.util.ArrayList;

import common.model.vo.PageInfo;
import employee.model.vo.Board;

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
	}
	
	
	public int insertBoard(SqlSession session, Board b) {
		return session.insert("boardMapper.insertBoard",b);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
