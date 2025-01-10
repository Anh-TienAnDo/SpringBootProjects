package app.e_commerce_application.payloads;
import lombok.*;
import java.util.List;

import app.e_commerce_application.entities.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesResponse {
    private List<Category> data;
    private String status; // true if the request was successful, false otherwise
    private String message; // message to show to the user

    public void setCategoryResposeSuccess(List<Category> data, String message) {
        this.data = data;
        this.status = "Success";
        this.message = message;
    }
    
    public void setCategoryResposeFail(String message) {
        this.status = "Fail";
        this.message = message;
    }
}
