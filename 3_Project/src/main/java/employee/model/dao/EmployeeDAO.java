package employee.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import employee.model.vo.Employee;

public class EmployeeDAO {

	public Employee login(SqlSession session, Employee e) {
		//쿼리생각하는거랑 같이 결과값 생각하기 ; selectOne, selectList...
		//첫번째 인자 : 접근할 쿼리명. 쿼리는 mapper.xml에 작성
		//두번째 인자 : ObjectParameter.쿼리에 전달할 데이터 ex.위치홀더있을 때
		
		Employee login = session.selectOne("empMapper.login", e); //mapper.xml의 쿼리를 id로 연결
		return login;
	}
	
	//전체사원조회
	public ArrayList<Employee> selectAll(SqlSession session) {
		//selectList는 Object 반환 > 형변환 (다운캐스팅)
		ArrayList<Employee> list = (ArrayList)session.selectList("empMapper.selectAll");
		return list;
		
	}

	//사원 추가
	public int insertEmployee(SqlSession session, Employee e) {
		int result = session.insert("empMapper.insertEmployee", e);  
		return result;
		
	}

	//사원 내 정보수정
	public int updateEmployee(SqlSession session, Employee e) {
		int result = session.update("empMapper.updateEmployee",e);
		return result;	
	}
	

	//사원번호 중복조회
	public int checkEmpNo(SqlSession session, int empNo) {
		int result = session.selectOne("empMapper.checkEmpNo", empNo);
		
		return result;
	}


	public int updateState(SqlSession session,HashMap<String, Object>map) {
		return session.update("empMapper.updateState",map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
