package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashBookDao;
import vo.CashBook;

@WebServlet("/CashBookOneController")
public class CashBookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 세션 작업
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(CashBookOneController) :" + sessionMemberId);
		if(sessionMemberId == null) { //로그인 된 상태가 아니라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		//요청값 받아오기 
		//상세보기 작업했다가 이전 버튼 눌렀을때 해당 년월로 보내기 위함
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		int m = -1;
		int y = -1;
		if(request.getParameter("m") != null && !"".equals(request.getParameter("m"))) {
			m = Integer.parseInt(request.getParameter("m"));
			y = Integer.parseInt(request.getParameter("y"));
		}
		
		CashBookDao cashBookDao = new CashBookDao();
		
		// cashBook에 selectCashBookOne메서드로 호출한 상세정보 저장
		CashBook cashBook = cashBookDao.selectCashBookOne(cashbookNo);
		request.setAttribute("cashBook", cashBook);
		request.setAttribute("m", m);
		request.setAttribute("y", y);
	
		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookOne.jsp").forward(request, response);
	}
}