package com.upendra.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upendra.blog.api.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
