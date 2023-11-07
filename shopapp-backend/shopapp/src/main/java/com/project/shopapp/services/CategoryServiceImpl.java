package com.project.shopapp.services;

import com.project.shopapp.models.dtos.CategoryDTO;
import com.project.shopapp.entities.Category;
import com.project.shopapp.repositories.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Category createCategory( CategoryDTO categoryDTO) throws Exception {
        Category newCategory = Category
                .builder()
                .name(categoryDTO.getName())
                .build();
        if(newCategory.getName().isEmpty()){
            throw new Exception("Category's name cannot be empty");
        }
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category updateCategory(long categoryId,
                                   CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    @Transactional
    public void deleteCategory(long id) {
        //xóa xong
        categoryRepository.deleteById(id);
    }
}
