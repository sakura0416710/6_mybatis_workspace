package Board.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import Board.model.service.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteBoardServlet
 */
@WebServlet("/deleteBoard.bo")
public class DeleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//bId받아오기
		String encode = request.getParameter("bId");
		//디코딩
		Decoder decoder = Base64.getDecoder();
		byte[] byteArr = decoder.decode(encode);
		//문자열 객체생성(디코딩 하면 읽을 수 없으니까 문자열로 받아오기)
		String strId = new String(byteArr);
		int bId = Integer.parseInt(strId);
		
		//삭제처리
		int result = new BoardService().deleteBoard(bId);
		if(result > 0) {
			response.sendRedirect("list.bo");
		} else {
			request.setAttribute("msg", "게시글 삭제 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage/jsp").forward(request, response);
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
