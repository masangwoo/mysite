package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.MysiteVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		/*접근 제어*/
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");

        String name = (String) request.getParameter("name");
        String password = (String) request.getParameter("password");
        String gender = (String) request.getParameter("gender");
        System.out.println(name);
		
		new UserDao().update(authUser.getNo(),name, password, gender);
		if(!password.isBlank()) {
			authUser.setPassword(password);
		}
		authUser.setName(name);
		authUser.setGender(gender);
		//forwarding user/updateform
		MvcUtil.redirect(request.getContextPath(), request, response);
	}
}

