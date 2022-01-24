package com.forum.comment.controller;

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

import com.forum.comment.controller.dto.CommentDto;
import com.forum.comment.model.Comment;
import com.forum.comment.service.CommentService;
 
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping
	public ResponseEntity<Comment> createComment(@RequestBody Comment newComment, UriComponentsBuilder uriBuilder ) {
		Comment commentary = commentService.createComment(newComment);
		URI uri =  uriBuilder.path("commentary/{id}").buildAndExpand(commentary.getId()).toUri();
		
		return ResponseEntity.created(uri).body(commentary);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		Comment commentary = commentService.getCommentById(id);
		
		return ResponseEntity.ok(commentary);		
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByIdPost(@PathVariable Long id) {
		List<CommentDto> comments = commentService.getAllCommentsByIdPost(id);
		
		return ResponseEntity.ok(comments);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable Long id,@RequestBody Comment commentary) {
		Comment commentaryUpdated = commentService.updateComment(id, commentary);
		
		return ResponseEntity.ok(commentaryUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.ok("Comment of id " + id + " deleted successfully");
	}
	
	 
	
}
