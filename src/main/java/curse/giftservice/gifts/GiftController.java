package curse.giftservice.gifts;

import curse.giftservice.dto.CreateGiftRequest;
import curse.giftservice.dto.GiftIdeaResponseDto;
import curse.giftservice.httpResponse.DefaultHttpResponseBody;
import curse.giftservice.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gifts")
public class GiftController {

    private final GiftIdeaService giftIdeaService;

    @GetMapping("/ideas")
    public DefaultHttpResponseBody<GiftIdeaResponseDto> ideas(@RequestParam(defaultValue = "10") int limit) {
        Long userId = SecurityUtils.currentUser().userId();
        DefaultHttpResponseBody<GiftIdeaResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Ideas generated");
        response.setResponseEntity(new GiftIdeaResponseDto(giftIdeaService.getIdeas(userId, limit)));
        return response;
    }

    @PostMapping
    public DefaultHttpResponseBody<GiftIdeaResponseDto> createGift(@Valid @RequestBody CreateGiftRequest request) {
        DefaultHttpResponseBody<GiftIdeaResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Gift created");
        response.setResponseEntity(new GiftIdeaResponseDto(java.util.List.of(giftIdeaService.createGift(request))));
        return response;
    }
}
