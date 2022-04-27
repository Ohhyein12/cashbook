package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정버튼으로 들어오면
		HttpSession session = request.getSession(); 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println("sessionMemberId(UpdateMemberController) :" + sessionMemberId);
		if(sessionMemberId == null) { // 로그인 상태가 아니라면 
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		//디버깅
		System.out.println("memberId(UpdateMemberController): " + sessionMemberId);
				
		MemberDao memberDao = new MemberDao();
			
		// 사용자의 개인정보 들고올 메서드 호출해서 member객체에 담기
		Member member = memberDao.selectMemberOne(sessionMemberId);
				
		request.setAttribute("member", member);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/UpdateMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩
		request.setCharacterEncoding("utf-8"); 
		
		// 요청값 불러오기
		String memberId = null;
		if(request.getParameter("memberId") != null &&  !"".equals(request.getParameter("memberId"))) {
			memberId = request.getParameter("memberId");
		}
		String memberName = null;
		if(request.getParameter("memberName") != null && !"".equals(request.getParameter("memberName"))) {
			memberName = request.getParameter("memberName");
		}
		String memberGender = null;
		if(request.getParameter("memberGender") != null && !"".equals(request.getParameter("memberGender") )) {
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
		String ChangeMemberPw = null;
		if(request.getParameter("ChangeMemberPw") != null && !"".equals(request.getParameter("ChangeMemberPw"))) {
			ChangeMemberPw = request.getParameter("ChangeMemberPw");
		}
		String CheckMemberPw = null;
		if(request.getParameter("CheckMemberPw") != null && !"".equals(request.getParameter("CheckMemberPw"))) {
			CheckMemberPw = request.getParameter("CheckMemberPw");
		}
		
		//디버깅
		System.out.println("memberId(UpdateMemberController):" + memberId);
		System.out.println("memberName(UpdateMemberController):" + memberName);
		System.out.println("memberGender(UpdateMemberController):" + memberGender);
		System.out.println("memberAddress(UpdateMemberController):" + memberAddress);
		System.out.println("memberPhone(UpdateMemberController):" + memberPhone);
		System.out.println("memberPw(UpdateMemberController):" + memberPw);
		System.out.println("ChangeMemberPw(UpdateMemberController):" + ChangeMemberPw);
		System.out.println("CheckMemberPw(UpdateMemberController):" + CheckMemberPw);
		
		MemberDao memberDao = new MemberDao();

		if(!ChangeMemberPw.equals(CheckMemberPw)) { // 수정할 비밀번호와 비밀번호 확인에 들어온 비밀번호가 같지않다면
			System.out.println("수정할 비밀번호와 비밀번호 확인의 비밀번호가 같지않습니다");
			response.sendRedirect(request.getContextPath()+"/UpdateMemberController?error=unequalpassword");
			return;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId",memberId);
		map.put("memberName",memberName);
		map.put("memberGender",memberGender);
		map.put("memberAddress",memberAddress);
		map.put("memberPhone",memberPhone);
		map.put("memberPw",memberPw);
		map.put("ChangeMemberPw",ChangeMemberPw);
		map.put("CheckMemberPw",CheckMemberPw);
		
	
		int row = memberDao.updateMember(map);
		
		if(row != 1) { // 행 수정 실패했을경우
			System.out.println("정보수정 실패");
			response.sendRedirect(request.getContextPath()+"/UpdateMemberController?error=UpdateMemberFail");
			return;
		} 
		
		//수정했으면 로그아웃 페이지로 보내기 -> 세션만료
		response.sendRedirect(request.getContextPath()+"/LogoutController");
	}

}
