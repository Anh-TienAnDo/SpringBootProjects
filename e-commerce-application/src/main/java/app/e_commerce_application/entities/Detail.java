package app.e_commerce_application.entities;
// import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Detail {
    @Field("author")
    private String author;

    @Field("categories")
    private List<String> categories;
    
    @Field("producer")
    private String producer;

    @Field("image")
    private String image;

    @Field("url")
    private String url;

    @Field("quantity")
    private Integer quantity;

    @Field("time_total")
    private Integer time_total;

    @Field("description")
    private String description;

    // Constructors
    public Detail() {
    }

    public Detail(String author, List<String> categories, String producer, String image, String url, Integer quantity, Integer time_total, String description) {
        System.out.println("Detail constructor");
        this.author = author;
        this.categories = categories;
        this.producer = producer;
        this.image = image;
        this.url = url;
        this.quantity = quantity;
        this.time_total = time_total;
        this.description = description;
    }

    // Getters and Setters
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTime_total() {
        return this.time_total;
    }

    public void setTime_total(Integer time_total) {
        this.time_total = time_total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "author=" + author +
                ", categories=" + categories +
                ", producer=" + producer +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", quantity=" + quantity +
                ", time_total=" + time_total +
                '}';
    }
}
