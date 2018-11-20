package com.example.demo.board.domain;

import org.springframework.stereotype.Repository;

public class FileVO {
	private int fno;
	private int cno;
	private String name;
	private String oriname;
	private String url;
	
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString() {
		return "fno: " + fno + ", cno: " + cno + ", name: " + name + ", oriname: " + oriname + ", url: " + url;
	}
}
