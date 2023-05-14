package com.upendra.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upendra.blog.api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
