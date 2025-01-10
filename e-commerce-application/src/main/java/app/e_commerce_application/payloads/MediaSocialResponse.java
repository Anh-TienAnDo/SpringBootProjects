package app.e_commerce_application.payloads;

import app.e_commerce_application.entities.MediaSocial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaSocialResponse {
    private MediaSocial data;
    private String status; // true if the request was successful, false otherwise
    private String message; // message to show to the user
    // private Object object;

    public void setMediaSocialResposeSuccess(MediaSocial data, String message) {
        this.data = data;
        this.status = "Success";
        this.message = message;
    }

    public void setMediaSocialResposeFail(String message) {
        this.status = "Fail";
        this.message = message;
    }
}
