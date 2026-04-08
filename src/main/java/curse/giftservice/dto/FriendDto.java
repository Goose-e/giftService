package curse.giftservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendDto {
    private Long userId;
    private String username;
    private String login;
}
