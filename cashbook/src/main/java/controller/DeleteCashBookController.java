package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashBookDao;

@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//삭제할 cashbookNo 요청값 불러오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		//디버깅
		System.out.println("cashbookNo(deleteCashBook):"+cashbookNo);
		
		CashBookDao cashBookDao = new CashBookDao();
		
		// 메서드 호출해서 삭제된 행 개수 담아오기
		int row = cashBookDao.deleteCashBook(cashbookNo);
		
		//디버깅
		System.out.println("row(deleteCashBook): " + row);
		
		// 끝났으면 달력페이지로 돌아가기
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}
}