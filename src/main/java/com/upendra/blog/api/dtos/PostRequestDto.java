package com.upendra.blog.api.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

	private Integer postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;


}
