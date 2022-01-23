package com.forum.post.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.forum.post.controller.dto.PostDto;
import com.forum.post.model.Post;
import com.forum.post.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody Post newPost, UriComponentsBuilder uriBuilder ) {
		PostDto post = postService.createPost(newPost);
		URI uri =  uriBuilder.path("post/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(uri).body(post);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		Post post = postService.getPostById(id);
		
		return ResponseEntity.ok(post);		
	}

	@GetMapping
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> posts = postService.getAllPosts();
		
		return ResponseEntity.ok(posts);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody Post post) {
		Post postUpdated = postService.updatePost(id, post);
		
		return ResponseEntity.ok(postUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		postService.deletePost(id);
		return ResponseEntity.ok("Post of id: " + id + " deleted successfully");
	}
	
	
	
}
