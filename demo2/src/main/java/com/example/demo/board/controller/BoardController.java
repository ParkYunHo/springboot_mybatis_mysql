package com.example.demo.board.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
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
//        String fileUrl = "C:\\dev\\git\\springboot_mybatis_mysql\\demo2\\src\\main\\webapp\\WEB-INF\\views\\uploadFiles\\";    // not home
        String fileUrl = "C:\\Users\\박윤호.DESKTOP-FUNI6TP\\Desktop\\Spring\\dev\\git\\springboot_mybatis_mysql\\demo2\\src\\main\\webapp\\WEB-INF\\views\\uploadFiles\\";
        
        File dsFile; 
        String dsFileName;
	        
        do { 
        	dsFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileExtension; 
            dsFile = new File(fileUrl + dsFileName); 
        } while (dsFile.exists()); 
        
        // Img파일을 Server 내에 저장하는 부
        dsFile.getParentFile().mkdirs(); 
        files.transferTo(dsFile);
        
        // file정보를 VO class에 담아서 DB에 저장하는 부분  
        vo.setCno(Integer.parseInt(request.getParameter("category")));
        vo.setName(dsFileName);
        vo.setOriname(fileName);
        vo.setUrl(fileUrl);
        bs.setFile(vo);
		
		 return "redirect:/imgUpload";
	}
	
	@RequestMapping(value="/getFile/{fno}")
	public void getFile(@PathVariable int fno, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
        FileVO fileVO = bs.getFile(fno);
        
        //파일 업로드된 경로 
        try{
            String fileUrl = fileVO.getUrl();
            fileUrl += "/";
            String savePath = fileUrl;
            String fileName = fileVO.getName();
            
            //실제 내보낼 파일명 
            String oriFileName = fileVO.getOriname();
            InputStream in = null;
            OutputStream os = null;
            File file = null;
            boolean skip = false;
            String client = "";
            
            //파일을 읽어 스트림에 담기  
            try{
                file = new File(savePath, fileName);
                in = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
                skip = true;
            }
            
            client = request.getHeader("User-Agent");
            
            //파일 다운로드 헤더 지정 
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "JSP Generated Data");
            
            if (!skip) {
                // IE
                if (client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    // IE 11 이상.
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(oriFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
                response.setHeader("Content-Length", "" + file.length());
                os = response.getOutputStream();
                byte b[] = new byte[(int) file.length()];
                int leng = 0;
                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
            }
            in.close();
            os.close();
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
	}
	
	@RequestMapping(value="/ajaxGet")
	public @ResponseBody Map<String, List<Map<String, String>>> ajaxGet() throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("col1", "1");
		m1.put("col2", "1");
		list.add(m1);
		
		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("col1", "2");
		m2.put("col2", "2");
		list.add(m2);
		
		Map<String, List<Map<String, String>>> res = new HashMap<String, List<Map<String, String>>>();
		res.put("item", list);
		
		return res;
	}
	
	@RequestMapping(value="/ajaxSet")
	public String ajaxSet(@RequestParam("file") MultipartFile files, HttpServletResponse response, HttpServletRequest request) throws Exception {
		FileVO vo = new FileVO();
		
		String fileName = files.getOriginalFilename(); 
        String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        // uploadFiles folder 오른쪽 클릭 Properties > Location (끝에 \\ 붙일것!)
//        String fileUrl = "C:\\dev\\git\\springboot_mybatis_mysql\\demo2\\src\\main\\webapp\\WEB-INF\\views\\uploadFiles\\";    // not home
        String fileUrl = "C:\\Users\\박윤호.DESKTOP-FUNI6TP\\Desktop\\Spring\\dev\\git\\springboot_mybatis_mysql\\demo2\\src\\main\\webapp\\WEB-INF\\views\\uploadFiles\\";
        
        File dsFile; 
        String dsFileName;
	        
        do { 
        	dsFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileExtension; 
            dsFile = new File(fileUrl + dsFileName); 
        } while (dsFile.exists()); 
        
        // Img파일을 Server 내에 저장하는 부
        dsFile.getParentFile().mkdirs(); 
        files.transferTo(dsFile);
        
        // file정보를 VO class에 담아서 DB에 저장하는 부분  
        vo.setCno(Integer.parseInt(request.getParameter("category")));
        vo.setName(dsFileName);
        vo.setOriname(fileName);
        vo.setUrl(fileUrl);
        bs.setFile(vo);
		
    	return "Success";
	}

}
