package employee.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import employee.model.vo.Employee;

public class EmployeeDAO {

	public Employee login(SqlSession session, Employee e) {
		//쿼리생각하는거랑 같이 결과값 생각하기 ; selectOne, selectList...
		//첫번째 인자 : 접근할 쿼리명. 쿼리는 mapper.xml에 작성
		//두번째 인자 : ObjectParameter.쿼리에 전달할 데이터 ex.위치홀더있을 때
		Employee login = session.selectOne("empMapper.login", e); //mapper.xml의 쿼리를 id로 연결
		System.out.println(login);
		return login;
	}
	
	
	public ArrayList<Employee> selectAll(SqlSession session) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		String query="select * from v_selectemp";
		
		try {
			stmt = session.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				list.add(new Employee(rset.getInt("empno"),
						 rset.getString("pwd"),
						 rset.getString("사원이름"),
						 rset.getString("job"),
						 rset.getInt("mgrno"),
						 rset.getString("매니저이름"),
						 rset.getDate("hiredate"),
						 rset.getInt("sal"),
						 rset.getInt("comm"),
						 rset.getInt("deptno"),
						 rset.getString("dname"),
						 rset.getString("is_admin"),
						 rset.getString("status")));
					}
				
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int insertEmployee(SqlSession session, Employee e) {
		PreparedStatement psmt = null;
		int result = 0; 
		String query = "INSERT INTO emp VALUES (?, ?, ?, ?, SYSDATE, ?, ?, ?, DEFAULT, ?, DEFAULT)";

		
		try {
			psmt = session.prepareStatement(query);
			psmt.setInt(1, e.getEmpNo());
			psmt.setString(2, e.getName());
			psmt.setString(3, e.getJob());

			if (e.getMgrNo() == null) {
			    psmt.setNull(4, java.sql.Types.INTEGER);
			} else {
			    psmt.setInt(4, e.getMgrNo());
			}

			psmt.setInt(5, e.getSal());
			psmt.setInt(6, e.getComm());
			psmt.setInt(7, e.getDeptNo());
			psmt.setString(8, e.getIsAdmin());

			
			result = psmt.executeUpdate();
	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}


	public int updateEmployee(SqlSession session, Employee e) {
		
		PreparedStatement psmt = null;
		int result = 0;
		String query = "update emp set pwd =?, ename=?, job=?, sal=?,comm=?, deptno=? where empno=?";
		
		
		try {
			psmt = session.prepareStatement(query);
			
			psmt.setString(1,e.getPwd());
			psmt.setString(2,e.getName());
			psmt.setString(3, e.getJob());
			psmt.setInt(4,e.getSal());
			psmt.setInt(5, e.getComm());
			psmt.setInt(6, e.getDeptNo());
			psmt.setInt(7, e.getEmpNo());
			
			result = psmt.executeUpdate();
			 
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		} finally {
			close(psmt);
		}
		
		
		return result;
	}


	public int checkEmpNo(SqlSession session, int empNo) {
		//불완전 쿼리 : select count(*) from emp where empNo=?
		//PreparedStatement 
		PreparedStatement psmt = null;
		ResultSet rest = null;
		int result = 0;
		String query = "select count(*) from emp where empNo=?";
		
		try {
			psmt = session.prepareStatement(query);
			psmt.setInt(1,empNo);
			rest = psmt.executeQuery();
			
			if(rest.next()) {       //여긴 컬럼명쓰는건데 1로 받을거니까 1이라고 써도 됨
				result = rest.getInt("count(*)");
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}finally {
			close(rest);
			close(psmt);
			
		}
		return result;
	}


	public int updateState(SqlSession session, int id, String column, String value) {
		//쿼리
		    PreparedStatement pstmt = null;
		    int result = 0;

		    //?는 sql에서 '?'로 넘어가니까 'is_Admin'으로 되면 인식X
		    // 그래서 걍 써주면댐
		    String query = "UPDATE emp SET " + column + " = ? WHERE empNo = ?";

		    try {
		        pstmt = session.prepareStatement(query);
		        pstmt.setString(1, value);
		        pstmt.setInt(2, id);

		        result = pstmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(pstmt);
		    }

		    return result;
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
