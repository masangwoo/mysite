package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();
        String title = request.getParameter("title");
        String contents = request.getParameter("content");
        int userNo = Integer.parseInt(request.getParameter("userNo"));

        vo.setTitle(title);
        vo.setContents(contents);
        vo.setUserNo((long)userNo);
                		
        dao.insert(vo);
        
       // MvcUtil.forward("/board/write", request, response);
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
		

	}

}
