package employee.controller;

import java.io.IOException;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertEmployeeServlet
 */
@WebServlet("/insertEmp.me")
public class InsertEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmployeeServlet() {
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//name parameter로 view에서 보낸 데이터를 받아오기
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		Integer mgr = request.getParameter("mgr").indexOf("-") == 0 
				? null : Integer.parseInt(request.getParameter("mgr"));
		
				//mgr은 필수가 아님. 이 0은 선택을 안햇을 경우 받는 값 (선택하면 있다는 의미로 1 받음)
				//선택안했을 때의 값을 null로 저장하는 이유 : 사번이 0일수도있으니까, DB에 이미 매니저없는 사람은 mgr이 null로 저장돼있으니까 맞춰주려고.
				//int는 null을 가질 수 없음(기본자료향 ->클래스화하기 : 래퍼 클래스화 중 하나인 Integer를 써둔것)
		int sal = Integer.parseInt(request.getParameter("sal"));
		int comm = Integer.parseInt(request.getParameter("comm"));
		int deptNo = Integer.parseInt(request.getParameter("dept"));
		//isAdmin값이 안 넘어오면 아니라는 거니까 N넣고 맞으면 Y넣기
		String isAdmin = request.getParameter("isAdmin")== null? "N" : "Y";
		System.out.println(isAdmin);
		
		Employee e = new Employee(id,null,name,job,mgr, null, null, sal, comm, deptNo, null,isAdmin, null);
		int result = new EmployeeService().insertEmployee(e); //insert는 넣은 행의 개수로 들어가므로 반환값이 int임
		if(result > 0) { //enroll해서 데이터를 추가하면 afterEnroll=Y 쿼리스트링이 추가된 url로 이동하기(데이터 전송 및 구분의 역할도 하는 것)
			response.sendRedirect(request.getContextPath() + "/admin.me?afterEnroll=Y");
			
		} else {
			request.setAttribute("msg", "사원 등록을 실패하였습니다.");
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
