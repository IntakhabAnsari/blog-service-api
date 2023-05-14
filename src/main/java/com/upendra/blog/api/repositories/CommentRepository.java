package com.upendra.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upendra.blog.api.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
