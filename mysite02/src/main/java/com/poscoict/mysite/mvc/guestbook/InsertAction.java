package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.MysiteDao;
import com.poscoict.mysite.vo.MysiteVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String message = request.getParameter("message");
                   	System.out.println(name);
        MysiteVo vo = new MysiteVo();
        vo.setName(name);
        vo.setPassword(password);
        vo.setMessage(message);
                		
        new MysiteDao().insert(vo);
		
		MvcUtil.redirect(request.getContextPath()+"/guestbook", request, response);
		
	}

}
