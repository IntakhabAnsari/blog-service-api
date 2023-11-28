package com.upendra.blog.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upendra.blog.api.dtos.PostDto;
import com.upendra.blog.api.dtos.PostRequestDto;
import com.upendra.blog.api.entities.Category;
import com.upendra.blog.api.entities.Post;
import com.upendra.blog.api.entities.User;
import com.upendra.blog.api.exceptions.ResourceNotFoundException;
import com.upendra.blog.api.repositories.CategoryRepository;
import com.upendra.blog.api.repositories.PostRepository;
import com.upendra.blog.api.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void savePost(PostRequestDto postRequestDto, Integer userId, Integer categoryId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", categoryId));
		Post post = modelMapper.map(postRequestDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		postRepository.save(post);
	}

	@Override
	public List<PostDto> getPosts(Pageable pageable) {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	    Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

        Category category = this.categoryRepository.findById(postDto.getCategory().getCategoryId()).get();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);

        Post updatedPost = this.postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		postRepository.delete(post);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
		List<Post> posts = postRepository.findByUser(user);
		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", categoryId));

		List<Post> posts = postRepository.findByCategory(category);
		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPostByTitle(String title) {
		List<Post> posts = postRepository.searchByTitle("%" + title + "%");
		return posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
