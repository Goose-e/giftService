package curse.giftservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VictimRequest {
    @NotBlank
    private String gender;

    private LocalDate birthdate;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String info;
}
