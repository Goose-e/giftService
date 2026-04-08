package curse.giftservice.friends;

import curse.giftservice.users.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friends", uniqueConstraints = @UniqueConstraint(name = "uq_friend_pair", columnNames = {"user_id", "friend_user_id"}))
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long friendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_user_id", nullable = false)
    private UserEntity friendUser;
}
