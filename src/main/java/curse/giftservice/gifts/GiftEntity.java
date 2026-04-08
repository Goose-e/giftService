package curse.giftservice.gifts;

import curse.giftservice.tags.TagEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gifts")
public class GiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gifts_id")
    private Long giftsId;

    @Column(name = "gift_name", nullable = false)
    private String giftName;

    @Column(name = "gift_avg_price")
    private Long giftAvgPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private TagEntity tag;
}
