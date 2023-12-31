package by.aleksandrphilimonov.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import by.aleksandrphilimonov.api.domains.IPersonTemplateAnalyze;
import by.aleksandrphilimonov.api.dto.PersonAnalyzeDto;
import by.aleksandrphilimonov.api.exceptions.AccessDeniedException;
import by.aleksandrphilimonov.api.exceptions.BadRequestException;
import by.aleksandrphilimonov.api.exceptions.NotFoundException;
import by.aleksandrphilimonov.api.store.entities.PsychologistEntity;
import by.aleksandrphilimonov.api.store.entities.SchoolClassEntity;
import by.aleksandrphilimonov.api.store.entities.TestEntity;
import by.aleksandrphilimonov.api.store.repositories.TestAnswerRepository;
import by.aleksandrphilimonov.api.store.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestService {

    TestRepository testRepository;

    TestAnswerRepository testAnswerRepository;

    public static final String LINK_TEMPLATE = "/test/%s/class/%s";

    public Map<Integer, List<PersonAnalyzeDto>> getTestAnswerIdToPersonAnalyze(
            SchoolClassEntity schoolClass,
            TestEntity test) {

        List<IPersonTemplateAnalyze> personAnalyzes = testAnswerRepository
                .analyzePersonTemplates(schoolClass.getId(), test.getId());

        Map<Integer, List<PersonAnalyzeDto>> testAnswerIdToPersonAnalyze = new HashMap<>();

        personAnalyzes
                .forEach(personAnalyze ->
                        testAnswerIdToPersonAnalyze
                                .computeIfAbsent(personAnalyze.getTestAnswerId(), key -> new ArrayList<>())
                                .add(PersonAnalyzeDto.builder()
                                        .testedUserAnswers(personAnalyze.getTestedUserAnswers())
                                        .totalAnswers(personAnalyze.getTotalAnswers())
                                        .personTemplateId(personAnalyze.getPersonTemplateId())
                                        .build()
                                )
                );
        return testAnswerIdToPersonAnalyze;
    }

    public String generateLinkForTest(PsychologistEntity psychologist, Integer testId) {

        getTestOrThrowException(psychologist, testId);

        SchoolClassEntity schoolClass = psychologist.getSchoolClass();

        if (Objects.isNull(schoolClass)) {

            throw new BadRequestException(
                    "Вы не можете сгенерировать ссылку, так как не привязаны ни к одному классу."
            );
        }

        return generateLink(testId, schoolClass.getId());
    }

    public static String generateLink(Integer testId, Integer schoolClassId) {

        if (Objects.isNull(testId) || testId < 0) {
            throw new IllegalArgumentException("Поле testId не может быть пустым или меньше 0.");
        }

        if (Objects.isNull(schoolClassId) || schoolClassId < 0) {
            throw new IllegalArgumentException("Поле schoolClassId не может быть пустым или меньше 0.");
        }

        return String.format(LINK_TEMPLATE, testId, schoolClassId);
    }

    public TestEntity getTestOrThrowException(PsychologistEntity psychologist, Integer testId) {

        TestEntity test = testRepository
                .findById(testId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Тест с идентификатором \"%s\" не найден.", testId))
                );

        if (!Objects.equals(test.getPsychologist().getId(), psychologist.getId())) {
            throw new AccessDeniedException("Вы не можете получить доступ к этому тесту.");
        }

        return test;
    }
}
