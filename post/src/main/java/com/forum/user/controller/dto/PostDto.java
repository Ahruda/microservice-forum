package com.forum.user.controller.dto;

import java.sql.Date;

public class PostDto {
	
	private Long id;
	
	private UserDto user;
	
	private Date date;
	
	private String topic;
	
	private String body;
	

	public PostDto(Long id, UserDto user, Date date, String topic, String body) {
		this.id = id;
		this.user = user;
		this.date = date;
		this.topic = topic;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
