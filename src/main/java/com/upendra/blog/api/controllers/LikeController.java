package com.upendra.blog.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.upendra.blog.api.dtos.LikeDto;
import com.upendra.blog.api.services.LikeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/like")
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	@PostMapping
	public ResponseEntity<Void> createLike(@RequestBody LikeDto likeDto) {
		log.info("Create Like API",likeDto);
		likeService.createLike(likeDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable long id) {
		likeService.deleteLike(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
