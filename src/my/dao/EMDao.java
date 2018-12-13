package my.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import my.model.EvaluationMess;

public class EMDao {

	/*
	 * 查找评价信息
	*/
	public EvaluationMess getEvaluationMessByEid(int eid) throws Exception {
		EvaluationMess em = new EvaluationMess();
		Connection conn = JDUtil.getConnection();
		String sql = "SELECT * FROM reservation.`evaluation_mess` WHERE eid='"+eid+"';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			em.setEmid(rs.getInt("emid"));
			em.setEid(rs.getInt("eid"));
			em.setUid(rs.getInt("uid"));
			em.setMsg(rs.getString("msg"));
			em.setScore(rs.getBigDecimal("score"));
		}
		
		JDUtil.closeConn(conn);
		return em;
	}
	
	/*
	 * 根据用户id查找评价信息
	*/
	public ArrayList<EvaluationMess> getEvaluationMessByUid(int uid) throws Exception {
		ArrayList<EvaluationMess> uemlist = new ArrayList<EvaluationMess>();
		Connection conn = JDUtil.getConnection();
		String sql = "SELECT * FROM reservation.`evaluation_mess` WHERE uid='"+uid+"' order by uid desc;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			EvaluationMess tmp = new EvaluationMess();
			tmp.setEmid(rs.getInt("emid"));
			tmp.setEid(rs.getInt("eid"));
			tmp.setUid(rs.getInt("uid"));
			tmp.setMsg(rs.getString("msg"));
			tmp.setScore(rs.getBigDecimal("score"));
			uemlist.add(tmp);
		}
		
		JDUtil.closeConn(conn);
		return uemlist;
	}
	
	/*
	 * 计算评价信息总数
	*/
	public int countEvaluationMess() throws Exception {
		int count=0;
		Connection conn = JDUtil.getConnection();
		String sql = "select count(*) from reservation.evaluation_mess";
		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		rs.next();
 		count = rs.getInt(1);
		
		return count;
	}
	
	/*
	 * 添加评价信息
	*/
	public int insertEvaluationMess(EvaluationMess upEM) throws Exception {
		Connection conn = JDUtil.getConnection();
		String sql = "INSERT INTO `reservation`.`evaluation_mess` (`emid`, `eid`, `uid`, `msg`, `score`) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, upEM.getEmid());
		pstm.setInt(2, upEM.getEid());
		pstm.setInt(3, upEM.getUid());
		pstm.setString(4, upEM.getMsg());
		pstm.setBigDecimal(5, upEM.getScore());
		
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
		EMDao emd = new EMDao();
		EvaluationMess em = new EvaluationMess();
		int emid = 0;
		try {
			System.out.println("count");
			emid = emd.countEvaluationMess();			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		emid++;
		System.out.println(emid);
		em.setEmid(emid);
		em.setEid(1);
		em.setUid(1);
		em.setMsg("evaluation message test。");
		BigDecimal score = new BigDecimal("4");
		em.setScore(score);
		try {
			System.out.println("asd");
			emd.insertEvaluationMess(em);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EvaluationMess tmp = new EvaluationMess();
		try {
			System.out.println("dsa");
			tmp = emd.getEvaluationMessByEid(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tmp.getEmid());
		System.out.println(tmp.getEid());
		System.out.println(tmp.getUid());
		System.out.println(tmp.getMsg());
		System.out.println(tmp.getScore());
	}*/
}
