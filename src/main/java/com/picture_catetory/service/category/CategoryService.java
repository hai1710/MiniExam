package com.picture_catetory.service.category;

import com.picture_catetory.model.Category;
import com.picture_catetory.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public Optional<Category> findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        Category newCategory = categoryRepository.save(category);
        return category;
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }
}
