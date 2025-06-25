package employee.controller;

import java.io.IOException;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateEmployeeServlet
 */
@WebServlet("/updateEmp.me")
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String job = request.getParameter("job");
		int sal = Integer.parseInt(request.getParameter("sal"));
		//INTEGER해준 애들은 사실 다 삼항엿나자로 INT처리 해줘야대긴 함
		 //사용자가 빈칸을 보내면 null이 아니라 빈칸이 나옴. 이럴 경우엔 문자열->숫자(파싱불가) 삼항연산자 이용해서 경우 나눠주기
		int comm = request.getParameter("comm").equals("")? 0 : Integer.parseInt(request.getParameter("comm"));
		int deptNo = Integer.parseInt(request.getParameter("dept"));
		
		/*
		 	1.비밀번호를 수정하는 경우
		 *	update emp set pwd =?, name=?, job=?,sal=?,comm=?, deptno=? 
		 *			where empno=?
		 *
		 *	2.비밀번호를 수정하지 않는 경우
		 *	update emp 
		 *	set name=? job=?, sal=?, comm=?, deptno=? 
		 *	where empno=?
		 */
		//pwd값이 비어있는 상태 > 비번 변경 X > session에 있는 비번번호를 가져와라 >1번 경우에 해당
		HttpSession session = request.getSession();
		if(pwd.equals("")) {
			pwd = ((Employee)session.getAttribute("loginUser")).getPwd();
		}
		
		Employee e = new Employee(id,pwd,name,job,null,null,null, sal, comm, deptNo,null,null,null);
		EmployeeService eService = new EmployeeService();
		int result = eService.updateEmployee(e); //insert는 넣은 행의 개수로 들어가므로 반환값이 int임
		if(result > 0) { //수정 성공 시 내정보수정 페이지로 이동
			Employee updateEmp = eService.login(e); //로그인했을 때의 정보를 한 번 더 가져와줌(아님 DB업데이트랑 컨트롤러업댓은 별개이므로 로그아웃되버림
			session.setAttribute("loginUser", updateEmp);
			session.setMaxInactiveInterval(600);
			//response.sendRedirect(request.getContextPath() + "/editPage.me");
			response.sendRedirect(request.getContextPath());
			
		} else {
			request.setAttribute("msg", "수정을 실패하였습니다.");
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
