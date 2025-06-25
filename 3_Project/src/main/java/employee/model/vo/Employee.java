package employee.model.vo;

import java.sql.Date;

public class Employee {
	private int empNo;
	private String pwd;
	private String name;
	private String job;
	private Integer mgrNo;
	private String mgr;
	private Date hireDate;
	private int sal;
	private int comm;
	private int deptNo;
	private String dept;
	private String isAdmin;
	private String status;
	
	public Employee() {}
	

	public Employee(int empNo, String pwd, String name, String job, Integer mgrNo, String mgr, Date hireDate,
			int sal, int comm, int deptNo, String dept, String isAdmin, String status) {
		//테이블 컬럼이랑 꼭 똑같지 않고 추가할수도있음 ->join해서 원하는 정보를 뽑기 위해 ->그걸담기위한 vo객체가 필요 
		//그거까지 생각해서 테이블이랑 다르게 구성함 (여기는 mgr이랑 dept가 추가됨.)
		
		this.empNo = empNo;
		this.pwd = pwd;
		this.name = name;
		this.job = job;
		this.mgrNo = mgrNo;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
		this.dept = dept;
		this.isAdmin = isAdmin;
		this.status = status;
	}


	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgrNo() {
		return mgrNo;
	}

	public void setMgrNo(Integer mgrNo) {
		this.mgrNo = mgrNo;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", pwd=" + pwd + ", name=" + name + ", job=" + job + ", mgrNo="
				+ mgrNo + ", mgr=" + mgr + ", hireDate=" + hireDate + ", sal=" + sal + ", comm=" + comm + ", deptNo="
				+ deptNo + ", dept=" + dept + ", isAdmin=" + isAdmin + ", status=" + status + "]";
	}
	
	
	
}
