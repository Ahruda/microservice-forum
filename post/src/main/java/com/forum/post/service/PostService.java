package com.forum.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.forum.post.controller.dto.PostDto;
import com.forum.post.controller.dto.UserDto;
import com.forum.post.model.Post;


@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Transactional
	public PostDto createPost(Post post) {
		
		RestTemplate client = new RestTemplate();
		ResponseEntity<UserDto> exchange = 
				client.exchange("http://localhost:8080/user/" + post.getIdUser(), HttpMethod.GET, null, UserDto.class);
		
		UserDto user = exchange.getBody();

		postRepository.save(post);
		
		PostDto postDto = new PostDto(post, user);
		
		return postDto;
	}
	
	public Post getPostById(Long id) {
		Optional<Post> post = postRepository.findById(id);
		
		return post.get(); 
	}
	
	public List<PostDto> getAllPosts() {
		Iterable<Post> posts = postRepository.findAll();
		List<PostDto> postsDto = new ArrayList<>();
		
		Consumer<Post> consumerPost = post -> {
			
			RestTemplate client = new RestTemplate();
			ResponseEntity<UserDto> exchange = 
					client.exchange("http://localhost:8080/user/" + post.getIdUser(), HttpMethod.GET, null, UserDto.class);
			
			UserDto user = exchange.getBody();
			
			postsDto.add(new PostDto(post, user));
			
		};
		
		posts.forEach(consumerPost);
		
		return postsDto;
	}
	
	@Transactional
	public Post updatePost(Long id, Post post) {
		Post postUpdated = getPostById(id);
		postUpdated.setBody(post.getBody());
		postUpdated.setTopic(post.getTopic()); 		
		
		return postUpdated;
	}
	
	@Transactional
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
