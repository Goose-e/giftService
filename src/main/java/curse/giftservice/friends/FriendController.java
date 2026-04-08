package curse.giftservice.friends;

import curse.giftservice.dto.FriendActionRequest;
import curse.giftservice.dto.FriendListResponseDto;
import curse.giftservice.httpResponse.DefaultHttpResponseBody;
import curse.giftservice.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public DefaultHttpResponseBody<FriendListResponseDto> list() {
        Long userId = SecurityUtils.currentUser().userId();
        DefaultHttpResponseBody<FriendListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Friends loaded");
        response.setResponseEntity(new FriendListResponseDto(friendService.getFriends(userId)));
        return response;
    }

    @PostMapping
    public DefaultHttpResponseBody<FriendListResponseDto> add(@Valid @RequestBody FriendActionRequest request) {
        Long userId = SecurityUtils.currentUser().userId();
        friendService.addFriend(userId, request.getFriendLogin());
        DefaultHttpResponseBody<FriendListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Friend added");
        response.setResponseEntity(new FriendListResponseDto(friendService.getFriends(userId)));
        return response;
    }

    @DeleteMapping("/{friendLogin}")
    public DefaultHttpResponseBody<FriendListResponseDto> delete(@PathVariable String friendLogin) {
        Long userId = SecurityUtils.currentUser().userId();
        friendService.removeFriend(userId, friendLogin);
        DefaultHttpResponseBody<FriendListResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Friend removed");
        response.setResponseEntity(new FriendListResponseDto(friendService.getFriends(userId)));
        return response;
    }
}
