package com.example.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.board.mapper.BoardMapper;

@Controller
public class Test {
	@Resource(name="com.example.demo.board.mapper.BoardMapper")
	BoardMapper bm;
	
	@RequestMapping("/test")
	public String test() throws Exception {
		System.out.println(bm.countUser());
		
		return "test";
	}
}
