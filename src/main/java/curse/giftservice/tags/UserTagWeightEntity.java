package curse.giftservice.tags;

import curse.giftservice.users.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "user_tag_weight", uniqueConstraints = @UniqueConstraint(name = "uq_user_tag", columnNames = {"user_id", "tag_id"}))
public class UserTagWeightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_tag_weight_id")
    private Long userTagWeightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "tag_weight")
    private BigDecimal tagWeight;
}
