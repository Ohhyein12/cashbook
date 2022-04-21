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


@WebServlet("/DayController")
public class DayController extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청값 불러오기
		String beginDate = null;
		String lastDate = null;

		if(request.getParameter("beginDate")!=null && request.getParameter("lastDate")!=null) {
			beginDate = request.getParameter("beginDate");
			lastDate = request.getParameter("lastDate");
		}
		
		HashtagDao hashtagDao = new HashtagDao();
		
		// 검색리스트 구현 메서드 호출해서 리스트에 할당
		List<Map<String, Object>> list = hashtagDao.selectDateByList(beginDate, lastDate);
		
		request.setAttribute("list", list);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/TagDateList.jsp").forward(request, response);
		
	}

}
