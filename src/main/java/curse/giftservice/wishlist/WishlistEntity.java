package curse.giftservice.wishlist;

import curse.giftservice.gifts.GiftEntity;
import curse.giftservice.users.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wishlist_data", uniqueConstraints = @UniqueConstraint(name = "uq_wishlist", columnNames = {"user_id", "gift_id"}))
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_id", nullable = false)
    private GiftEntity gift;
}
