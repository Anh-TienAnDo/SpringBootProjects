package app.e_commerce_application.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "producers")
public class Producer {
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
    private Date createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private Date updatedAt;

    
    // Constructors
    public Producer() {
        super();
    }
    
    public Producer(String id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = slugify(this.name);
        }
    }
    
    @Override
    public String toString() {
        return name;
    }

    private String slugify(String input) {
        // Slugify logic here
        return input.toLowerCase().replaceAll("[^a-z0-9]+", "-");
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
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = slugify(this.name);
        }
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
        this.slug = slug;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
}
