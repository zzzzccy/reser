package my.model;

import java.util.Date;

public class Order {
	
	private int oid;//预约id
	private int uid;//用户id
	private Date odate;//预约日期
	private String otime;//预约时间
	private String addr;//预约地址
	private String ophone;//预约手机号
	private String status;//状态:1-等待接收、2-已取消、3-审批中、4-订单进行中、5-等待评价、6-已评价
	
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getOphone() {
		return ophone;
	}
	public void setOphone(String ophone) {
		this.ophone = ophone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
