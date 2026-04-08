package curse.giftservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiftIdeaDto {
    private Long giftId;
    private String giftName;
    private Long avgPrice;
    private String tag;
}
