package curse.giftservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        String userIdClaim = source.containsClaim("userId") ? source.getClaimAsString("userId") : source.getSubject();
        Long userId = userIdClaim != null && userIdClaim.matches("\\d+") ? Long.valueOf(userIdClaim) : null;
        String login = source.getClaimAsString("login");
        String username = source.getClaimAsString("username");
        AppUserPrincipal principal = new AppUserPrincipal(userId, login, username);
        return UsernamePasswordAuthenticationToken.authenticated(principal, source.getTokenValue(), List.of());
    }
}
