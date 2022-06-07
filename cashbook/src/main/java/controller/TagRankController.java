package controller;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HashtagDao;


@WebServlet("/TagRankController")
public class TagRankController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(TagRankController) :" + sessionMemberId);
		if(sessionMemberId == null) { // 로그인 된 상태가 아니라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		HashtagDao hashtagDao = new HashtagDao();
		
		// 오늘 날짜 뽑아오기
		Date nowTime = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				
		//요청값 불러오기 
		String kind = null;
		String beginDate = "0000-00-00"; // 초기값 정해주기
		String nowDate = sf.format(nowTime);
		String lastDate = nowDate; 
		
		
		if(request.getParameter("kind")!=null) { // 지출, 수입 중 하나를 선택했다면 값이 들어올 것
			kind = request.getParameter("kind");
		} 
		
		if(request.getParameter("lastDate")!=null && !"".equals(request.getParameter("lastDate")) && !"nowDate".equals(request.getParameter("lastDate"))) {
			System.out.println("".equals(request.getParameter("lastDate")));
			
			lastDate = request.getParameter("lastDate");
		}
		if(request.getParameter("beginDate")!=null && !"".equals(request.getParameter("beginDate"))) {
			System.out.println("".equals(request.getParameter("beginDate")));
			
			beginDate = request.getParameter("beginDate");
		}
		
		//디버깅
		System.out.println("kind(TagController) : " + kind);
		System.out.println("beginDate(TagController) : " + beginDate);
		System.out.println("lastDate(TagController) : " + lastDate);
		
		List<Map<String, Object>> list = null;
		
		if(request.getParameter("kind") == null && request.getParameter("beginDate") == null && request.getParameter("lastDate") == null) { // 수입 or 지출, 날짜를 누르지않았다면
			list = hashtagDao.selectTagRankList(sessionMemberId); // 전체 항목 리스트 
		} else {	
			list = hashtagDao.selectKindDateByList(kind, beginDate, lastDate, sessionMemberId); // 검색 했을 시 리스트
		}

		request.setAttribute("kind", kind);
		request.setAttribute("beginDate", beginDate);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("list", list);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/TagRankList.jsp").forward(request, response);
		
	}
}
