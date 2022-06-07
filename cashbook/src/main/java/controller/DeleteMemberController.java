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


@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(DeleteMemberController) :" + sessionMemberId);
		if(sessionMemberId == null) { // 로그인 된 상태가 아니라면
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		//뷰 포워딩(삭제 폼으로)
		request.getRequestDispatcher("/WEB-INF/view/DeleteMember.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = null;
		if(request.getParameter("memberPw")!= null && !"".equals(request.getParameter("memberPw"))) {
			memberPw = request.getParameter("memberPw");
		}
		
		//디버깅
		System.out.println("memberId(DeleteMemberController):"+ memberId);
		System.out.println("memberPw(DeleteMemberController):"+ memberPw);
		
		//데이터 바인딩
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberDao = new MemberDao();
		
		// memberDao의 deleteMember메서드 호출해서 멤버 삭제 시키고 삭제된 행 개수 받아서 저장
		int row = memberDao.deleteMember(member);
		
		if(row != 1) { // 행 삭제 실패했을경우
			System.out.println("정보 삭제 실패");
			response.sendRedirect(request.getContextPath()+"/DeleteMemberController?error=DeleteFail");
			return;
		} 
		
		//삭제했으면 로그아웃 페이지로 보내기 -> 세션만료
		response.sendRedirect(request.getContextPath()+"/LogoutController");
	}

}
