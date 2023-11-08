package com.project.shopapp.controllers;
import com.project.shopapp.components.LocalizationUtils;
import com.project.shopapp.models.dtos.CategoryDTO;
import com.project.shopapp.entities.Category;
import com.project.shopapp.models.responses.UpdateCategoryResponse;
import com.project.shopapp.services.CategoryService;
import com.project.shopapp.utils.ExportExcel;
import com.project.shopapp.utils.MessageKeys;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/categories")
//@Validated
//Dependency Injection
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private  MessageSource messageSource;
    @Autowired
    private  LocalizationUtils localizationUtils;
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)  {
        Category createdCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }
    //Hiện tất cả các categories
    @GetMapping("/export/excel")
    public void exportIntoExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<Category> listOfRecords = categoryService.getAllCategories();
        ExportExcel generator = new ExportExcel(listOfRecords);
        generator.generate(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        UpdateCategoryResponse updateCategoryResponse = new UpdateCategoryResponse();
        categoryService.updateCategory(id, categoryDTO);
        updateCategoryResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.UPDATE_CATEGORY_SUCCESSFULLY));
        return ResponseEntity.ok(updateCategoryResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(localizationUtils.getLocalizedMessage(MessageKeys.DELETE_CATEGORY_SUCCESSFULLY));
    }
}

