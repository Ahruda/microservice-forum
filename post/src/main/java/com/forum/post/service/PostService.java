package com.forum.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.forum.post.controller.dto.CommentDto;
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
	
	public PostDto getPostById(Long id) {

		Optional<Post> postOpt = postRepository.findById(id);
		
		Post post = postOpt.get(); 
		
		RestTemplate client = new RestTemplate();
		
		ResponseEntity<UserDto> exchangeUser = 
				client.exchange("http://localhost:8080/user/" + post.getIdUser(), HttpMethod.GET, null, UserDto.class);
		
		UserDto user = exchangeUser.getBody();
		
		ResponseEntity<List<CommentDto>> exchangeComments = 
				client.exchange("http://localhost:8082/comment/post/" + post.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDto>>(){});

		List<CommentDto> comments = exchangeComments.getBody();

		PostDto postDto = new PostDto(post, user, comments);
		
		return postDto;
	}
	
	public List<PostDto> getAllPosts() {
		Iterable<Post> posts = postRepository.findAll();
		List<PostDto> postsDto = new ArrayList<>();
		
		RestTemplate client = new RestTemplate();

		Consumer<Post> consumerPost = post -> {
			
			ResponseEntity<UserDto> exchange = 
					client.exchange("http://localhost:8080/user/" + post.getIdUser(), HttpMethod.GET, null, UserDto.class);
			
			UserDto user = exchange.getBody();
			
			ResponseEntity<List<CommentDto>> exchangeComments = 
					client.exchange("http://localhost:8082/comment/post/" + post.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDto>>(){});

			List<CommentDto> comments = exchangeComments.getBody();
			
			postsDto.add(new PostDto(post, user, comments));
			
		};
		
		posts.forEach(consumerPost);
		
		return postsDto;
	}
	
	@Transactional
	public Post updatePost(Long id, Post post) {
		Post postUpdated = postRepository.findById(id).get();
		postUpdated.setBody(post.getBody());
		postUpdated.setTopic(post.getTopic()); 		
		
		return postUpdated;
	}
	
	@Transactional
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
