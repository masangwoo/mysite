package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String p = request.getParameter("p");  //몇번페이지눌러사나  // 1
      HttpSession session = request.getSession();
      UserVo authUser = (UserVo)session.getAttribute("authUser");

      
      int cPage;  //인트로바꾼거
      
      if (p == null || p.length() == 0) {
           cPage = 1;
       }else {
           cPage = Integer.parseInt(p);
       }
      
      
      List<BoardVo> list =  new BoardDao().findAll((cPage - 1) * 5);
      int cnt = new BoardDao().count();   //총개수
      int pcnt = 0;  //페이지총개수
      if(cnt % 5 == 0) {
         pcnt = cnt / 5;
      } else {
         pcnt  = (cnt / 5) + 1;
      }
      
      
      request.setAttribute("list", list);
      request.setAttribute("vo", authUser);
      request.setAttribute("pcnt", pcnt);
      System.out.println(pcnt);
      request.setAttribute("p", Integer.valueOf(cPage));
      request.setAttribute("cnt", Integer.valueOf(cnt));

      
      MvcUtil.forward("board/list", request, response);
   }
}