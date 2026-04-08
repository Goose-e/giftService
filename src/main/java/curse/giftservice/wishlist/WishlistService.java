package curse.giftservice.wishlist;

import curse.giftservice.common.BadRequestException;
import curse.giftservice.dto.GiftIdeaDto;
import curse.giftservice.gifts.GiftEntity;
import curse.giftservice.gifts.GiftIdeaService;
import curse.giftservice.users.UserEntity;
import curse.giftservice.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final GiftIdeaService giftIdeaService;

    @Transactional(readOnly = true)
    public List<GiftIdeaDto> getWishlist(Long userId) {
        return wishlistRepository.findByUser_UserId(userId).stream()
                .map(it -> new GiftIdeaDto(
                        it.getGift().getGiftsId(),
                        it.getGift().getGiftName(),
                        it.getGift().getGiftAvgPrice(),
                        it.getGift().getTag() != null ? it.getGift().getTag().getTagName() : null)
                )
                .toList();
    }

    @Transactional
    public void addGift(Long userId, Long giftId) {
        if (wishlistRepository.existsByUser_UserIdAndGift_GiftsId(userId, giftId)) {
            throw new BadRequestException("Gift already exists in wishlist");
        }

        UserEntity user = userService.getById(userId);
        GiftEntity gift = giftIdeaService.getById(giftId);

        WishlistEntity entity = new WishlistEntity();
        entity.setUser(user);
        entity.setGift(gift);
        wishlistRepository.save(entity);
    }

    @Transactional
    public void removeGift(Long userId, Long giftId) {
        wishlistRepository.deleteByUser_UserIdAndGift_GiftsId(userId, giftId);
    }
}
