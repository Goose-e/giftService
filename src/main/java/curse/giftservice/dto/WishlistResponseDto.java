package curse.giftservice.dto;

import curse.giftservice.httpResponse.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WishlistResponseDto implements ResponseDto {
    private List<GiftIdeaDto> gifts;
}
