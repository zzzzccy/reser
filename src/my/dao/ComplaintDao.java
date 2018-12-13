package my.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import my.model.Complaint;

public class ComplaintDao {

	/*
	 * 反馈/投诉不通过
	*/
	public void refuseComplaint(int cid) throws Exception {
		
		Connection conn = JDUtil.getConnection();
		String next="不通过";
		String sql = "UPDATE `reservation`.`complaint` SET `status`='"+next+"' WHERE cid='"+cid+"';";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		
		JDUtil.closeConn(conn);
	}
	
	/*
	 * 计算反馈/投诉总数
	*/
	public int countComplaint() throws Exception {
		int count=0;
		Connection conn = JDUtil.getConnection();
		String sql = "select count(*) from reservation.complaint";
		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		rs.next();
 		count = rs.getInt(1);
		
 		JDUtil.closeConn(conn);
		return count;
	}
	
	/*
	 * 添加反馈/投诉
	*/
	public int insertComplaint(Complaint complaint) throws Exception {
		
		Connection conn = JDUtil.getConnection();
		String sql = "INSERT INTO `reservation`.`complaint` (`cid`, `udid`, `cudid`, `msg`, `status`) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, complaint.getCid());
		pstm.setInt(2, complaint.getUdid());
		pstm.setInt(3, complaint.getCudid());
		pstm.setString(4, complaint.getMsg());
		pstm.setString(5, complaint.getStatus());
		int flag = 1;
		try {
			pstm.execute();
		} catch (Exception e) {
			flag = 0;
		}
		
		JDUtil.closeConn(conn);
		return flag;
	}
	
	/*
	 * 查找反馈/投诉
	*/
	public Complaint getComplaintByCid(int cid) throws Exception {
		Complaint complaint = new Complaint();
		Connection conn = JDUtil.getConnection();
		String sql = "SELECT * FROM reservation.`complaint` WHERE cid='"+cid+"';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			complaint.setCid(rs.getInt("cid"));
			complaint.setUdid(rs.getInt("udid"));
			complaint.setCudid(rs.getInt("cudid"));
			complaint.setMsg(rs.getString("msg"));
			complaint.setStatus(rs.getString("status"));
		}
		
		JDUtil.closeConn(conn);
		
		return complaint;
	}
	
	/*
	 * 根据用户id查找反馈/投诉
	*/
	public ArrayList<Complaint> getUserComplaintByUdid(int udid) throws Exception {
		ArrayList<Complaint> uclist = new ArrayList<Complaint>();
		Connection conn = JDUtil.getConnection();
		
		String sql = "SELECT * FROM reservation.`complaint` WHERE udid='"+udid+"' order by cid desc;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			Complaint tmp = new Complaint();
			tmp.setCid(rs.getInt("cid"));
			tmp.setUdid(rs.getInt("udid"));
			tmp.setCudid(rs.getInt("cudid"));
			tmp.setMsg(rs.getString("msg"));
			tmp.setStatus(rs.getString("status"));
			uclist.add(tmp);
		}
		
		JDUtil.closeConn(conn);
		return uclist;
	}
	
	/*
	 * 反馈/投诉审核通过
	*/
	public void ComplaintPass(int cid) throws Exception {
		
		Connection conn = JDUtil.getConnection();
		Complaint checkComplaint = new Complaint();
		checkComplaint = getComplaintByCid(cid);
		String next = null;
		switch(checkComplaint.getStatus()) {
		case "待审核":
			next="已通过";break;
		default:
			break;
		}
		String sql = "UPDATE `reservation`.`complaint` SET `status`='"+next+"' WHERE cid='"+cid+"';";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		
		JDUtil.closeConn(conn);
	}
	
	
	/*
	 * @test
	*/
	/*public static void main(String[] args) {
		ComplaintDao cd = new ComplaintDao();
		Complaint c = new Complaint();
		int cid = 0;
		try {
			System.out.println("count");
			cid = cd.countComplaint();		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cid++;
		System.out.println(cid);
		c.setCid(cid);
		c.setUdid(1);
		c.setCudid(1);
		c.setMsg("Complaint msg test。");
		c.setStatus("待审核");
		try {
			System.out.println("asd");
			cd.insertComplaint(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("asd");
			cd.refuseComplaint(1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println("asd");
			cd.ComplaintPass(1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Complaint tmp = new Complaint();
		try {
			System.out.println("dsa");
			tmp = cd.getComplaintByCid(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tmp.getCid());
		System.out.println(tmp.getUdid());
		System.out.println(tmp.getCudid());
		System.out.println(tmp.getMsg());
		System.out.println(tmp.getStatus());
	}*/
}
