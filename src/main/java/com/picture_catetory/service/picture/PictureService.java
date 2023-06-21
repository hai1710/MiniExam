package com.picture_catetory.service.picture;

import com.picture_catetory.model.Category;
import com.picture_catetory.model.Picture;
import com.picture_catetory.repository.IPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService implements IPictureService {
    @Autowired
    private IPictureRepository pictureRepository;

    @Override
    public Iterable<Picture> findAll() {
        Iterable<Picture> pictures = pictureRepository.findAll();
        return pictures;
    }

    @Override
    public Optional<Picture> findById(Long id) {
        Optional<Picture> picture = pictureRepository.findById(id);
        return picture;
    }

    @Override
    public List<Picture> searchPicturesByName(String name) {
        return pictureRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Picture save(Picture picture) {
        Picture newPicture = pictureRepository.save(picture);
        return newPicture;
    }

    @Override
    public List<Picture> getPicturesByCategory(Category category) {
        return pictureRepository.findByCategoryList(category);
    }


    @Override
    public void remove(Long id) {
        pictureRepository.deleteById(id);
    }
}
