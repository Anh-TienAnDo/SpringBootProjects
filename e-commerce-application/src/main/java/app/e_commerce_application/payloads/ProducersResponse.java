package app.e_commerce_application.payloads;
import lombok.*;
import java.util.List;

import app.e_commerce_application.entities.Producer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducersResponse {
    
    private List<Producer> data;
    private String status; // true if the request was successful, false otherwise
    private String message; // message to show to the user

    public void setProducerResposeSuccess(List<Producer> data, String message) {
        this.data = data;
        this.status = "Success";
        this.message = message;
    }

    public void setProducerResposeFail(String message) {
        this.status = "Fail";
        this.message = message;
    }
}
