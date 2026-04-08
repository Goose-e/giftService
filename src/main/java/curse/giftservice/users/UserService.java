package curse.giftservice.users;

import curse.giftservice.common.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserEntity getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id=" + userId + " not found"));
    }

    @Transactional(readOnly = true)
    public UserEntity getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("User with login=" + login + " not found"));
    }
}
