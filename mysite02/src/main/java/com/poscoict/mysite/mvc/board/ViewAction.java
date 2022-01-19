package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewAction implements Action {
	//private static final String COOKIE_NAME = "visitcount";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();
		HttpSession session = request.getSession();
		//BoardVo authUser = (BoardVo)session.getAttribute("authUser");
      
        int no = Integer.parseInt(request.getParameter("no"));

        vo = dao.view(no);
        request.setAttribute("vo", vo);
        MvcUtil.forward("/board/view", request, response);

	}

}
