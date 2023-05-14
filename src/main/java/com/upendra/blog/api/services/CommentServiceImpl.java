package com.upendra.blog.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upendra.blog.api.dtos.CommentDto;
import com.upendra.blog.api.entities.Comment;
import com.upendra.blog.api.entities.Post;
import com.upendra.blog.api.exceptions.ResourceNotFoundException;
import com.upendra.blog.api.repositories.CommentRepository;
import com.upendra.blog.api.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Integer id) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		commentRepository.delete(comment);
	}

}
