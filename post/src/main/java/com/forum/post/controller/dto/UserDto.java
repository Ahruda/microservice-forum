package com.forum.post.controller.dto;



public class UserDto {
	
	private Long id;
	
	private String name;
	
	private String userName;
	
	private String tags;
	
	
	public UserDto() {}
	
	public UserDto(Long id, String name, String userName, String tags) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


}
