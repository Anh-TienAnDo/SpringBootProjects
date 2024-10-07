package app.e_commerce_application.payloads;

import app.e_commerce_application.entities.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaSocialDTO_Receive {
    
    private String id;
    private String title;
    private String slug;
    private String type;
    private int view;
    private boolean isActive;
    private Detail detail;
}
