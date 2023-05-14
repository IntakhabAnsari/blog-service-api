package com.upendra.blog.api.services;

import java.util.List;

import com.upendra.blog.api.dtos.CategoryDto;

public interface CategoryService {

	List<CategoryDto> getCategories();

	CategoryDto getCategoryById(Integer id);

	void createCategory(CategoryDto categoryDto);

	void updateCategory(Integer id, CategoryDto categoryDto);

	void deleteCategory(Integer id);

}
