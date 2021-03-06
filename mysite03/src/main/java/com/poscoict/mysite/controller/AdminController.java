package com.poscoict.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;


@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SiteService siteService;
	@Autowired
	private FileUploadService fileUploadService;
	

	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = siteService.view();
		model.addAttribute("siteVo",vo);
		return "admin/main";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String mainUpdate(@RequestParam(value="upload-file") MultipartFile multipartFile,
			SiteVo vo) {
		String url = fileUploadService.restore(multipartFile);	
		vo.setProfile(url);
		System.out.println(url);
		siteService.update(vo);
		return "redirect:/admin";
	}
	


	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}	
}