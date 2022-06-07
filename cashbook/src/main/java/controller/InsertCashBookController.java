package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashBookDao;
import vo.CashBook;

@WebServlet("/InsertCashBookController")
public class InsertCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(InsertCashBookController) :" + sessionMemberId);
		if(sessionMemberId == null) { // 로그인 된 상태가 아니라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashDate = y+"-"+m+"-"+d;
		request.setAttribute("cashDate", cashDate);
		request.setAttribute("y",y);
		request.setAttribute("m",m);
		request.getRequestDispatcher("/WEB-INF/view/InsertCashBookForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//요청값 불러오기
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		// 헌재 로그인된 세션의 아이디 받아와서 저장하기
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		
		//디버깅
		System.out.println(cashDate + " <--cashDate InsertCashBookController.doPost()");
		System.out.println(kind + " <--kind InsertCashBookController.doPost()");
		System.out.println(cash + " <--cash InsertCashBookController.doPost()");
		System.out.println(memo + " <--memo InsertCashBookController.doPost()");
		System.out.println(sessionMemberId + "<--sessionMemberId InsertCashBookController.doPost()");
		
		//요청값 데이터 바인딩
		CashBook cashBook = new CashBook();
		cashBook.setCashDate(cashDate);
		cashBook.setKind(kind);
		cashBook.setCash(cash);
		cashBook.setMemo(memo);
		cashBook.setMemberId(sessionMemberId);
		
		//태그 값 구하기
		List<String> hashtag = new ArrayList<>(); // tag 넣을 리스트
		String memo2 = memo.replace("#", " #"); // ## 불가능하게 만들기 "#"를 " #"로 바꾸는 문자열 새로 만들어서 memo2에 저장
		String[] arr = memo2.split(" "); // memo를 " "토큰으로 나눔
		for(String s : arr) {
			if(s.startsWith("#")) { // 문장이 #로 시작되면
				String temp = s.replace("#", ""); //#를 모두 공백으로 바꾸고 temp에 임시 저장한 후 
				if(temp.length()>0) { // temp가 빈칸이 아니라면
					hashtag.add(temp); //리스트에 저장
				}
			}
		}
		//디버깅
		System.out.println(hashtag.size() + " <--hashtag.size InsertCashBookController.doPost()");
		for(String h : hashtag) {
			System.out.println(h + " <-- hashtag InsertCashBookController.doPost()");
		}
		
		CashBookDao cashBookDao = new CashBookDao();
		int row = cashBookDao.insertCashBook(cashBook, hashtag);
		
		//뷰 포워딩
		if(row == 1) { // 입력 성공했다면
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
		} else {
			response.sendRedirect(request.getContextPath()+"/InsertCashBookController?error=insertFail");
		}
	}
}
