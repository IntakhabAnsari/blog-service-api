package com.upendra.blog.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private int id;

	private String content;
	
	private int userId;
	
	private int postId;

}
