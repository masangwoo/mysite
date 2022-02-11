package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo view(){
		return siteRepository.view();
	}
	
	public Boolean update(SiteVo vo) {
		return siteRepository.update(vo.getTitle(), vo.getWelcome(), vo.getDescription(), vo.getProfile());
	}

}
