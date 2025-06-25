package employee.model.service;

import static common.Template.getSqlSession;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import employee.model.dao.EmployeeDAO;
import employee.model.vo.Employee;


public class EmployeeService {

	//DAO랑 연결
	private EmployeeDAO eDAO = new EmployeeDAO();
	
	public Employee login(Employee e){
		SqlSession session = getSqlSession();
		Employee login = eDAO.login(session,e);
		
		session.close();
		
		return login;
		
	}
	
	//사원정보 조회
	public ArrayList<Employee> selectAll () {
		SqlSession session = getSqlSession();
		ArrayList<Employee> list = eDAO.selectAll(session);
		session.close();
		return list;
	}
	
	//사원등록하기
	public int insertEmployee(Employee e) {
		SqlSession session = getSqlSession();
		int result = eDAO.insertEmployee(session,e);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}
	
	
	//내 정보수정
	public int updateEmployee(Employee e) {
		SqlSession session = getSqlSession();
		int result = eDAO.updateEmployee(session, e);
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}
	
	//사번 중복 조회
	public int checkEmpNo(int empNo) {
		SqlSession session = getSqlSession();
		int result = eDAO.checkEmpNo(session,empNo);
		
		return result;
	}
	
	
	//버튼 눌렀을 때 isAdmin / status 상태 Y,N으로 바꾸기 기능
	public int updateState(HashMap<String, Object>map) {
		SqlSession session = getSqlSession();
		int result = eDAO.updateState(session, map );
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
