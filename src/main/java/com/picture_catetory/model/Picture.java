package com.picture_catetory.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "picture")
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double height_size;
    private Double width_size;
    private String material;
    private String description;
    private Double price;
    @ManyToMany
    @JoinTable(
            name = "picture_category",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;

    public Picture() {
    }

    public Picture(String name, Double height_size, Double width_size, String material, String description, Double price, List<Category> categoryList) {
        this.name = name;
        this.height_size = height_size;
        this.width_size = width_size;
        this.material = material;
        this.description = description;
        this.price = price;
        this.categoryList = categoryList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_picture) {
        this.name = name_picture;
    }

    public Double getHeight_size() {
        return height_size;
    }

    public void setHeight_size(Double height_size) {
        this.height_size = height_size;
    }

    public Double getWidth_size() {
        return width_size;
    }

    public void setWidth_size(Double width_size) {
        this.width_size = width_size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
