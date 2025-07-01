package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import Board.model.service.BoardService;
import Board.model.vo.Reply;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertReplyServlet
 */
@WebServlet("/insertReply.bo")
public class InsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*mybatis로 이용하여 댓글등록하기.
		 * 등록 결과를 view로 보내주고
		 * 등록이 성공하면 textarea에 썻던 내용 지우기 ->새로고침 하면 새 댓글이 보이게 됨
		 * 등록이 실패하면 실패등록했다고 알림창 띄우기
		 */
		//1.content랑 게시글 번호, 작성자 받아오기
		String content = request.getParameter("content");
		int bId = Integer.parseInt(request.getParameter("bId"));
		int empNo = ((Employee)request.getSession().getAttribute("loginUser")).getEmpNo();
		
		//담아서
		Reply r  = new Reply();
		r.setContent(content);
		r.setRefBoard(bId);
		r.setEmpNo(empNo);
		
		//보내주기
		int result = new BoardService().insertReply(r);
				
		//등록결과를 ajax 받아와서  view로 뿌리기
		response.getWriter().print(result);
		
		if(result > 0) {
			response.sendRedirect("");
			request.getRequestDispatcher("WEB-INF/Board/boardDetail.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "댓글 등록 실패");
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
