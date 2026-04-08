package curse.giftservice.victim;

import curse.giftservice.common.NotFoundException;
import curse.giftservice.dto.VictimDto;
import curse.giftservice.dto.VictimRequest;
import curse.giftservice.users.UserEntity;
import curse.giftservice.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VictimService {

    private final VictimRepository victimRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<VictimDto> getVictims(Long userId) {
        return victimRepository.findByUser_UserId(userId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public VictimDto createVictim(Long userId, VictimRequest request) {
        UserEntity user = userService.getById(userId);
        VictimEntity entity = new VictimEntity();
        applyRequest(entity, request);
        entity.setUser(user);
        return toDto(victimRepository.save(entity));
    }

    @Transactional
    public VictimDto updateVictim(Long userId, Long victimId, VictimRequest request) {
        VictimEntity entity = victimRepository.findByVictimIdAndUser_UserId(victimId, userId)
                .orElseThrow(() -> new NotFoundException("Victim with id=" + victimId + " not found"));
        applyRequest(entity, request);
        return toDto(victimRepository.save(entity));
    }

    @Transactional
    public void deleteVictim(Long userId, Long victimId) {
        victimRepository.deleteByVictimIdAndUser_UserId(victimId, userId);
    }

    @Transactional(readOnly = true)
    public String buildAiProfile(Long userId, Long victimId) {
        VictimEntity entity = victimRepository.findByVictimIdAndUser_UserId(victimId, userId)
                .orElseThrow(() -> new NotFoundException("Victim with id=" + victimId + " not found"));
        return "Анкета пользователя для подбора подарка: " +
                "пол=" + entity.getGender() +
                ", дата рождения=" + entity.getBirthdate() +
                ", страна=" + entity.getCountry() +
                ", город=" + entity.getCity() +
                ", дополнительная информация=" + entity.getInfo();
    }

    private VictimDto toDto(VictimEntity entity) {
        return new VictimDto(
                entity.getVictimId(),
                entity.getGender(),
                entity.getBirthdate(),
                entity.getCountry(),
                entity.getCity(),
                entity.getInfo()
        );
    }

    private void applyRequest(VictimEntity entity, VictimRequest request) {
        entity.setGender(request.getGender().trim());
        entity.setBirthdate(request.getBirthdate());
        entity.setCountry(request.getCountry().trim());
        entity.setCity(request.getCity().trim());
        entity.setInfo(request.getInfo().trim());
    }
}
