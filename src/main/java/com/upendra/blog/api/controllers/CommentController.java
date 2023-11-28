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

import com.upendra.blog.api.dtos.CommentDto;
import com.upendra.blog.api.services.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comments")
@Slf4j
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}")
	public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		log.info("Create Comment API",commentDto);
		commentService.createComment(commentDto, postId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
		commentService.deleteComment(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
