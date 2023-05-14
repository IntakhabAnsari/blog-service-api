package com.upendra.blog.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upendra.blog.api.dtos.CategoryDto;
import com.upendra.blog.api.entities.Category;
import com.upendra.blog.api.exceptions.ResourceNotFoundException;
import com.upendra.blog.api.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		categoryRepository.save(category);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(user -> categoryToDto(user)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return categoryToDto(category);
	}

	@Override
	public void updateCategory(Integer id, CategoryDto categoryDto) {
		Category updateCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		dtoToCategory(categoryDto);
		categoryRepository.save(updateCategory);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category deleteCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		categoryRepository.delete(deleteCategory);
	}

	private Category dtoToCategory(CategoryDto categoryDto) {
		if (categoryDto.equals(null)) {
			return null;
		}
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	private CategoryDto categoryToDto(Category category) {
		if (category.equals(null)) {
			return null;
		}
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
