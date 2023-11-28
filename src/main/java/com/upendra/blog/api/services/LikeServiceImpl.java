package com.upendra.blog.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.upendra.blog.api.dtos.LikeDto;
import com.upendra.blog.api.entities.Comment;
import com.upendra.blog.api.entities.Like;
import com.upendra.blog.api.entities.Post;
import com.upendra.blog.api.entities.User;
import com.upendra.blog.api.exceptions.ResourceNotFoundException;
import com.upendra.blog.api.repositories.LikeRepository;
import com.upendra.blog.api.repositories.PostRepository;
import com.upendra.blog.api.repositories.UserRepository;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private LikeRepository likeRepository;

	@Override
	@Transactional
	public void createLike(LikeDto likeDto) {
		Post post = postRepository.findById(likeDto.getPostId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", likeDto.getPostId()));
		User user = userRepository.findById(likeDto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", likeDto.getUserId()));
		Like like = new Like();
		like.setPost(post);
		like.setUser(user);
		likeRepository.save(like);
		long likecount = 1;
		if (post.getNumberOfLikes() == 0) {
			post.setNumberOfLikes(likecount);
		} else {
			post.setNumberOfLikes(post.getNumberOfLikes() + likecount);
		}
	}

	@Override
	@Transactional
	public void deleteLike(long id) {
		Like like = likeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		likeRepository.delete(like);
		long likecount = 1;
		if (like.getPost().getNumberOfLikes() != 0) {
			like.getPost().setNumberOfLikes(like.getPost().getNumberOfLikes() - likecount);
		}
	}

	@Override
	public List<LikeDto> getLikeByPost(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
