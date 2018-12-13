package my.model;

public class Complaint {

	private int cid;//反馈/投诉id
	private int udid;//反馈/投诉人id
	private int cudid;//反馈/投诉对象id
	private String msg;//反馈/投诉信息
	private String status;//状态：1-待审核、2-已通过、3-不通过
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUdid() {
		return udid;
	}
	public void setUdid(int udid) {
		this.udid = udid;
	}
	public int getCudid() {
		return cudid;
	}
	public void setCudid(int cudid) {
		this.cudid = cudid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
