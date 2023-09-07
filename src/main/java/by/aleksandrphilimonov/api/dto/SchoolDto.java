package by.aleksandrphilimonov.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolDto {

    @NonNull
    Integer id;

    @NonNull
    String name;
}