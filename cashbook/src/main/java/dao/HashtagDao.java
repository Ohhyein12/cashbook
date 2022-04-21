package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashtagDao {
	public List<Map<String, Object>> selectTagRankList() {
		List<Map<String, Object>> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql = "SELECT kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank"
					+ "		FROM"
					+ "		(SELECT c.kind kind, tag, COUNT(*) cnt"
					+ " 	FROM hashtag t INNER JOIN cashbook c"
					+ " 	ON t.cashbook_no = c.cashbook_no"
					+ " 	GROUP BY t.tag) t";
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getString("t.cnt"));
				map.put("rank", rs.getString("rank"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//수입, 지출 눌렀을때 항목 들고올 메서드
	public List<Map<String, Object>> selectKindByList(String kind) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql = "SELECT kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank"
					+ "		FROM"
					+ "		(SELECT c.kind kind, tag, COUNT(*) cnt"
					+ " 	FROM hashtag t INNER JOIN cashbook c"
					+ " 	ON t.cashbook_no = c.cashbook_no"
					+ " 	WHERE c.kind = ?"
					+ " 	GROUP BY t.tag) t";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, kind);
			
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("tag"));
				map.put("cnt", rs.getString("t.cnt"));
				map.put("rank", rs.getString("rank"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 날짜별 검색 메서드 구현
	public List<Map<String, Object>> selectDateByList(String beginDate, String lastDate) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql = "SELECT"
					+ "	 		c.cash_date"
					+ "			,c.cashbook_no"
					+ "			,c.kind"
					+ "			,h.tag"
					+ "			,c.cash"
					+ "	  FROM hashtag h"
					+ "	  INNER JOIN cashbook c"
					+ "	  ON c.cashbook_no = h.cashbook_no"
					+ "	  WHERE c.cash_date BETWEEN ? AND ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, beginDate);
			stmt.setString(2, lastDate);
			
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashDate", rs.getString("cash_date"));
				map.put("cashbookNo", rs.getString("cashbook_no"));
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("tag"));
				map.put("cash", rs.getString("cash"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 상세보기
	public List<Map<String, Object>> selectTagByList(String tag) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql = "SELECT"
					+ " 	h.tag"
					+ " 	,c.cash_date"
					+ " 	,c.kind"
					+ " 	,c.cash"
					+ " 	,c.memo"
					+ " FROM hashtag h"
					+ " INNER JOIN cashbook c"
					+ " ON h.cashbook_no = c.cashbook_no"
					+ " WHERE h.tag = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tag", rs.getString("tag"));
				map.put("cashDate", rs.getString("cash_date"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getString("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
