package com.forum.comment.controller.dto;

import java.sql.Date;


public class CommentDto {

	private Long id;
	private Date date;
	private String body;
	private UserDto user;
	
	public CommentDto() {}
	
	public CommentDto(Long id, Date date, String body, UserDto user) {
		super();
		this.id = id;
		this.date = date;
		this.body = body;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
