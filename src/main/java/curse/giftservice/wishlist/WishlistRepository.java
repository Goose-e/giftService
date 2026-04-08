package curse.giftservice.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {
    List<WishlistEntity> findByUser_UserId(Long userId);

    boolean existsByUser_UserIdAndGift_GiftsId(Long userId, Long giftId);

    void deleteByUser_UserIdAndGift_GiftsId(Long userId, Long giftId);

    @Query("select w.gift.giftsId from WishlistEntity w where w.user.userId = :userId")
    List<Long> findGiftIdsByUserId(Long userId);
}
