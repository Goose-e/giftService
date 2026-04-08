package curse.giftservice.dto;

import curse.giftservice.httpResponse.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VictimAiProfileResponseDto implements ResponseDto {
    private Long victimId;
    private String profile;
}
