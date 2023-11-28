package com.upendra.blog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.blog.api.dtos.CategoryDto;
import com.upendra.blog.api.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories() {
		return ResponseEntity.ok(categoryService.getCategories());
	}

	@PostMapping
	public ResponseEntity<Void> saveCategory(@RequestBody CategoryDto categoryDto) {
		categoryService.createCategory(categoryDto);
	 	return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto ) {
		categoryService.updateCategory(id, categoryDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
