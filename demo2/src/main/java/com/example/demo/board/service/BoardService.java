package com.example.demo.board.service;

import javax.annotation.Resource;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.mapper.BoardMapper;
import com.example.demo.board.domain.*;

@Service("com.example.demo.board.service.BoardService")
public class BoardService {
	@Resource(name="com.example.demo.board.mapper.BoardMapper")
	BoardMapper bm;
	
	public BoardVO getUser(String user_id) throws Exception {
		return bm.getUser(user_id);
	}
	
	public List<BoardVO> getUserList() throws Exception {
		return bm.getUserList();
	}
	
	public int setFile(FileVO vo) throws Exception{
		return bm.setFile(vo);
	}
	
	public List<FileCategoryVO> getCategory() throws Exception{
		return bm.getCategory();
	}
	
	public FileVO getFile(int fno) throws Exception{
		return bm.getFile(fno);
	}
 }
