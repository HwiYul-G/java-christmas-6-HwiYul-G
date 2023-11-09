package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.utils.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("유효하지 않은 방문 날짜 범위에 대한 예외 처리")
    @ValueSource(ints = {0, 32, -1})
    @ParameterizedTest
    void invalidRangeVisitDate(Integer input) {
        assertThatThrownBy(() -> new VisitDate(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
    }

}