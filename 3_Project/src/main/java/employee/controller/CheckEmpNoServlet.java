package employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import employee.model.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckEmpNoServlet
 */
@WebServlet("/checkEmpNo.me")
public class CheckEmpNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmpNoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empNo = Integer.parseInt(request.getParameter("value"));
		
		EmployeeService eService = new EmployeeService();
		int result = eService.checkEmpNo(empNo); 
		
		//중복확인하는 곳. admin/me로 결과값을 보내주어야 사용자가 화면에서 정보를 받아볼 수 있음
		//값을 보낼 때 ajax비동기방식으로 보냈으므로 받을 때도 비동기로 받아야 함
		//재요청이 아니라 응답중.
		PrintWriter out = response.getWriter();
		out.println(result); //success + 줄바꿈이 반환되는 것!
	
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
