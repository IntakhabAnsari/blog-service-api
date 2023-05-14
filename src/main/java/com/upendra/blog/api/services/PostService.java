package com.upendra.blog.api.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.upendra.blog.api.dtos.PostDto;

public interface PostService {

	List<PostDto> getPosts(Pageable pageable);

	PostDto getPostById(Integer id);

	void savePost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer id);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> searchPostByTitle(String title);

}
