package by.aleksandrphilimonov.api.factory;

import by.aleksandrphilimonov.api.dto.AnswerDto;
import by.aleksandrphilimonov.api.dto.QuestionDto;
import by.aleksandrphilimonov.api.dto.*;
import by.aleksandrphilimonov.api.store.entities.AnswerEntity;
import by.aleksandrphilimonov.api.store.entities.PersonTemplateEntity;
import by.aleksandrphilimonov.api.store.entities.QuestionEntity;
import by.aleksandrphilimonov.api.store.entities.TestEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestDtoFactory {

    public LiteTestDto createLiteTestDto(TestEntity entity) {

        return LiteTestDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public TestDto createTestDto(TestEntity entity) {

        return TestDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .questions(createQuestionDtoList(entity.getQuestions()))
                .personTemplates(createPersonTemplateDtoList(entity.getPersonTemplates()))
                .build();
    }

    public List<PersonTemplateDto> createPersonTemplateDtoList(List<PersonTemplateEntity> entities) {

        return entities
                .stream()
                .map(this::createPersonTemplateDto)
                .collect(Collectors.toList());
    }

    public PersonTemplateDto createPersonTemplateDto(PersonTemplateEntity entity) {

        return PersonTemplateDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .userShouldAnswers(
                        entity
                                .getAnswers()
                                .stream()
                                .map(it -> UserShouldAnswerDto.builder()
                                        .answerId(it.getAnswer_id())
                                        .questionId(it.getQuestion_id())
                                        .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }

    public List<TestDto> createTestDtoList(List<TestEntity> entities) {
        return entities
                .stream()
                .map(this::createTestDto)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<QuestionDto> createQuestionDtoList(List<QuestionEntity> entities) {
        return entities
                .stream()
                .map(this::createQuestionDto)
                .distinct()
                .collect(Collectors.toList());
    }

    public QuestionDto createQuestionDto(QuestionEntity entity) {
        return QuestionDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .order(entity.getQuestionOrder())
                .answers(createAnswerDtoList(entity.getAnswers()))
                .build();
    }

    public List<AnswerDto> createAnswerDtoList(List<AnswerEntity> entities) {
        return entities
                .stream()
                .map(this::createAnswerDto)
                .distinct()
                .collect(Collectors.toList());
    }

    public AnswerDto createAnswerDto(AnswerEntity entity) {
        return AnswerDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .order(entity.getAnswerOrder())
                .build();
    }

}
