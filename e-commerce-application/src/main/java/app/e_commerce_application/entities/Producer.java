package app.e_commerce_application.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import app.e_commerce_application.helper.ConvertVietnameseToNormalText;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@Document(collection = "producers")
public class Producer {

    private ConvertVietnameseToNormalText convertVietnameseToNormalText = new ConvertVietnameseToNormalText();

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("email")
    private String email;

    @Field("slug")
    private String slug;

    @Field("is_active")
    private boolean isActive = true;

    @CreatedDate
    @Field("created_at")
    private Date created_at;

    @LastModifiedDate
    @Field("updated_at")
    private Date updated_at;

    
    // Constructors
    public Producer() {
        super();
    }
    
    public Producer(String id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.slug = convertVietnameseToNormalText.slugify(name);
    }
    
    @Override
    public String toString() {
        return name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.slug = convertVietnameseToNormalText.slugify(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        if (slug == null || slug.isEmpty()) {
            slug = convertVietnameseToNormalText.slugify(this.name);
        }
        this.slug = slug;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
