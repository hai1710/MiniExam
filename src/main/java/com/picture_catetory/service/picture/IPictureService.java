package com.picture_catetory.service.picture;

import com.picture_catetory.model.Category;
import com.picture_catetory.model.Picture;

import java.util.List;
import java.util.Optional;

public interface IPictureService {
    Iterable<Picture> findAll();
    Optional<Picture> findById(Long id);
    List<Picture> searchPicturesByName(String name);
    Picture save(Picture picture);
    List<Picture> getPicturesByCategory(Category category);
    void remove(Long id);
}
