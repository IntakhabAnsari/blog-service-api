package com.upendra.blog.api.services;

import com.upendra.blog.api.dtos.CommentDto;

public interface CommentService {

	void createComment(CommentDto commentDto, Integer PostId);

	void deleteComment(Integer id);

}
