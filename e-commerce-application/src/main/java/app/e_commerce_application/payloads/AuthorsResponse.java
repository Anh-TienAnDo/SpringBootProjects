package app.e_commerce_application.payloads;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import app.e_commerce_application.entities.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorsResponse {
    private List<Author> data;
    private String status; // true if the request was successful, false otherwise
    private String message; // message to show to the user

    public void setAuthorResposeSuccess(List<Author> data, String message) {
        this.data = data;
        this.status = "Success";
        this.message = message;
    }

    public void setAuthorResposeFail(String message) {
        this.status = "Fail";
        this.message = message;
    }
}
