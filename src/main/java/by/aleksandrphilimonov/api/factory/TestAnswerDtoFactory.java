package by.aleksandrphilimonov.api.factory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import by.aleksandrphilimonov.api.dto.PersonAnalyzeDto;
import by.aleksandrphilimonov.api.dto.TestAnswerDto;
import by.aleksandrphilimonov.api.dto.TestedUserAnswerDto;
import by.aleksandrphilimonov.api.store.entities.TestAnswerEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class TestAnswerDtoFactory {

    TestDtoFactory testDtoFactory;

    TestedUserDtoFactory testedUserDtoFactory;

    public List<TestAnswerDto> createTestAnswerDtoList(
            List<TestAnswerEntity> entities,
            Map<Integer, List<PersonAnalyzeDto>> testAnswerIdToPersonAnalyze) {

        return entities
                .stream()
                .map(it -> createTestAnswerDto(it, testAnswerIdToPersonAnalyze))
                .collect(Collectors.toList());
    }

    public TestAnswerDto createTestAnswerDto(
            TestAnswerEntity entity,
            Map<Integer, List<PersonAnalyzeDto>> testAnswerIdToPersonAnalyze) {

        return TestAnswerDto.builder()
                .test(testDtoFactory.createLiteTestDto(entity.getTest()))
                .user(testedUserDtoFactory.createTestedUserDto(entity.getTestedUser()))
                .personAnalyzes(testAnswerIdToPersonAnalyze.getOrDefault(entity.getId(), new ArrayList<>()))
                .answers(
                        entity
                                .getAnswers()
                                .stream()
                                .map(it ->
                                        TestedUserAnswerDto.builder()
                                                .answerId(it.getAnswer_id())
                                                .createdAt(Instant.ofEpochMilli(it.getCreated_at()))
                                                .questionId(it.getQuestion_id())
                                                .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }
}