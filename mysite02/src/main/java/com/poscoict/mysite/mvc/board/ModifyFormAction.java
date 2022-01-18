package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();

        int no = Integer.parseInt(request.getParameter("no"));

        vo = dao.findByNo((long)no);
        request.setAttribute("vo", vo);
        MvcUtil.forward("/board/modify", request, response);
		//MvcUtil.redirect(request.getContextPath()+"/board", request, response);

	}

}
