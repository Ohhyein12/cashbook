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
	public int insertCashBook(CashBook cashBook, List<String> hashtag) {
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			String sql = "INSERT INTO cashbook"
					+ "        (cash_date"
					+ "         , kind"
					+ "         , cash"
					+ "         , memo"
					+ "         , update_date"
					+ "         , create_date"
					+ "         , member_id)"
					+ "   VALUES(?,?,?,?,NOW(),NOW(),?)";
			
			//sql(insert)만 실행하는게 아니라 select(방금 생성된 행의 키값)도 같이 실행 ex) select 방금입력한 cashbook_no from cashbook;
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashBook.getCashDate());
			stmt.setString(2, cashBook.getKind());
			stmt.setInt(3, cashBook.getCash());
			stmt.setString(4, cashBook.getMemo());
			stmt.setString(5, cashBook.getMemberId());
			
			row = stmt.executeUpdate(); //insert
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
		return row;
	}
	// 수정위한 메서드
	public int UpdateCashBook(CashBook cashBook, List<String> hashtag) {
		//수정한 행 개수 담을 row 생성
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			String CashBookSql = "UPDATE cashbook SET"
					+"           cash_date=?"
					+"			 , kind=?"
					+"			 , cash=?"
					+"			 , memo=?"
					+"			 , update_date=NOW()"
					+"			 , member_id=?"
					+"	 WHERE cashbook_no=?";
			
			stmt = conn.prepareStatement(CashBookSql);
			stmt.setString(1, cashBook.getCashDate());
			stmt.setString(2, cashBook.getKind());
			stmt.setInt(3, cashBook.getCash());
			stmt.setString(4, cashBook.getMemo());
			stmt.setString(5, cashBook.getMemberId());
			stmt.setInt(6, cashBook.getCashbookNo());
			
			
			row = stmt.executeUpdate(); 
			
			// hashtag를 저장하기전 해당 태그 삭제 
			PreparedStatement deleteHashTagStmt = null;
			for(String h : hashtag) { // for문은 h 사이즈 개수만
				String deleteHastTagSql = "DELETE FROM hashtag WHERE cashbook_no=?";
				deleteHashTagStmt = conn.prepareStatement(deleteHastTagSql);
				deleteHashTagStmt.setInt(1, cashBook.getCashbookNo());
				deleteHashTagStmt.executeUpdate();
			}
			
			// hashtag를 저장하는 코드
			PreparedStatement insertHashTagStmt = null;
			for(String h : hashtag) { // for문은 h 사이즈 개수만
				String insertHashTagSql = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?,?,NOW())";
				insertHashTagStmt = conn.prepareStatement(insertHashTagSql);
				insertHashTagStmt.setInt(1, cashBook.getCashbookNo());
				insertHashTagStmt.setString(2, h); // for문 순서대로
				insertHashTagStmt.executeUpdate();
			}
			
			conn.commit(); // 최종 커밋
			
		} catch (Exception e) {
			try {
				conn.rollback(); //예외 발생하면 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				// DB자원반납
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//수정한 행 개수 담은 row 변수 리턴
		return row;
	}
	
	// 삭제위한 메서드
	public int deleteCashBook(int cashbookNo, String cashbookPw) {
		//삭제한 행 개수 담을 row 생성
		int row = 0;
		Connection conn = null;
		PreparedStatement hashtagStmt = null;
		PreparedStatement cashbookStmt = null;
		String cashbookSql = "DELETE c"
				+ "			  FROM cashbook c"
				+ "			  INNER JOIN member m"
				+ "				  ON c.member_id = m.member_id"
				+ "			  WHERE c.cashbook_no = ? AND m.member_pw=PASSWORD(?)"; 
		
		String hashtagSql = "DELETE FROM hashtag WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			//해시태그 삭제
			hashtagStmt = conn.prepareStatement(hashtagSql);
			hashtagStmt.setInt(1, cashbookNo);
			
			// 가계부 삭제
			cashbookStmt = conn.prepareStatement(cashbookSql);
			cashbookStmt.setInt(1, cashbookNo);
			cashbookStmt.setString(2, cashbookPw);
		
			hashtagStmt.executeUpdate();
			row = cashbookStmt.executeUpdate();
			
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
	public List<Map<String, Object>> selectCashBookListByMonth(int y, int m, String sessionMemberId) {
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
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ? AND member_id=?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			stmt.setString(3, sessionMemberId);
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