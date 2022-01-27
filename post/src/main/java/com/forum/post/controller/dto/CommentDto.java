package com.forum.post.controller.dto;

import java.time.LocalDate;

public class CommentDto {

	private Long id;
	private LocalDate date;
	private String body;
	private UserDto user;
	
	public CommentDto() {}
	
	public CommentDto(Long id, LocalDate date, String body, UserDto user) {
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
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
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
