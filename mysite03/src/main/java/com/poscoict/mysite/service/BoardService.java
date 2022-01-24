package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public boolean addContents(BoardVo vo) {
		if(vo.getGroupNo() != null) {
			return boardRepository.insert(vo);
		}else {
			return boardRepository.insert(vo.getTitle(), vo.getContents(), vo.getUserNo());
		}
	
	}

	
	//view에서 1개 게시물보기
	public BoardVo getContents(Long no){
		return boardRepository.view(no);
	}
	
	//글 수정 전
	public BoardVo getContents(Long no, Long userNo){
		return null;
	}
	
	//글 수정
	public Boolean updateContents(BoardVo vo) {
		return boardRepository.update(vo.getNo(), vo.getTitle(), vo.getContents());
	}
	
	//글 삭제
	public Boolean deleteContents(Long no) {
		return boardRepository.delete(no);
	}
	
	//글 리스트(찾기 결과)
	public Map<String, Object> getContentsList(Integer p, String kwd) {
	      Map<String, Object> map = new HashMap<>();
	      List<BoardVo> list = null;
	      
	      int cPage;  //인트로바꾼거
	      int cnt = 0;
	      int pcnt = 0;  //페이지총개수
	      
	      if (p == null) {
	           cPage = 1;
	       }else {
	           cPage = p;
	       }
	      
	      if(kwd == null ) {

	         list =  boardRepository.findAll((cPage - 1) * 5);
	         cnt = boardRepository.count();   //총개수
	      }else {

	         list =  boardRepository.findAll((cPage - 1) * 5 ,kwd);
	         cnt = boardRepository.count(kwd);   //총개수
	         
	      }
	      
	   
	      if(cnt % 5 == 0) {
	         pcnt = cnt / 5;
	      } else {
	         pcnt  = (cnt / 5) + 1;
	      }
	      
	      
	      if(kwd == null || kwd.length()==0) {

	      }else {

	         if(cPage > pcnt) {
	            cPage = 1;
	         }
	         
	      }
	      
	      map.put("list", list);
	      map.put("p", cPage);
	      map.put("pcnt", pcnt);
	      map.put("cnt", cnt);
	      map.put("kwd", kwd);
	      
	      
	      return map;
	   }
	
	public Map<String,Object> getContentsList(int currentPage) {
		Map<String,Object> map = new HashMap<>();
	
		
		 String p = Integer.toString(currentPage);//몇번페이지눌러사나  // 1 
	      int cPage;  //인트로바꾼거
	      
	      if (p == null || p.length() == 0) {
	           cPage = 1;
	       }else {
	           cPage = Integer.parseInt(p);
	       }
	      
	      
	      List<BoardVo> list =  boardRepository.findAll((cPage - 1) * 5);
	      int cnt = boardRepository.count();   //총개수
	      int pcnt = 0;  //페이지총개수
	      if(cnt % 5 == 0) {
	         pcnt = cnt / 5;
	      } else {
	         pcnt  = (cnt / 5) + 1;
	      }
	      
	      
	      map.put("pcnt", pcnt);
	      map.put("list", list);
	      map.put("p", Integer.valueOf(cPage));
	      map.put("cnt", Integer.valueOf(cnt));

		
		return map;
	}
	
	//첫 리스트
	/*public List<BoardVo> findAll(){
		List<BoardVo> list = boardRepository.findAll();
		return list;
	}*/
	

}
