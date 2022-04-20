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

import vo.CashBook;

public class CashBookDao {
	
	// 상세보기 눌렀을때 정보들고오기위한 메서드
	public CashBook selectCashBookOne(int cashbookNo) {
		// return할 cashBook 선언
		CashBook cashBook = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			String sql = "SELECT * FROM cashbook WHERE cashbook_no =?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cashBook = new CashBook();
				cashBook.setCashbookNo(rs.getInt("cashbook_no"));
				cashBook.setCashDate(rs.getString("cash_date"));
				cashBook.setKind(rs.getString("kind"));
				cashBook.setCash(rs.getInt("cash"));
				cashBook.setMemo(rs.getString("memo"));
				cashBook.setUpdateDate(rs.getString("update_date"));
				cashBook.setCreateDate(rs.getString("create_date"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cashBook;
		
	}
	
	//입력위한 메서드
	public void insertCashBook(CashBook cashBook, List<String> hashtag) {
		Connection conn =null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			String sql = "INSERT INTO cashbook(cash_date,kind,cash,memo,update_date,create_date)"
						+ "   VALUES(?,?,?,?,NOW(),NOW())";
			
			//sql(insert)만 실행하는게 아니라 select(방금 생성된 행의 키값)도 같이 실행 ex) select 방금입력한 cashbook_no from cashbook;
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1,  cashBook.getCashDate());
			stmt.setString(2,  cashBook.getKind());
			stmt.setInt(3,  cashBook.getCash());
			stmt.setString(4,  cashBook.getMemo());
			
			stmt.executeUpdate(); //insert
			rs = stmt.getGeneratedKeys(); // select 방금입력한 cashbook_no from cashbook
			
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
			// hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for(String h : hashtag) { // for문은 h 사이즈 개수만
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?,?,NOW())";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h); // for문 순서대로 
				
				stmt2.executeUpdate();
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	// 삭제위한 메서드
	public int deleteCashBook(int cashbookNo) {
		//삭제한 행 담을 row 생성
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		String cbSql = "DELETE FROM cashbook WHERE cashbook_no = ?"; 
		String htSql = "DELETE FROM hashtag WHERE cashbook_no = ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			//해시태그 삭제
			stmt2 = conn.prepareStatement(htSql);
			stmt2.setInt(1, cashbookNo);
			
			// 가계부 삭제
			stmt = conn.prepareStatement(cbSql);
			stmt.setInt(1, cashbookNo);
		
			stmt2.executeUpdate();
			row = stmt.executeUpdate();
			
			if(row == 1) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 전체 달력출력위한 메서드
	public List<Map<String, Object>> selectCashBookListByMonth(int y, int m) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/*
		 SELECT 
		 	cashbook_no cashbookNo
		 	,DAY(cash_date) day
		 	,kind
		 	,cash
		 	,concat(LEFT(memo, 5),'...') memo
		 FROM cashbook
		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
		 ORDER BY DAY(cash_date) ASC
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "			,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}