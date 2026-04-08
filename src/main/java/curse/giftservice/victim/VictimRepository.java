package curse.giftservice.victim;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VictimRepository extends JpaRepository<VictimEntity, Long> {
    List<VictimEntity> findByUser_UserId(Long userId);

    Optional<VictimEntity> findByVictimIdAndUser_UserId(Long victimId, Long userId);

    void deleteByVictimIdAndUser_UserId(Long victimId, Long userId);
}
