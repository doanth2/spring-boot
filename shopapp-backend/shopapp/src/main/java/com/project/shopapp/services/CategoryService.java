package com.project.shopapp.services;

import com.project.shopapp.models.dtos.CategoryDTO;
import com.project.shopapp.entities.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO category) ;
    Category getCategoryById(long id);
    List<Category> getAllCategories();
    Category updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
