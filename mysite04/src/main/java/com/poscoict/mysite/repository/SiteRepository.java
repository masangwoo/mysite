package com.poscoict.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo view() {
		return sqlSession.selectOne("site.view");
	}
	
	public boolean update(String title, String welcome, String description, String profile) {
		SiteVo vo = new SiteVo();

		vo.setTitle(title);
		vo.setWelcome(welcome);
		vo.setDescription(description);
		vo.setProfile(profile);


		int count = sqlSession.update("site.update",vo);
		return count==1;
	}

}
