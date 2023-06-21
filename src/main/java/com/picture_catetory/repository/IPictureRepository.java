package com.picture_catetory.repository;

import com.picture_catetory.model.Category;
import com.picture_catetory.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findByNameContainingIgnoreCase(String name);
    List<Picture> findByCategoryList(Category category);
}
