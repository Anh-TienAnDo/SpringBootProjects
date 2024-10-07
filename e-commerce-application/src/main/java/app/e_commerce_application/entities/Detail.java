package app.e_commerce_application.entities;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Detail {
    private String author;

    private List<String> categories;
    
    private String producer;

    @Field("image")
    private String image;

    @Field("quantity")
    private Integer quantity;

    @Field("time_total")
    private Integer timeTotal;

    @Field("description")
    private String description;

    // Constructors
    public Detail() {
    }

    public Detail(String author, List<String> categories, String producer, String image, Integer quantity, Integer timeTotal, String description) {
        System.out.println("Detail constructor");
        this.author = author;
        this.categories = categories;
        this.producer = producer;
        this.image = image;
        this.quantity = quantity;
        this.timeTotal = timeTotal;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTimeTotal() {
        return timeTotal;
    }

    public void setTimeTotal(Integer timeTotal) {
        this.timeTotal = timeTotal;
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
                ", quantity=" + quantity +
                ", timeTotal=" + timeTotal +
                '}';
    }
}
