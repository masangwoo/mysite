package com.poscoict.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")	
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping("")	
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute("list",list);
		return "/guestbook/index";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	/*public String add(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}*/
	
	@RequestMapping(value = "/deleteform", method = RequestMethod.GET)
	public String deleteform(Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}


	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(long no, String password) {
		guestbookService.deleteMessage(no, password);		
		return "redirect:/guestbook";
	}

}