package employee.controller;

import java.io.IOException;
import java.util.ArrayList;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminviewServlet
 */
@WebServlet("/admin.me") //보낼 데이터없고 그냥 관리자화면으로이동하기
public class AdminviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
    public AdminviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee loginUser = (Employee)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getIsAdmin().equals("Y")) {
			//<사원정보 조회 클릭했을 때 정보가 떴으면 좋겠다>
			ArrayList<Employee> list = new EmployeeService().selectAll();
			request.setAttribute("empList", list); //<사원정보추출:1. request에 set을 설정한다.>
			
			request.getRequestDispatcher("WEB-INF/views/employee/admin.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request,response);
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
