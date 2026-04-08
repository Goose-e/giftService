package curse.giftservice.victim;

import curse.giftservice.users.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "victim")
public class VictimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "victim_id")
    private Long victimId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String gender;
    private LocalDate birthdate;
    private String country;
    private String city;

    @Column(columnDefinition = "text")
    private String info;
}
