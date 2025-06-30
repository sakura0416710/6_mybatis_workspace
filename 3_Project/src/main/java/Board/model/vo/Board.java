package Board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String title;
	private String content;
	private int empNo;
	private String writer;
	private int count;
	private Date createDate;
	private Date updateDate;
	private String isNotice;
	private String status;
	
	public Board() {}

	public Board(int boardNo, String title, String content, int empNo, String writer, int coutnt, Date createDate,
			Date updateDate, String isNotice, String status) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.empNo = empNo;
		this.writer = writer;
		this.count = count;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.isNotice = isNotice;
		this.status = status;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", empNo=" + empNo
				+ ", writer=" + writer + ", count=" + count + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", isNotice=" + isNotice + ", status=" + status + "]";
	}
	
	
}
