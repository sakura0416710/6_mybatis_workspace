package employee.controller;

import java.io.IOException;
import java.util.ArrayList;

import common.Pageination;
import common.model.vo.PageInfo;
import employee.model.vo.Board;
import employee.model.vo.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectBoardListServlet
 */
@WebServlet("/list.bo")
public class SelectBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardListServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			BoardService service = new BoardService();
			
		//page정보가 없을 때, 처음 화면 일 때 첫 페이지가 나오게 하기
			int currentPage=1;
			if(request.getParameter("page")!=null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			
		//전체 게시글의 개수 
			int listCount = service.getListCount();
			PageInfo pi = Pageination.getPageInfo(currentPage, listCount);
			ArrayList<Board> list = service.selectBoardList(pi);
			
			request.setAttribute("list", list);
			request.setAttribute("pi",pi);
			
			request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
