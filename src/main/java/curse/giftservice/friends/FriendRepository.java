package curse.giftservice.friends;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    List<FriendEntity> findByUser_UserId(Long userId);

    boolean existsByUser_UserIdAndFriendUser_UserId(Long userId, Long friendUserId);

    void deleteByUser_UserIdAndFriendUser_UserId(Long userId, Long friendUserId);
}
