package com.poscoict.mysite.dao.test;

import java.util.List;

import com.poscoict.mysite.dao.EmaillistDao;
import com.poscoict.mysite.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		//testInsert();
		testFindAll();
	}
	
	
	
	private static void testInsert() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("안");
		vo.setLastName("대혁");
		vo.setEmail("kickscar@gmail.com");
		
		boolean result = new EmaillistDao().insert(vo);
		System.out.println(result ? "success" : "fail");
	}



	public static void testFindAll() {
		List <EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVo vo:list) {
			System.out.println(vo);
			}
		//assertEqual(2,list.size());
	}

}
