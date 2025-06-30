package Board.model.vo;

import java.sql.Date;

public class Reply {
	private int replyNo;
	private String content;
	private int empNo;
	private String writer;
	private Date createDate;
	private Date updateDate;
	private int refBoard;
	private String status;
	
	public Reply() {}

	public Reply(int replyNo, String content, int empNo, String writer, Date createDate, Date updateDate, int refBoard,
			String status) {
		super();
		this.replyNo = replyNo;
		this.content = content;
		this.empNo = empNo;
		this.writer = writer;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.refBoard = refBoard;
		this.status = status;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getRefBoard() {
		return refBoard;
	}

	public void setRefBoard(int refBoard) {
		this.refBoard = refBoard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", content=" + content + ", empNo=" + empNo + ", writer=" + writer
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", refBoard=" + refBoard + ", status="
				+ status + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
