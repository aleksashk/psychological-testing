package by.aleksandrphilimonov.api.dto;

import by.aleksandrphilimonov.api.domains.TestedUserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestedUserDto {

    @NonNull
    Integer id;

    @NonNull
    String firstName;

    String middleName;

    @NonNull
    String lastName;

    @NonNull
    String login;

    @NonNull
    String password;

    @NonNull
    Instant birthday;

    @NonNull
    TestedUserStatus role;

    @NonNull
    Integer schoolClassId;
}
