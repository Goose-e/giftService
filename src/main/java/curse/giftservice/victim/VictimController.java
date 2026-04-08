package curse.giftservice.victim;

import curse.giftservice.dto.VictimAiProfileResponseDto;
import curse.giftservice.dto.VictimListResponseDto;
import curse.giftservice.dto.VictimRequest;
import curse.giftservice.httpResponse.DefaultHttpResponseBody;
import curse.giftservice.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/victims")
public class VictimController {

    private final VictimService victimService;

    @GetMapping("/me")
    public DefaultHttpResponseBody<VictimListResponseDto> getMyVictims() {
        Long userId = SecurityUtils.currentUser().userId();
        DefaultHttpResponseBody<VictimListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Victims loaded");
        response.setResponseEntity(new VictimListResponseDto(victimService.getVictims(userId)));
        return response;
    }

    @PostMapping("/me")
    public DefaultHttpResponseBody<VictimListResponseDto> createVictim(@Valid @RequestBody VictimRequest request) {
        Long userId = SecurityUtils.currentUser().userId();
        victimService.createVictim(userId, request);
        DefaultHttpResponseBody<VictimListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Victim profile created");
        response.setResponseEntity(new VictimListResponseDto(victimService.getVictims(userId)));
        return response;
    }

    @PutMapping("/me/{victimId}")
    public DefaultHttpResponseBody<VictimListResponseDto> updateVictim(@PathVariable Long victimId, @Valid @RequestBody VictimRequest request) {
        Long userId = SecurityUtils.currentUser().userId();
        victimService.updateVictim(userId, victimId, request);
        DefaultHttpResponseBody<VictimListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Victim profile updated");
        response.setResponseEntity(new VictimListResponseDto(victimService.getVictims(userId)));
        return response;
    }

    @DeleteMapping("/me/{victimId}")
    public DefaultHttpResponseBody<VictimListResponseDto> deleteVictim(@PathVariable Long victimId) {
        Long userId = SecurityUtils.currentUser().userId();
        victimService.deleteVictim(userId, victimId);
        DefaultHttpResponseBody<VictimListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Victim profile deleted");
        response.setResponseEntity(new VictimListResponseDto(victimService.getVictims(userId)));
        return response;
    }

    @GetMapping("/me/{victimId}/ai-profile")
    public DefaultHttpResponseBody<VictimAiProfileResponseDto> aiProfile(@PathVariable Long victimId) {
        Long userId = SecurityUtils.currentUser().userId();
        DefaultHttpResponseBody<VictimAiProfileResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("AI profile generated");
        response.setResponseEntity(new VictimAiProfileResponseDto(victimId, victimService.buildAiProfile(userId, victimId)));
        return response;
    }
}
