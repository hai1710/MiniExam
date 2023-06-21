package com.picture_catetory.controller.noREST;

import com.picture_catetory.model.Category;
import com.picture_catetory.model.Picture;
import com.picture_catetory.service.category.ICategoryService;
import com.picture_catetory.service.picture.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class GeneralController {
    @Autowired
    private IPictureService pictureService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    private ModelAndView showListPicture() {
        List<Picture> pictureList = (List<Picture>) pictureService.findAll();
        if (pictureList.isEmpty()) {
            return new ModelAndView("404-error");
        } else {
            ModelAndView modelAndView = new ModelAndView("home");
            modelAndView.addObject("pictures", pictureList);
            return modelAndView;
        }
    }

    @GetMapping("create-picture")
    private ModelAndView showCreateFrom() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("picture", new Picture());
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("create-picture")
    private ModelAndView addNewPicture(@ModelAttribute("picture") Picture picture) {
        pictureService.save(picture);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("/search")
    public String searchPicturesByNameAndCategory(@RequestParam(name="name", required=false) String name,
                                                  @RequestParam(name="categoryId", required=false) Long categoryId,
                                                  Model model) {
        List<Picture> pictures = null;
        Category category = null;

        if (name != null && !name.isEmpty()) {
            pictures = pictureService.searchPicturesByName(name);
        }

        if (categoryId != null) {
            category = categoryService.getCategoryById(categoryId);
            List<Picture> picturesByCategory = pictureService.getPicturesByCategory(category);
            if (pictures == null) {
                pictures = picturesByCategory;
            } else {
                pictures.retainAll(picturesByCategory);
            }
        }

        model.addAttribute("pictures", pictures);
        model.addAttribute("categories", categoryService.findAll());

        return "home";
    }
}
