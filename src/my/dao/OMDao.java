package my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import my.model.OrderMess;


public class OMDao {
	
	/*
	 * 查找订单信息
	*/
	public OrderMess getOrderMessByOid(int oid) throws Exception {
		OrderMess om = new OrderMess();
		Connection conn = JDUtil.getConnection();
		String sql = "SELECT * FROM reservation.`order_mess` WHERE oid='"+oid+"';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			om.setOmid(rs.getInt("omid"));
			om.setOid(rs.getInt("oid"));
			om.setDid(rs.getInt("did"));
		}
		
		JDUtil.closeConn(conn);
		
		return om;
	}
	
	/*
	 * 计算订单信息总数
	*/
	public int countOrderMess() throws Exception {
		int count=0;
		Connection conn = JDUtil.getConnection();
		String sql = "select count(*) from reservation.order_mess";
		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		rs.next();
 		count = rs.getInt(1);
		
 		JDUtil.closeConn(conn);
		return count;
	}
	
	/*
	 * 添加订单信息
	*/
	public int insertOrderMess(OrderMess upOM) throws SQLException {
		Connection conn = JDUtil.getConnection();
		//"INSERT INTO `merchant`.`order_message` (`oid`, `cid`, `number`, `tprice`) VALUES (1, 1, 11, 22);";
		String sql = "INSERT INTO `reservation`.`order_mess` (`omid`, `oid`, `did`) VALUES (?, ?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, upOM.getOmid());
		pstm.setInt(2, upOM.getOid());
		pstm.setInt(3, upOM.getDid());
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
	 * @test
	*/
	/*public static void main(String[] args) {
		OMDao omd = new OMDao();
		OrderMess om = new OrderMess();
		int omid = 0;
		try {
			System.out.println("count");
			omid = omd.countOrderMess();			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		omid++;
		System.out.println(omid);
		om.setOmid(omid);
		om.setOid(1);
		om.setDid(1);
		try {
			System.out.println("asd");
			omd.insertOrderMess(om);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderMess tmp = new OrderMess();
		try {
			System.out.println("dsa");
			tmp = omd.getOrderMessByOid(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tmp.getOmid());
		System.out.println(tmp.getOid());
		System.out.println(tmp.getDid());
	}*/
}
