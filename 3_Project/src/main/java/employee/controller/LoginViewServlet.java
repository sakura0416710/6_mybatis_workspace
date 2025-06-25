package employee.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginViewServlet
 */

//사용자가 로그인 화면 /loginView.user 으로 접근하는 단계

@WebServlet("/loginView.user") 
public class LoginViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginViewServlet() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 화면으로 이동하기->데이터를 들고갈 필요가 없는 단계.화면이동만 하는단계
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/employee/login.jsp"); //화면이동
		view.forward(request, response); //경로 받아서 이동시키는 메소드
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
