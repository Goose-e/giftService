package curse.giftservice.friends;

import curse.giftservice.common.BadRequestException;
import curse.giftservice.dto.FriendDto;
import curse.giftservice.users.UserEntity;
import curse.giftservice.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<FriendDto> getFriends(Long userId) {
        return friendRepository.findByUser_UserId(userId).stream()
                .map(FriendEntity::getFriendUser)
                .map(user -> new FriendDto(user.getUserId(), user.getUsername(), user.getLogin()))
                .toList();
    }

    @Transactional
    public void addFriend(Long userId, String friendLogin) {
        UserEntity user = userService.getById(userId);
        UserEntity friend = userService.getByLogin(friendLogin);
        if (user.getUserId().equals(friend.getUserId())) {
            throw new BadRequestException("You cannot add yourself as a friend");
        }
        if (friendRepository.existsByUser_UserIdAndFriendUser_UserId(userId, friend.getUserId())) {
            return;
        }

        FriendEntity direct = new FriendEntity();
        direct.setUser(user);
        direct.setFriendUser(friend);
        friendRepository.save(direct);

        if (!friendRepository.existsByUser_UserIdAndFriendUser_UserId(friend.getUserId(), userId)) {
            FriendEntity reverse = new FriendEntity();
            reverse.setUser(friend);
            reverse.setFriendUser(user);
            friendRepository.save(reverse);
        }
    }

    @Transactional
    public void removeFriend(Long userId, String friendLogin) {
        UserEntity friend = userService.getByLogin(friendLogin);
        friendRepository.deleteByUser_UserIdAndFriendUser_UserId(userId, friend.getUserId());
        friendRepository.deleteByUser_UserIdAndFriendUser_UserId(friend.getUserId(), userId);
    }
}
