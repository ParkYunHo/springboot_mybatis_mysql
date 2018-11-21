package com.example.demo.board.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.service.BoardService;
import com.example.demo.board.domain.*;

@Controller
public class BoardController {
	@Resource(name = "com.example.demo.board.service.BoardService")
	BoardService bs;

	@RequestMapping(value = "/getUser/{user_id}")
	public String getUser(@PathVariable String user_id, Model model) throws Exception {
		model.addAttribute("userList", bs.getUserList());
		model.addAttribute("userData", bs.getUser(user_id));

		return "User/getUser";
	}

	@RequestMapping(value = "/redirect")
	public String redirect() throws Exception {
		return "redirect";
	}

	@RequestMapping(value = "/redirectProc")
	public String redirectProc() throws Exception {
		return "redirect:/test";
	}

	@RequestMapping(value = "/imgUpload")
	public String imgUpload(Model model) throws Exception {
		model.addAttribute("categoryList", bs.getCategory());
		
		return "imgUpload";
	}

	@RequestMapping(value="/imgUploadProc")
	public String imgUploadProc(HttpServletRequest request, @RequestParam("file") MultipartFile files) throws Exception {
		FileVO vo = new FileVO();
		
		String fileName = files.getOriginalFilename(); 
        String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        // uploadFiles folder 오른쪽 클릭 Properties > Location (끝에 \\ 붙일것!)
        String fileUrl = "C:\\dev\\git\\springboot_mybatis_mysql\\demo2\\src\\main\\webapp\\WEB-INF\\views\\uploadFiles\\";
        
        File dsFile; 
        String dsFileName;
	        
        do { 
        	dsFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileExtension; 
            dsFile = new File(fileUrl + dsFileName); 
        } while (dsFile.exists()); 
        
        dsFile.getParentFile().mkdirs(); 
        files.transferTo(dsFile);
        
        vo.setCno(Integer.parseInt(request.getParameter("category")));
        vo.setName(dsFileName);
        vo.setOriname(fileName);
        vo.setUrl(fileUrl);
        bs.setFile(vo);
		
		return "redirect:/imgUpload";
	}

}
