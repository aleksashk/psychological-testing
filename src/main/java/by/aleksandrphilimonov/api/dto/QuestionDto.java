package by.aleksandrphilimonov.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto {

    Integer id;

    Short order;

    String text;

    List<AnswerDto> answers;
}
