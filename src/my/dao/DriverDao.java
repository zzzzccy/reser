package my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import my.model.Driver;

public class DriverDao {
	/*
	 * @根据司机名查找用户
	*/
	public Driver getDriverBydName(String dName) throws SQLException {
		Driver tmp = new Driver();

		Connection conn = JDUtil.getConnection();

		String sql = "SELECT * FROM reservation.driver WHERE dName='" + dName + "';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			tmp.setDid(rs.getInt("did"));
			tmp.setdName(rs.getString("dName"));
			tmp.setdPwd(rs.getString("dPwd"));
			tmp.setCarNum(rs.getString("carNum"));
			tmp.setPhone(rs.getString("phone"));
		} else {
			tmp = null;
		}
		
		JDUtil.closeConn(conn);
		
		return tmp;
	}
	
	/*
	 * @插入司机数据
	*/
	public int insertDriver(Driver upDriver) throws SQLException {
		
		Connection conn = JDUtil.getConnection();
		String sql = "insert into reservation.driver(dName,dPwd,carNum,phone) values(?,?,?,?)";
		PreparedStatement ptmta = conn.prepareStatement(sql);
		ptmta.setString(1,upDriver.getdName());
		ptmta.setString(2,upDriver.getdPwd());
		ptmta.setString(3, upDriver.getCarNum());
		ptmta.setString(4, upDriver.getPhone());
		int rs = ptmta.executeUpdate();
		
		JDUtil.closeConn(conn);
		return rs;
	}
	
	/*
	 * @更新司机数据
	*/
	public int update(Driver upDriver, String dName) throws SQLException {
		Connection conn = JDUtil.getConnection();
		String sql = "update reservation.driver set dPwd=?,carNum=?,phone=? where dName = ?";
		PreparedStatement ptmta = conn.prepareStatement(sql);
		ptmta.setString(1, upDriver.getdPwd());
		ptmta.setString(2, upDriver.getCarNum());
		ptmta.setString(3, upDriver.getPhone());
		ptmta.setString(4, dName);
		int rs = ptmta.executeUpdate();
		
		JDUtil.closeConn(conn);
		return rs;
	}
	
	
	/*
	 * 查找所有司机信息
	*/
	public ArrayList<Driver> queryAllDriver() throws Exception{
		ArrayList<Driver> list = new ArrayList<Driver>();
		
		Connection conn = JDUtil.getConnection();
     	 String sql = "select * from reservation.driver ";
 		// 3 、创建一个statement对象，并且执行sql
 		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		while(true) {
 			boolean flag = rs.next();
 			if(!flag) {
 				break;
 			}
 			int did = rs.getInt(1);
 			String dName = rs.getString(2);
 			String dPwd = rs.getString(3);
 			String carNum = rs.getString(4);
 			String phone = rs.getString(5);
 			
 			Driver tmpDriver = new Driver();
 			tmpDriver.setDid(did);
 			tmpDriver.setdName(dName);
 			tmpDriver.setdPwd(dPwd);
 			tmpDriver.setCarNum(carNum);
 			tmpDriver.setPhone(phone);
 			list.add(tmpDriver);
 		}
 		
 		
 		JDUtil.closeConn(conn);
		return list;
	}

}
