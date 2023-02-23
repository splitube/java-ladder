package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import domain.ladder.LadderResult;
import exception.NotEnglishAndNumberException;
import exception.ladder.InvalidLadderResultException;
import exception.view.EmptyInputException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class LadderResultTest {

    @DisplayName("사다리 결과의 입력값이 요구사항에 충족할 경우")
    @Test
    void createSuccess() {
        LadderResult ladderResult = new LadderResult("1000");
        assertThat(ladderResult.getName()).isEqualTo("1000");
    }

    @DisplayName("사다리 결과의 입력값이 요구사항에 맞지 않는 경우")
    @TestFactory
    Stream<DynamicTest> createFail() {
        return Stream.of(
            dynamicTest("결과가 null인 경우", () -> assertThatThrownBy(() -> new LadderResult(null))
                .isExactlyInstanceOf(EmptyInputException.class)),
            dynamicTest("결과가 빈문자열인 경우", () -> assertThatThrownBy(() -> new LadderResult(""))
                .isExactlyInstanceOf(EmptyInputException.class)),
            dynamicTest("결과가 공백으로 이루어져 있는 경우", () -> assertThatThrownBy(() -> new LadderResult("    "))
                .isExactlyInstanceOf(EmptyInputException.class)),
            dynamicTest("결과가 5자보다 긴 경우", () -> assertThatThrownBy(() -> new LadderResult("result"))
                .isExactlyInstanceOf(InvalidLadderResultException.class)),
            dynamicTest("영어와 숫자로 이루어지지 않은 경우", () -> assertThatThrownBy(() -> new LadderResult("인밸리드"))
                .isExactlyInstanceOf(NotEnglishAndNumberException.class))
        );
    }
}
