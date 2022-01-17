package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.MysiteDao;
import com.poscoict.mysite.vo.MysiteVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MysiteVo vo = new MysiteVo();
		MysiteDao dao = new MysiteDao();
		List<MysiteVo> list = new ArrayList<>();
		list = dao.findAll();
		request.setAttribute("list", list);
		MvcUtil.forward("/guestbook/index", request, response);
	}

}