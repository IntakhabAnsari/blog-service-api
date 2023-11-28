package com.upendra.blog.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upendra.blog.api.entities.Category;
import com.upendra.blog.api.entities.Post;
import com.upendra.blog.api.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	@Query("select p from Post p where p.title like :title")
	List<Post> searchByTitle(String title);	

}