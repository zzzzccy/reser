package my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import my.model.Evaluation;


public class EvaluationDao {
	/*
	 * 根据评价id查找评价
	*/
	public Evaluation getEvaluationByEid(int eid) throws Exception {
		Evaluation evaluation = new Evaluation();
		Connection conn = JDUtil.getConnection();
		String sql = "SELECT * FROM reservation.`evaluation` WHERE eid='"+eid+"';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			evaluation.setEid(rs.getInt("eid"));
			evaluation.setOid(rs.getInt("oid"));
		}
		
		JDUtil.closeConn(conn);
		
		return evaluation;
	}
	
	/*
	 * 计算评价总数
	*/
	public int countEvaluation() throws Exception {
		int count=0;
		Connection conn = JDUtil.getConnection();
		String sql = "select count(*) from reservation.evaluation";
		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		rs.next();
 		count = rs.getInt(1);
		
 		JDUtil.closeConn(conn);
		return count;
	}
	
	/*
	 * 添加评价信息
	*/
	public int insertEvaluation(Evaluation upEvaluation) throws Exception {
		Connection conn = JDUtil.getConnection();
		String sql = "INSERT INTO `reservation`.`evaluation` (`eid`, `oid`) VALUES (?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, upEvaluation.getEid());
		pstm.setInt(2, upEvaluation.getOid());
		
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
		EvaluationDao ed = new EvaluationDao();
		Evaluation ev = new Evaluation();
		int eid = 0;
		try {
			System.out.println("count");
			eid = ed.countEvaluation();		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		eid++;
		System.out.println(eid);
		ev.setEid(eid);
		ev.setOid(1);
		try {
			System.out.println("asd");
			ed.insertEvaluation(ev);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Evaluation tmp = new Evaluation();
		try {
			System.out.println("dsa");
			tmp = ed.getEvaluationByEid(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tmp.getEid());
		System.out.println(tmp.getOid());
	}*/
}
