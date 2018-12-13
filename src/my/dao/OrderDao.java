package my.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import my.model.Order;

public class OrderDao {

	/*
	 * 取消订单
	*/
	public void cancelOrder(int oid) throws Exception {
		
		Connection conn = JDUtil.getConnection();
		String next="已取消";
		String sql = "UPDATE `reservation`.`order` SET `status`='"+next+"' WHERE oid='"+oid+"';";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		
		JDUtil.closeConn(conn);
		
	}
	
	/*
	 * 查找订单
	*/
	public Order getOrderByOid(int oid) throws Exception {
		Order order = new Order();
		Connection conn = JDUtil.getConnection();
		String sql = "SELECT * FROM reservation.`order` WHERE oid='"+oid+"';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			order.setOid(rs.getInt("oid"));
			order.setUid(rs.getInt("uid"));
			order.setOdate(rs.getDate("odate"));
			order.setOtime(rs.getString("otime"));
			order.setAddr(rs.getString("addr"));
			order.setOphone(rs.getString("ophone"));
			order.setStatus(rs.getString("status"));
		}
		
		JDUtil.closeConn(conn);
		
		return order;
	}
	
	/*
	 * 根据用户id查找订单
	*/
	public ArrayList<Order> getUserOrderByUid(int uid) throws Exception {
		ArrayList<Order> uolist = new ArrayList<Order>();
		Connection conn = JDUtil.getConnection();

		String sql = "SELECT * FROM reservation.`order` WHERE uid='"+uid+"' order by oid desc;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			Order tmp = new Order();
			tmp.setOid(rs.getInt("oid"));
			tmp.setUid(rs.getInt("uid"));
			tmp.setOdate(rs.getDate("odate"));
			tmp.setOtime(rs.getString("otime"));
			tmp.setAddr(rs.getString("addr"));
			tmp.setOphone(rs.getString("ophone"));
			tmp.setStatus(rs.getString("status"));
			uolist.add(tmp);
		}
		
		JDUtil.closeConn(conn);
		return uolist;
	}
	
	/*
	 * 计算订单总数
	*/
	public int countOrder() throws Exception {
		int count=0;
		Connection conn = JDUtil.getConnection();
		String sql = "select count(*) from reservation.order";
		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		rs.next();
 		count = rs.getInt(1);
		
 		JDUtil.closeConn(conn);
		return count;
	}
	
	/*
	 * 添加订单
	*/
	public int insertOrder(Order order) throws SQLException {
		Connection conn = JDUtil.getConnection();
		String sql = "INSERT INTO `reservation`.`order` (`oid`, `uid`, `odate`, `otime`, `addr`, `ophone`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, order.getOid());
		pstm.setInt(2, order.getUid());
		pstm.setDate(3, (Date) order.getOdate());
		pstm.setString(4, order.getOtime());
		pstm.setString(5, order.getAddr());
		pstm.setString(6, order.getOphone());
		pstm.setString(7, order.getStatus());
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
	 * 订单状态改变
	*/
	public void OrderStatusNext(int oid) throws Exception {
		
		Connection conn = JDUtil.getConnection();
		Order checkorder = new Order();
		checkorder = getOrderByOid(oid);
		String next;
		switch(checkorder.getStatus()) {
		case "等待接受":
			next="审批中";break;
		case "审批中":
			next="订单进行中";break;
		case "订单进行中":
			next="等待评价";break;
		default:
			next="已评价";break;
		}
		String sql = "UPDATE `reservation`.`order` SET `status`='"+next+"' WHERE oid='"+oid+"';";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		
		JDUtil.closeConn(conn);
		
	}
	
	
	/*
	 * @test
	*/
	/*public static void main(String[] args) {
		OrderDao od = new OrderDao();
		Order o = new Order();
		int oid = 0;
		try {
			System.out.println("count");
			oid = od.countOrder();			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		oid++;
		System.out.println(oid);
		o.setOid(oid);
		o.setUid(1);
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");     
		java.util.Date date = null;
		try {
			date = sdf.parse("2018-11-28");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date odate = new java.sql.Date(date.getTime());
		o.setOdate(odate);
		o.setOtime("16:20");
		o.setAddr("asdasd");
		o.setOphone("13456789210");
		o.setStatus("等待接受");
		try {
			System.out.println("asd");
			od.insertOrder(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("asd");
			od.cancelOrder(1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println("asd");
			od.OrderStatusNext(1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Order tmp = new Order();
		try {
			System.out.println("dsa");
			tmp = od.getOrderByOid(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tmp.getOid());
		System.out.println(tmp.getUid());
		System.out.println(tmp.getOdate());
		System.out.println(tmp.getOtime());
		System.out.println(tmp.getAddr());
		System.out.println(tmp.getOphone());
		System.out.println(tmp.getStatus());
	}*/
}
