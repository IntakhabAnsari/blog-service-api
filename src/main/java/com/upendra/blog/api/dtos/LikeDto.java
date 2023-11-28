package com.upendra.blog.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikeDto {

	
	private int userId;
	
	private int postId;
}
