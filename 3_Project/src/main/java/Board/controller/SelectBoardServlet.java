package Board.controller;

import java.io.IOException;
import java.util.ArrayList;

import Board.model.service.BoardService;
import Board.model.vo.Board;
import Board.model.vo.Reply;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectBoardServlet
 */ //게시글 상세조회 서블릿
@WebServlet("/selectBoard.bo")
public class SelectBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 상세보기 + 조회수 올리기 (같은 비즈니스 단 = 같은 model을 가진다는 뜻)
		int bId = Integer.parseInt(request.getParameter("bId"));
		Employee loginUser = (Employee)request.getSession().getAttribute("loginUser");
		
		Board b = new BoardService().selectBoard(bId, loginUser);
		ArrayList<Reply> list = new BoardService().selectReplyList(bId);
		if(b != null) {
			request.setAttribute("b",b);
			request.setAttribute("list", list);
			request.getRequestDispatcher("WEB-INF/views/board/boardDetail.jsp").forward(request, response);
			
		} else {
			request.setAttribute("msg", "해당 게시글은 존재하지 않습니다.");
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
