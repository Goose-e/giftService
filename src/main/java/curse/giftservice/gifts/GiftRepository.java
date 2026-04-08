package curse.giftservice.gifts;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface GiftRepository extends JpaRepository<GiftEntity, Long> {

    @Query("""
            select g from GiftEntity g
            where g.tag.tagId in :tagIds
            and (:excludedIdsEmpty = true or g.giftsId not in :excludedIds)
            order by g.giftsId desc
            """)
    List<GiftEntity> findIdeasByTagIds(Collection<Long> tagIds, Collection<Long> excludedIds, boolean excludedIdsEmpty, Pageable pageable);

    @Query("""
            select g from GiftEntity g
            where (:excludedIdsEmpty = true or g.giftsId not in :excludedIds)
            order by g.giftsId desc
            """)
    List<GiftEntity> findIdeasAll(Collection<Long> excludedIds, boolean excludedIdsEmpty, Pageable pageable);
}
