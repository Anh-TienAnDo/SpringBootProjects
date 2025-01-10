package app.e_commerce_application.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
// import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mongodb.lang.NonNull;

import app.e_commerce_application.helper.ConvertVietnameseToNormalText;
import jakarta.persistence.Entity;
// import lombok.Data;

import java.util.Date;
// import java.util.List;

// @Data 
// @NoArgsConstructor 
// @RequiredArgsConstructor 
// @AllArgsConstructor 
@Entity
@Document(collection = "media_socials")
public class MediaSocial {

    private ConvertVietnameseToNormalText convertVietnameseToNormalText = new ConvertVietnameseToNormalText();

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
    private Date created_at;

    @NonNull
    @LastModifiedDate
    @Field("updated_at")
    private Date updated_at;

    @Field("detail")
    private Detail detail;

    // Constructors
    public MediaSocial() {
    }

    public MediaSocial(String title, String type, int view, boolean isActive, Detail detail) {
        this.title = title;
        // this.slug = slugify(title);
        this.slug = convertVietnameseToNormalText.slugify(title);
        if (type == null || type.isEmpty()) {
            type = "sayings";
        }
        this.type = type;
        this.view = view;
        this.isActive = isActive;
        // this.created_at = created_at;
        // this.updated_at = updated_at;
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
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", detail=" + detail +
                '}';
    }

    // private String slugify(String input) {
    //     // Slugify logic here
    //     input =  toNonAccentVietnamese(input.trim());
    //     return input.toLowerCase().replaceAll("[^a-z0-9]+", "-");
    // }

    // private String toNonAccentVietnamese(String str) {
    //     str = str.replaceAll("[AÁÀÃẠÂẤẦẪẬĂẮẰẴẶ]", "A");
    //     str = str.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
    //     str = str.replaceAll("[EÉÈẼẸÊẾỀỄỆ]", "E");
    //     str = str.replaceAll("[èéẹẻẽêềếệểễ]", "e");
    //     str = str.replaceAll("[IÍÌĨỊ]", "I");
    //     str = str.replaceAll("[ìíịỉĩ]", "i");
    //     str = str.replaceAll("[OÓÒÕỌÔỐỒỖỘƠỚỜỠỢ]", "O");
    //     str = str.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
    //     str = str.replaceAll("[UÚÙŨỤƯỨỪỮỰ]", "U");
    //     str = str.replaceAll("[ùúụủũưừứựửữ]", "u");
    //     str = str.replaceAll("[YÝỲỸỴ]", "Y");
    //     str = str.replaceAll("[ỳýỵỷỹ]", "y");
    //     str = str.replaceAll("[Đ]", "D");
    //     str = str.replaceAll("[đ]", "d");
    //     // Some system encode vietnamese combining accent as individual utf-8 characters
    //     str = str.replaceAll("[\\u0300\\u0301\\u0303\\u0309\\u0323]", ""); // Huyền sắc hỏi ngã nặng 
    //     str = str.replaceAll("[\\u02C6\\u0306\\u031B]", ""); // Â, Ê, Ă, Ơ, Ư
    //     return str;
    // }

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
        this.slug = convertVietnameseToNormalText.slugify(title);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        if (slug == null || slug.isEmpty()) {
            slug = convertVietnameseToNormalText.slugify(this.title);
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

}