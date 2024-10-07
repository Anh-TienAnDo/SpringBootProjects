package app.e_commerce_application.payloads;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaSocialsResponse {
    private List<MediaSocialDTO_Receive> data;
    private boolean success; // true if the request was successful, false otherwise
    private Integer totalElements; // total number of elements in DB
    private String message; // message to show to the user
}
