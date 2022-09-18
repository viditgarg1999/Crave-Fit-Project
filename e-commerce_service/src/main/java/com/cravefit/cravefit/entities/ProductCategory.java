package com.cravefit.cravefit.entities;

import javax.persistence.*;

@Entity
@Table(name="product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;
    @Column(name="name")
    private String name;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="description")
    private String description;

    public ProductCategory() {
    }

    public ProductCategory(Long categoryId, String name, String imageUrl, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
