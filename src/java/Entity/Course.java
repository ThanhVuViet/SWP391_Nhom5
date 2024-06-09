/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
import java.math.BigDecimal;
import java.util.Date;

public class Course {

    private int courseId;
    private String title;

    private Integer categoryId;
    private String description;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;

    // Constructor
    public Course() {
    }

    public Course(int courseId, String title, Integer categoryId, String description, BigDecimal price, Date createdAt, Date updatedAt) {
        this.courseId = courseId;
        this.title = title;

        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", title=" + title + ", categoryId=" + categoryId + ", description=" + description + ", price=" + price + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
