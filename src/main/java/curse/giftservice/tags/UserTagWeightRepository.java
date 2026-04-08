package curse.giftservice.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTagWeightRepository extends JpaRepository<UserTagWeightEntity, Long> {

    @Query("""
            select utw from UserTagWeightEntity utw
            where utw.user.userId = :userId
            order by utw.tagWeight desc
            """)
    List<UserTagWeightEntity> findRankedByUserId(Long userId);
}
