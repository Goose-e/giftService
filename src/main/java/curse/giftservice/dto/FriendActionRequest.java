package curse.giftservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FriendActionRequest {
    @NotBlank
    private String friendLogin;
}
