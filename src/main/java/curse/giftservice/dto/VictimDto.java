package curse.giftservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VictimDto {
    private Long victimId;
    private String gender;
    private LocalDate birthdate;
    private String country;
    private String city;
    private String info;
}
