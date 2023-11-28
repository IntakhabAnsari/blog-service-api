package com.upendra.blog.api.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

	private Integer postId;

	private String title;

	private String content;

	private String imageName;
	
	private long numberOfLikes;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();
	

}
