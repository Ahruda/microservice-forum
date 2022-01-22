package com.forum.user.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findById(Long id);

}
