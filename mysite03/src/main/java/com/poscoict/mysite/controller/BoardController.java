package com.poscoict.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	   public String index(HttpSession session, 
				@RequestParam(value = "p", defaultValue = "1") Integer p,
				@RequestParam(value = "kwd", required = false, defaultValue = "") String kwd, Model model) {
			Map<String, Object> map = null;
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (kwd.equals(null)) {
				map = boardService.getContentsList(p);

			} else {
				map = boardService.getContentsList(p, kwd);
			}
				model.addAttribute("map", map);
				model.addAttribute("authUser", authUser);
			
			return "board/list";
	   }
	
	@RequestMapping("/writeform")
	public String writeform(BoardVo vo,Model model) {
		model.addAttribute("vo", vo);
		return "/board/write";
	}
	
	@RequestMapping(value="/replyform", method = RequestMethod.GET)
	public String writeform(String no, Model model) {
		BoardVo vo = boardService.getContents(Long.parseLong(no));
		//System.out.println(vo);
		model.addAttribute("vo",vo);
		return "/board/write";
	}
	
	
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(BoardVo vo) {
		
		boardService.addContents(vo);
		return "redirect:/board";
	}
	

	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo",vo);
		return "/board/view";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(long no) {
		boardService.deleteContents(no);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/updateform", method = RequestMethod.GET)
	public String updateform(long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo",vo);
		return "/board/modify";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(BoardVo vo) {	
		boardService.updateContents(vo);
		return "redirect:/board";
	}
	
/*	@RequestMapping(value="/replyform", method = RequestMethod.GET)
	public String replyform(long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo",vo);
		return "/board/write";
	}*/
	
}
