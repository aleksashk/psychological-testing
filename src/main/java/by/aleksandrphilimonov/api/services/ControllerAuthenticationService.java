package by.aleksandrphilimonov.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import by.aleksandrphilimonov.api.exceptions.UnauthorizedException;
import by.aleksandrphilimonov.api.store.entities.PsychologistEntity;
import by.aleksandrphilimonov.api.store.entities.TokenEntity;
import by.aleksandrphilimonov.api.store.repositories.TokenRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
@Transactional
public class ControllerAuthenticationService {

    TokenRepository tokenRepository;

    public PsychologistEntity authenticate(String tokenStr) {

        TokenEntity token = tokenRepository
                .findById(tokenStr)
                .orElseThrow(
                        () -> new UnauthorizedException("Для выполнения этого действия необходимо войти в систему.")
                );

        if (token.getExpiredAt().isBefore(Instant.now())) {
            throw new UnauthorizedException("Время сессии истекло. Перезайдите в систему.");
        }

        return token.getPsychologist();
    }
}
