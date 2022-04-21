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


@WebServlet("/TagByListController")
public class TagByListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashtagDao hashtagDao = new HashtagDao();
		
		//요청값 불러오기
		String tag = null;
		if(request.getParameter("tag")!=null) { // tag값이 null이 아니라면
			tag = request.getParameter("tag");
		}
		
		//디버깅
		System.out.println("tag(TagByListController) : " + tag);
		
		List<Map<String, Object>> list = hashtagDao.selectTagByList(tag);
		
		request.setAttribute("list", list);
		
		//뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/TagByList.jsp").forward(request, response);
	}

}
