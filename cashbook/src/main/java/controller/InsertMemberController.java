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


@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) { // 로그인 상태가 아니라면 
			
			// 회원가입 버튼으로 들어왔다면 회원가입 jsp로 보내주기
			request.getRequestDispatcher("/WEB-INF/view/InsertMember.jsp").forward(request, response);
			
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩
		request.setCharacterEncoding("utf-8"); 
		
		// 요청값 불러오기
		String memberId = null;
		if(request.getParameter("memberId") != null && !"".equals(request.getParameter("memberId"))) {
			memberId = request.getParameter("memberId");
		}
		String memberName = null;
		if(request.getParameter("memberName") != null && !"".equals(request.getParameter("memberName"))) {
			memberName = request.getParameter("memberName");
		}
		String memberGender = null;
		if(request.getParameter("memberGender") != null && !"".equals(request.getParameter("memberGender"))) {
			memberGender = request.getParameter("memberGender");
		}
		
		String memberAddress = null;
		if(request.getParameter("memberAddress") != null && !"".equals(request.getParameter("memberAddress"))) {
			memberAddress = request.getParameter("memberAddress");
		}
		String memberPhone = null;
		if(request.getParameter("memberPhone") != null && !"".equals(request.getParameter("memberPhone"))) {
			memberPhone = request.getParameter("memberPhone");
		}
		String memberPw = null;
		if(request.getParameter("memberPw") != null && !"".equals(request.getParameter("memberPw"))) {
			memberPw = request.getParameter("memberPw");
		}
		
		
		
		//디버깅
		System.out.println("memberId(insertMemberController):" + memberId);
		System.out.println("memberName(insertMemberController):" + memberName);
		System.out.println("memberGender(insertMemberController):" + memberGender);
		System.out.println("memberAddress(insertMemberController):" + memberAddress);
		System.out.println("memberPhone(insertMemberController):" + memberPhone);
		System.out.println("memberPw(insertMemberController):" + memberPw);
	
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setMemberGender(memberGender);
		member.setMemberAddress(memberAddress);
		member.setMemberPhone(memberPhone);
		member.setMemberPw(memberPw);
		
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.insertMember(member);
		
		if(row != 1) { // 행 추가 실패했을경우
			System.out.println("회원가입 실패");
			response.sendRedirect(request.getContextPath()+"/InsertMemberController");
			return;
		} 
		
		//추가했으면 다시 로그인페이지로 보내기
		response.sendRedirect(request.getContextPath()+"/LoginController");
		
		
		
		
	}

}
