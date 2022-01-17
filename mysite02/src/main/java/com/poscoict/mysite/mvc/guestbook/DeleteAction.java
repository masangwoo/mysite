package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.MysiteDao;
import com.poscoict.mysite.vo.MysiteVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        int no = Integer.parseInt(request.getParameter("no")) ;
        String password = request.getParameter("password");
        System.out.println(no);
        System.out.println(password);
        
        MysiteVo vo = new MysiteVo();
        vo.setNo(no);
        vo.setPassword(password);
		
        //new MysiteDao().delete(vo);
        System.out.println( new MysiteDao().delete(vo));
		MvcUtil.redirect(request.getContextPath()+"/guestbook", request, response);

	}

}
