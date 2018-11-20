package com.example.demo.board.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.service.BoardService;

@Controller
public class BoardController {
	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService bs;
	
	@RequestMapping(value="/getUser/{user_id}")
	public String getUser(@PathVariable String user_id, Model model) throws Exception {
		model.addAttribute("userData", bs.getUser(user_id));
		model.addAttribute("userList", bs.getUserList());
		
		return "User/getUser";
	}
	
	@RequestMapping(value="/redirect")
	public String redirect() throws Exception {
		return "redirect";
	}
	
	@RequestMapping(value="/redirectProc")
	public String redirectProc() throws Exception{
		return "redirect:/getUser";
	}
	
}
