package co.kr.board.vo;

public class BoardVO {
	
	
	private int bnum  ;
	private String title ;
	private String writer ;
	private String bdate ;
	private int bcnt ;
	private String bcontent ;
	private String btype ;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBcnt() {
		return bcnt;
	}

	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public BoardVO(int bnum, String title, String writer, String bdate, int bcnt, String bcontent, String btype) {
		//super();
		this.bnum = bnum;
		this.title = title;
		this.writer = writer;
		this.bdate = bdate;
		this.bcnt = bcnt;
		this.bcontent = bcontent;
		this.btype = btype;
	}

	@Override
	public String toString() {
		return "BoardVO [bnum=" + bnum + ", title=" + title + ", writer=" + writer + ", bdate=" + bdate + ", bcnt="
				+ bcnt + ", bcontent=" + bcontent + ", btype=" + btype + "]";
	}
	
	
}
