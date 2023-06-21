package com.picture_catetory.service.category;

import com.picture_catetory.model.Category;

import java.util.Calendar;
import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();
    Optional<Category> findById(Long id);
    Category getCategoryById(long id);
    Category save(Category category);
    void remove(Long id);
}
