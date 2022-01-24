package com.forum.comment.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.comment.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> findByIdPost(Long id);

}

