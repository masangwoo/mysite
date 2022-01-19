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
		BoardDao dao = new BoardDao();
		
		
		if(request.getParameter("groupNo")!=null) {
			BoardVo vo = new BoardVo();
	        String title = request.getParameter("title");
	        String contents = request.getParameter("content");
	        int userNo = Integer.parseInt(request.getParameter("userNo"));
	        int orderNo = Integer.parseInt(request.getParameter("orderNo"));
	        int groupNo = Integer.parseInt(request.getParameter("groupNo"));
	        int depth = Integer.parseInt(request.getParameter("depth"));
	        dao.update(orderNo, groupNo);
	        
	        vo.setTitle(title);
	        vo.setContents(contents);
	        vo.setUserNo((long)userNo);
	        vo.setOrderNo(orderNo);
	        vo.setGroupNo(groupNo);
	        vo.setDepth(depth);

	                		
	        dao.insert(vo);
		}else {
	        String title = request.getParameter("title");
	        String contents = request.getParameter("content");
	        int userNo = Integer.parseInt(request.getParameter("userNo"));
	
	                		
	        dao.insert(title, contents, (long)userNo);
		}
       // MvcUtil.forward("/board/write", request, response);
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
		

	}

}
