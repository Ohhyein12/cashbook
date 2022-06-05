package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashBookDao;

@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(DeleteCashBookController) :" + sessionMemberId);
		if(sessionMemberId == null) { // 로그인 된 상태가 아니라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		//삭제할 cashbookNo 요청값 불러오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		//디버깅
		System.out.println("cashbookNo(deleteCashBook):"+cashbookNo);
		
		request.setAttribute("cashbookNo", cashbookNo);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/DeleteCashBook.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//삭제할 cashbookNo, pw 요청값 불러오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String cashbookPw = request.getParameter("cashbookPw");
		
		//디버깅
		System.out.println("cashbookNo(deleteCashBook):"+cashbookNo);
		System.out.println("cashbookPw(deleteCashBook):"+cashbookPw);
		
		CashBookDao cashBookDao = new CashBookDao();
		
		// 메서드 호출해서 삭제된 행 개수 담아오기
		int row = cashBookDao.deleteCashBook(cashbookNo, cashbookPw);
		//디버깅
		System.out.println("row(deleteCashBook): " + row);
		if(row != 1) { // 삭제 실패했다면 
			System.out.println("삭제 실패");
		}
		
		// 끝났으면 달력페이지로 돌아가기(삭제 성공하던 실패하던)
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
}