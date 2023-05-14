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
		Category category = modelMapper.map(categoryDto, Category.class);
		categoryRepository.save(category);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		
		//throw new ServiceException("Employee not found for given id " + id + "", HttpStatus.NOT_FOUND)
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void updateCategory(Integer id, CategoryDto categoryDto) {
		Category updateCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		updateCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		updateCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		categoryRepository.save(updateCategory);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category deleteCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		categoryRepository.delete(deleteCategory);
	}

}
