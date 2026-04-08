package curse.giftservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGiftRequest {
    @NotBlank
    private String giftName;

    @Min(0)
    private Long giftAvgPrice;

    @NotBlank
    private String tagName;
}
