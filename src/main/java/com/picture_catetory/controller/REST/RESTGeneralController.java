package com.picture_catetory.controller.REST;

import com.picture_catetory.model.Category;
import com.picture_catetory.model.Picture;
import com.picture_catetory.service.category.ICategoryService;
import com.picture_catetory.service.picture.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api")
public class RESTGeneralController {
    @Autowired
    private IPictureService pictureService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    private ResponseEntity<Iterable<Picture>> showPictureList(){
        Iterable<Picture> pictures = pictureService.findAll();

        return new ResponseEntity<>(pictures, HttpStatus.OK);
    }

    @GetMapping("category")
    private ResponseEntity<Iterable<Category>> showCategoryList(){
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Picture> addNewPicture(@RequestBody Picture picture){
        Picture newPicture = pictureService.save(picture);
        return new ResponseEntity<>(newPicture, HttpStatus.CREATED);
    }
    @GetMapping("picture/search")
    public ResponseEntity<Iterable<Picture>> findByName(@RequestParam("name") String name) {
        Iterable<Picture> painting = pictureService.searchPicturesByName(name);
        if (painting != null) {
            return new ResponseEntity<>(painting, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("painting/searchbycategory/{categoryId}")
    public ResponseEntity<Iterable<Picture>> findByCategory(@PathVariable Long categoryId) {

        Category category = categoryService.findById(categoryId).orElse(null);
        if (category != null) {
            Iterable<Picture> iterable = pictureService.getPicturesByCategory(category);
            if (iterable != null) {
                return new ResponseEntity<>(iterable, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
