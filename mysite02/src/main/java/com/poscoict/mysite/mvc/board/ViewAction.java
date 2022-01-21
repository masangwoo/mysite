package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewAction implements Action {
	//private static final String COOKIE_NAME = "visitcount";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		String strNo = request.getParameter("no");
		//BoardVo authUser = (BoardVo)session.getAttribute("authUser");
      
	
		int visitCount = 0;
		BoardVo vo =  new BoardDao().view(Integer.parseInt(strNo));
		BoardVo vo1 = new BoardVo();
		request.setAttribute("vo", vo);
		
		
		//쿠키읽기
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(vo.getNo().toString().equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue());
					break;
				}
			}
		}
		
		
		visitCount++;
		//쿠키쓰기(굽기)
		Cookie cookie =  new Cookie(vo.getNo() + "", String.valueOf(visitCount));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(60); //1day
		response.addCookie(cookie);
		
		if(visitCount <= 1) {
			new BoardDao().hitUp(Integer.parseInt(strNo));
		}
		
        int no = Integer.parseInt(request.getParameter("no"));

        vo1 = dao.view(no);
        request.setAttribute("vo", vo1);
        MvcUtil.forward("/board/view", request, response);

	}

}
