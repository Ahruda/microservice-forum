package com.forum.post.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.post.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>{

}
