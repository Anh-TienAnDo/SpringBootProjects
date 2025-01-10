package app.e_commerce_application.payloads;
import java.util.List;

import app.e_commerce_application.entities.MediaSocial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaSocialsResponse {
    private List<MediaSocial> data;
    private String status; // true if the request was successful, false otherwise
    private Long total_items; // total number of elements in DB
    private String message = ""; // message to show to the user

    public void setMediaSocialsResposeSuccess(List<MediaSocial> data, Long total_items, String message) {
        this.data = data;
        this.status = "Success";
        this.total_items = total_items;
        this.message = message;
    }

    public void setMediaSocialsResposeFail(String message) {
        this.status = "Fail";
        this.message = message;
    }
}
