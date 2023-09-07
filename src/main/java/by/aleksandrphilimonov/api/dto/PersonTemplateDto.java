package by.aleksandrphilimonov.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonTemplateDto {

    Integer id;

    String text;

    List<UserShouldAnswerDto> userShouldAnswers;
}