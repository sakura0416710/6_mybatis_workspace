package Board.controller;

import java.io.IOException;

import Board.model.service.BoardService;
import Board.model.vo.Board;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertBoardServlet
 */
@WebServlet("/insertBoard.bo")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String isNotice = request.getParameter("isNotice") == null ? "N" : request.getParameter("isNotice");
		String content = request.getParameter("content");
		int writer = ((Employee)request.getSession().getAttribute("loginUser")).getEmpNo();
		
		Board b = new Board(0,title, content,writer, null, 0, null, null, isNotice, null);
	
		int result = new BoardService().insertBoard(b);
		if(result > 0) {
			response.sendRedirect("list.bo");
		} else {
			request.setAttribute("msg", "게시글 등록 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
