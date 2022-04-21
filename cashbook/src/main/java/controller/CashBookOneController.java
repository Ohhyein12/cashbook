package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashBookDao;
import vo.CashBook;

@WebServlet("/CashBookOneController")
public class CashBookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청값 받아오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		CashBookDao cashBookDao = new CashBookDao();
		
		// cashBook에 selectCashBookOne메서드로 호출한 상세정보 저장
		CashBook cashBook = cashBookDao.selectCashBookOne(cashbookNo);
		request.setAttribute("cashBook", cashBook);
	
		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookOne.jsp").forward(request, response);
	}
}