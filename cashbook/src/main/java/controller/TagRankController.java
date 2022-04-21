package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;


@WebServlet("/TagRankController")
public class TagRankController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashtagDao hashtagDao = new HashtagDao();
		
		//요청값 불러오기 
		String kind = null;
		if(request.getParameter("kind")!=null) { // 지출, 수입 중 하나를 선택했다면 값이 들어올 것
			kind = request.getParameter("kind");
		} 
		
		//디버깅
		System.out.println("kind(TagController) : " + kind);
		
		List<Map<String, Object>> list = null;
		
		if(request.getParameter("kind") == null) { // 수입, 지출을 누르지않았다면
			list = hashtagDao.selectTagRankList(); // 전체 항목 리스트 출력
		} else {
			list = hashtagDao.selectKindByList(kind); 
		}

		request.setAttribute("list", list);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/TagRankList.jsp").forward(request, response);
		
	}
}
