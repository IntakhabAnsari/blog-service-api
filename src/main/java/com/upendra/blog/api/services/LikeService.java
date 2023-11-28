package com.upendra.blog.api.services;

import java.util.List;

import com.upendra.blog.api.dtos.LikeDto;

public interface LikeService {

	void createLike(LikeDto commentDto);

	List<LikeDto> getLikeByPost(Integer userId);
	
	void deleteLike(long id);

}
