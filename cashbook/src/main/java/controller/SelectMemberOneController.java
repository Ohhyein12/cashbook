package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(SelectMemberOneController) :" + sessionMemberId);
		if(sessionMemberId == null) { // 로그인 상태가 아니라면 
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		MemberDao memberDao = new MemberDao();
		
		// 사용자의 개인정보 들고올 메서드 호출해서 member객체에 담기
		Member member = memberDao.selectMemberOne(sessionMemberId);
		
		request.setAttribute("member", member);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/MemberOne.jsp").forward(request, response);
	}

}
