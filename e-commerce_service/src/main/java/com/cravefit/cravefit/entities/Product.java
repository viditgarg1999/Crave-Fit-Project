package com.cravefit.cravefit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="user_cart")
public class Product {

    @Id
    @Column(name="product_id")
    private Long productId;
    @Column(name="name")
    private String name;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="price")
    private Double price;
    @Column(name="description")
    private String description;
    @Column(name="category_id")
    private Long categoryId;
    @Column(name="tags")
    private String tags;
    @Column(name="listed_date")
    private Date listedDate;
    @Column(name="priority_index")
    private int popularityIndex;

    public Product() {
    }



    public Product(Long productId, String name, String imageUrl, Double price, String description, Long categoryId, String tags, Date listedDate) {
        this.productId = productId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.tags = tags;
        this.listedDate = listedDate;
        this.popularityIndex = 0;
    }



    public int getPopularityIndex() {
        return popularityIndex;
    }

    public void setPopularityIndex(int popularityIndex) {
        this.popularityIndex = popularityIndex;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getListedDate() {
        return listedDate;
    }

    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }
}
