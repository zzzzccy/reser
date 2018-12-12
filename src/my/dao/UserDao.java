package my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import my.model.User;

public class UserDao {

	/*
	 * @根据用户名查找用户
	*/
	public User getUserByUname(String userName) throws SQLException {
		User tmp = new User();

		Connection conn = JDUtil.getConnection();

		String sql = "SELECT * FROM reservation.user WHERE userName='" + userName + "';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			tmp.setUid(rs.getInt("uid"));
			tmp.setUserName(rs.getString("userName"));
			tmp.setUserPwd(rs.getString("userPwd"));
			tmp.setName(rs.getString("name"));
			tmp.setSex(rs.getString("sex"));
			tmp.setAge(rs.getInt("age"));
			tmp.setPhone(rs.getString("phone"));
		} else {
			tmp = null;
		}
		
		JDUtil.closeConn(conn);
		
		return tmp;
	}
	
	/*
	 * @插入用户数据
	*/
	public int insertUser(User upUser) throws SQLException {
		
		Connection conn = JDUtil.getConnection();
		String sql = "insert into reservation.user(userName,userPwd,name,sex,age,phone) values(?,?,?,?,?,?)";
		PreparedStatement ptmta = conn.prepareStatement(sql);
		ptmta.setString(1,upUser.getUserName());
		ptmta.setString(2,upUser.getUserPwd());
		ptmta.setString(3, upUser.getName());
		ptmta.setString(4, upUser.getSex());
		ptmta.setInt(5, upUser.getAge());
		ptmta.setString(6, upUser.getPhone());
		int rs = ptmta.executeUpdate();
		
		JDUtil.closeConn(conn);
		return rs;
	}
	
	/*
	 * @更新用户数据
	*/
	public int update(User upUser, String userName) throws SQLException {
		Connection conn = JDUtil.getConnection();
		String sql = "update reservation.user set userPwd=?,name=?,age=?,phone=? where userName = ?";
		PreparedStatement ptmta = conn.prepareStatement(sql);
		ptmta.setString(1, upUser.getUserPwd());
		ptmta.setString(2, upUser.getName());
		ptmta.setInt(3, upUser.getAge());
		ptmta.setString(4, upUser.getPhone());
		ptmta.setString(5, userName);
		int rs = ptmta.executeUpdate();
		
		JDUtil.closeConn(conn);
		return rs;
	}
	
	
	/*
	 * 查找所有用户信息
	*/
	public ArrayList<User> queryAllUser() throws Exception{
		ArrayList<User> list = new ArrayList<User>();
		
		Connection conn = JDUtil.getConnection();
     	 String sql = "select * from reservation.user ";
 		// 3 、创建一个statement对象，并且执行sql
 		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		while(true) {
 			boolean flag = rs.next();
 			if(!flag) {
 				break;
 			}
 			int uid = rs.getInt(1);
 			String userName = rs.getString(2);
 			String userPwd = rs.getString(3);
 			String name = rs.getString(4);
 			String sex = rs.getString(5);
 			int age = rs.getInt(6);
 			String phone = rs.getString(7);
 			
 			User tmpUser = new User();
 			tmpUser.setUid(uid);
 			tmpUser.setUserName(userName);
 			tmpUser.setUserPwd(userPwd);
 			tmpUser.setName(name);
 			tmpUser.setSex(sex);
 			tmpUser.setAge(age);
 			tmpUser.setPhone(phone);
 			list.add(tmpUser);
 		}
 		
 		
 		JDUtil.closeConn(conn);
		return list;
	}
	
	
	/*
	 * @test
	 * */
	
/*	public static void main(String args[]) {
		
		UserDao ud = new UserDao();
		User tmp = new User();
		tmp.setUserName("22");
		tmp.setUserPwd("33");
		tmp.setName("44");
		tmp.setSex("55");
		tmp.setAge(66);
		tmp.setPhone("77");
		try {
			System.out.println("dsa");
			ud.update(tmp, "2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("dsa");
			ud.insertUser(tmp);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println("asd");
			tmp = ud.getUserByUname("22");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tmp.getUid());
		System.out.println(tmp.getUserName());
		System.out.println(tmp.getUserPwd());
		System.out.println(tmp.getName());
		System.out.println(tmp.getSex());
		System.out.println(tmp.getAge());
		System.out.println(tmp.getPhone());
		
	}*/

}
