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

@WebServlet("/UpdateCashBookController")
public class UpdateCashBookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인 된 상태가 아니라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		//요청값 불러오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println("cashbokNo(UpdateCashBookController.doGet()) : " + cashbookNo);
		
		// cashbookNo이 null이라면 다시 가계부로 보내기
		if(request.getParameter("cashbookNo") == null) {
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController?null=cashbookNo");
			return;
		}
		
		CashBookDao cashBookDao = new CashBookDao();
		
		// cashBookDao에서 선택한 가계부 정보 담아올 메서드 호출해서 담기
		CashBook cashBook = cashBookDao.selectCashBookOne(cashbookNo);
		request.setAttribute("cashBook", cashBook);
		
		// 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/UpdateCashBook.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//요청값 불러오기
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		
		//디버깅
		System.out.println(cashbookNo + " <--cashbookNo UpdateCashBookController.doPost()");
		System.out.println(cashDate + " <--cashDate UpdateCashBookController.doPost()");
		System.out.println(kind + " <--kind UpdateCashBookController.doPost()");
		System.out.println(cash + " <--cash UpdateCashBookController.doPost()");
		System.out.println(memo + " <--memo UpdateCashBookController.doPost()");
		
		//요청값 변수 가공
		CashBook cashBook = new CashBook();
		cashBook.setCashbookNo(cashbookNo);
		cashBook.setCashDate(cashDate);
		cashBook.setKind(kind);
		cashBook.setCash(cash);
		cashBook.setMemo(memo);
		
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
		System.out.println(hashtag.size() + " <--hashtag.size UpdateCashBookController.doPost()");
		for(String h : hashtag) {
			System.out.println(h + " <-- hashtag UpdateCashBookController.doPost()");
		}
		
		HttpSession session = request.getSession(); 
		String memberId = (String)session.getAttribute("sessionMemberId");
		
		CashBookDao cashBookDao = new CashBookDao();
		
		//cashBookDao의 수정할 UpdateCashBook메서드 호출해서 수정한 개수 row 변수에 담기
		int row = cashBookDao.UpdateCashBook(cashBook, hashtag, memberId);
		
		if(row == 1) {
			//뷰 포워딩
			response.sendRedirect(request.getContextPath()+"/CashBookOneController?cashbookNo="+cashbookNo);
		} else {
			response.sendRedirect(request.getContextPath()+"/UpdateCashBookController?error=UpdateFail");
		}
		
	}

}
