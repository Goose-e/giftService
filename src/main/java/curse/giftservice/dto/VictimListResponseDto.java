package curse.giftservice.dto;

import curse.giftservice.httpResponse.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VictimListResponseDto implements ResponseDto {
    private List<VictimDto> victims;
}
