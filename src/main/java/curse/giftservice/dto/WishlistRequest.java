package curse.giftservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WishlistRequest {
    @NotNull
    private Long giftId;
}
