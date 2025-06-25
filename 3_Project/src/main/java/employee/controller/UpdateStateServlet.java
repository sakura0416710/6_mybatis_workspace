package employee.controller;

import java.io.IOException;
import java.util.HashMap;

import employee.model.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateStateServlet
 */
@WebServlet("/updateState.me")
public class UpdateStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("empNo"));
		String column = request.getParameter("column");
		String value = request.getParameter("value");
		
		//보내야하는 데이터가 2개 이상일 때는 메소드 인자가 부족하기 때문에
		//데이터들을 x에 담아서 보내야 함
		//x :1)vo 클래스 2)Collection 
		//HashMap<key, value>
		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("column", column);
		map.put("value", value);
		
		EmployeeService eService = new EmployeeService();
		int result = eService.updateState(map);
		response.getWriter().println(result == 1 ? "success" : "fail");//success + 줄바꿈이 반환되는 것!
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
