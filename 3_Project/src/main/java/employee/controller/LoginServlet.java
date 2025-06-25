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
 * Servlet implementation class LoginServlet
 */
//login.jsp(form action = "${contextPath}/login.user")에서 제출 시 기능처리를 하는 로그인서블릿으로 이동하게 됨.
@WebServlet("/login.user")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post방식으로 넘어올 경우 인코딩 해주는 것이 좋음
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id")); //name="id"이렇게 파라미터로 받기
		String pwd = request.getParameter("pwd");
		
		Employee e = new Employee();
		e.setEmpNo(id);
		e.setPwd(pwd);
		
		
		//login의 반환타입은 객체이다. count(존재 시 1반환), boolean(t,f)로는 사용자의정보(이름 등)를 알 수 없기 때문이다.
		//SQL에서 select * from 테이블명  ~~ 맞는 것임.
		EmployeeService eService = new EmployeeService();
		Employee login = eService.login(e); 
		
		/*					<view로 이동해주는 역할을 하는 메소드>
		 				 forward  		vs   		sendRedirect
		  		request, response유지				request,response가 새로생성됨
		  ->   담은 데이터가 유지된다               담은 데이터가 유지되지 않는다.(사라짐)
		  	   전달할 데이터가 있으면 forward		  그냥 화면 이동만 하면 된다면 sendRedirect
		  	   url이 유지된다.
		  	   (요청했던 url이 유지됨)			  내가 지정한 url로 바뀌게 된다. 
		  
		  
		 */
		
		if(login != null) {
			//로그인 정보가 있을 때(session)
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", login);
			session.setMaxInactiveInterval(600); //session이 유지되는 시간-단위(초)(600 = 10분)
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			//forward는 로그인 성공 후에도 url이 login.user로 남아있으니까(원래는 메인으로 가야함) url이 안남는 sendRedirect로시도.
			response.sendRedirect(request.getContextPath()); //<-이건 index.jsp가 안보이게댐
			//response.sendRedirect("index.jsp"); 이러면 내가 지정한 index.jsp로 url이 바뀜.
			//근데 데이터도 유지가 되고 있음.왜?session에 로그인 데이터가 있으므로 !
			//request에 있는게 아니잔슴ㅇㅇ
		}else {
			//로그인 정보가 없을 때 (메세지를 전달해야하니까 forward, url이 유지됨)
			request.setAttribute("msg", "로그인에 실패했습니다.");
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
