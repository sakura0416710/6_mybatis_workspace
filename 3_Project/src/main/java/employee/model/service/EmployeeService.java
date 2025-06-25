package employee.model.service;

import static common.Template.getSqlSession;


import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import employee.model.dao.EmployeeDAO;
import employee.model.vo.Employee;


public class EmployeeService {

	//DAO랑 연결
	private EmployeeDAO eDAO = new EmployeeDAO();
	
	//Connection 객체 받아오기
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
		return list;
	}
	
	//사원등록하기
	public int insertEmployee(Employee e) {
		SqlSession session = getSqlSession();
		int result = eDAO.insertEmployee(session,e);
		if(result > 0) {
			commit(session);
		} else {
			rollback(session);
		}
		return result;
	}
	//사원 정보 수정
	public int updateEmployee(Employee e) {
		SqlSession session = getSqlSession();
		int result = eDAO.updateEmployee(session, e);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int checkEmpNo(int empNo) {
		SqlSession session = getSqlSession();
		int result = eDAO.checkEmpNo(session,empNo);
		
		return result;
	}
	//버튼 눌렀을 때 isAdmin / status 상태 Y,N으로 바꾸기 기능
	public int updateState(int id, String column, String value) {
		SqlSession session = getSqlSession();
		int result = eDAO.updateState(session, id, column, value );
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
