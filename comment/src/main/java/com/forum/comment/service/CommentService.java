package com.forum.comment.service;

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

import com.forum.comment.model.Comment;
import com.forum.comment.controller.dto.CommentDto;
import com.forum.comment.controller.dto.UserDto;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment getCommentById(Long id) {
		Optional<Comment> comment;
		
		comment = commentRepository.findById(id);
		return comment.get();
			 
	}
	
	public List<CommentDto> getAllCommentsByIdPost(Long id) {
		List<Comment> comments = commentRepository.findByIdPost(id);
		List<CommentDto> commentsDto = new ArrayList<>();
		
		RestTemplate client = new RestTemplate();
		
		Consumer<Comment> consumerComments = comment -> {
			
			ResponseEntity<UserDto> exchange = 
					client.exchange("http://localhost:8080/user/" + comment.getIdUser(), HttpMethod.GET, null, UserDto.class);
			
			UserDto userComment = exchange.getBody();
			
			commentsDto.add(new CommentDto(comment.getId(),comment.getDate(), comment.getBody(), userComment));
			
		};
		
		comments.forEach(consumerComments);
		
		return commentsDto;
	}
	
	@Transactional
	public Comment createComment(Comment comment) {
		commentRepository.save(comment);
		return comment;
	}

	@Transactional
	public Comment updateComment(Long id, Comment comment) {
		Comment commentaryUpdated = getCommentById(id);
		commentaryUpdated.setBody(comment.getBody());
				
		return commentaryUpdated;
	}
	
	@Transactional
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
}
