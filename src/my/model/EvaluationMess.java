package my.model;

import java.math.BigDecimal;

public class EvaluationMess {

	private int emid;//评价信息id
	private int eid;//评价id
	private int uid;//用户id
	private String msg;//评价内容
	private BigDecimal score;//评分(0-5)
	
	public int getEmid() {
		return emid;
	}
	public void setEmid(int emid) {
		this.emid = emid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	
}
