package com.forum.post.controller.dto;



import java.sql.Date;
import java.util.List;

import com.forum.post.model.Post;

public class PostDto {
	
	private Long id;
	
	private UserDto user;
	
	private Date date;
	
	private String topic;
	
	private String body;
	
	private List<CommentDto> comments;
	
	public PostDto(Post post, UserDto user) {
		this.id = post.getId();
		this.date = post.getDate();
		this.topic = post.getTopic();
		this.body = post.getBody();
		
		this.user = user;
	}


	public PostDto(Post post, UserDto user,List<CommentDto> comments) {
		this.id = post.getId();
		this.date = post.getDate();
		this.topic = post.getTopic();
		this.body = post.getBody();
		
		this.user = user;
		this.comments = comments;
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

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	
}
