package com.upendra.blog.api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upendra.blog.api.dtos.PostDto;
import com.upendra.blog.api.services.FileService;
import com.upendra.blog.api.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Value("${project.image}")
	private String path;

	@Autowired
	private FileService fileService;

	@GetMapping
	public ResponseEntity<List<PostDto>> getPosts(Pageable pageable) {
		return ResponseEntity.ok(postService.getPosts(pageable));
	}

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<Void> savePost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		postService.savePost(postDto, userId, categoryId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(postService.getPostsByUser(userId));
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
	}

	@GetMapping("/search/{title}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String title) {
		return ResponseEntity.ok(postService.searchPostByTitle(title));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		return ResponseEntity.ok(postService.updatePost(postDto, postId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
		postService.deletePost(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<Void> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postDto = postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		postService.updatePost(postDto, postId);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
