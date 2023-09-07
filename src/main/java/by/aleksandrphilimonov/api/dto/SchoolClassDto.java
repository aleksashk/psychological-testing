package by.aleksandrphilimonov.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolClassDto {

    @NonNull
    Integer id;

    @NonNull
    String name;

    @NonNull
    SchoolDto school;
}
