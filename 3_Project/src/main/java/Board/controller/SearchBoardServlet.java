package Board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Board.model.service.BoardService;
import Board.model.vo.Board;
import Board.model.vo.PageInfo;
import common.Pagination;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBoardServlet
 */
@WebServlet("/search.bo")
public class SearchBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String condition = request.getParameter("condition");
		String value = request.getParameter("value");
		
		//검색한 결과도 페이징 처리해야함
		//현재 페이지 기준(currentPage) 처음, 끝 페이
		int currentPage = 1;
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		//전체 개시글 개수 : listCount
		//보내는 자리는 하난데 우린 두개 보내야 하니까 map이용하기
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("condition", condition);
		map.put("value", value);
		
		//서비스단 전달 (결과값 : 검색해서 나온 전체 페이지 개수를 알아야 하므로)
		BoardService service = new BoardService();
		int listCount = service.getSearchListCount(map);
		
		//페이징 계산 결과 받아오기 
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Board> list = service.selectSearchList(map, pi);
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.setAttribute("condition", condition);
		request.setAttribute("value", value);
		
		request.getRequestDispatcher("WEB-INF/views/Board/boardList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
