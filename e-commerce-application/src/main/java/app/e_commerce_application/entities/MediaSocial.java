package app.e_commerce_application.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.Data;

import java.util.Date;
import java.util.List;

// @Data 
// @NoArgsConstructor 
// @RequiredArgsConstructor 
// @AllArgsConstructor 
@Document(collection = "media_socials")
public class MediaSocial {

    @Id
    private String id;

    @NonNull
    @Field("title")
    private String title;

    @Field("slug")
    private String slug;

    @NonNull
    @Field("type")
    private String type = "sayings"; // sayings, audio-book, video, etc.

    @NonNull
    @Field("view")
    private int view = 0;

    @Field("is_active")
    private boolean isActive = true;

    @NonNull
    @CreatedDate
    @Field("created_at")
    private Date createdAt;

    @NonNull
    @LastModifiedDate
    @Field("updated_at")
    private Date updatedAt;

    @Field("detail")
    private Detail detail;

    // Constructors
    public MediaSocial() {
    }

    public MediaSocial(String title, String type, int view, boolean isActive, Detail detail) {
        this.title = title;
        this.slug = slugify(title);
        if (type == null || type.isEmpty()) {
            type = "sayings";
        }
        this.type = type;
        this.view = view;
        this.isActive = isActive;
        // this.createdAt = createdAt;
        // this.updatedAt = updatedAt;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MediaSocial{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", type='" + type + '\'' +
                ", view=" + view +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", detail=" + detail +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.slug = slugify(title);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        if (slug == null || slug.isEmpty()) {
            slug = slugify(this.title);
        }
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            type = "sayings";
        }
        this.type = type;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

}