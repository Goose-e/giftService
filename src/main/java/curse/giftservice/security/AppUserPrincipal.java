package curse.giftservice.security;

public record AppUserPrincipal(Long userId, String login, String username) {
}
