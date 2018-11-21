package com.example.demo.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.example.demo.board.domain.*;

import java.util.*;

@Repository("com.example.demo.board.mapper.BoardMapper")
public interface BoardMapper {
	public int countUser() throws Exception;
	public BoardVO getUser(String user_id) throws Exception;
	public List<BoardVO> getUserList() throws Exception;
	public int setFile(FileVO vo) throws Exception;
	public List<FileCategoryVO> getCategory() throws Exception;
	public FileVO getFile(int fno) throws Exception; 
}
