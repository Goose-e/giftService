package curse.giftservice.wishlist;

import curse.giftservice.dto.WishlistRequest;
import curse.giftservice.dto.WishlistResponseDto;
import curse.giftservice.httpResponse.DefaultHttpResponseBody;
import curse.giftservice.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping("/me")
    public DefaultHttpResponseBody<WishlistResponseDto> getMyWishlist() {
        Long userId = SecurityUtils.currentUser().userId();
        DefaultHttpResponseBody<WishlistResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Wishlist loaded");
        response.setResponseEntity(new WishlistResponseDto(wishlistService.getWishlist(userId)));
        return response;
    }

    @PostMapping("/me")
    public DefaultHttpResponseBody<WishlistResponseDto> addToWishlist(@Valid @RequestBody WishlistRequest request) {
        Long userId = SecurityUtils.currentUser().userId();
        wishlistService.addGift(userId, request.getGiftId());
        DefaultHttpResponseBody<WishlistResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Gift added to wishlist");
        response.setResponseEntity(new WishlistResponseDto(wishlistService.getWishlist(userId)));
        return response;
    }

    @DeleteMapping("/me/{giftId}")
    public DefaultHttpResponseBody<WishlistResponseDto> removeFromWishlist(@PathVariable Long giftId) {
        Long userId = SecurityUtils.currentUser().userId();
        wishlistService.removeGift(userId, giftId);
        DefaultHttpResponseBody<WishlistResponseDto> response = new DefaultHttpResponseBody<>();
        response.setResponseCode("OC_OK");
        response.setMessage("Gift removed from wishlist");
        response.setResponseEntity(new WishlistResponseDto(wishlistService.getWishlist(userId)));
        return response;
    }
}
