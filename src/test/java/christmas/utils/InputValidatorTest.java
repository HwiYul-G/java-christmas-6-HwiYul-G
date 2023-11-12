package christmas.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("올바른 입력이 들어가는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1", "타파스-1,시저샐러드-1,바비큐립-1,해산물파스타-1,아이스크림-2,제코롤라-3","양송이수프-4"})
    void verifyValidOrderFormats(String input) {
        assertDoesNotThrow(() -> InputValidator.validateInputOderFormat(input));
    }

    @DisplayName("잘못된 입력이 들어가는 경우")
    @ParameterizedTest
    @ValueSource(strings={"양송이수프,2-타파스,-3", "양송이수프-1,"})
    void verifyInvalidOrderFormats(String input){
        assertThatThrownBy(()->InputValidator.validateInputOderFormat(input))
            .isInstanceOf(IllegalArgumentException.class);
        // TODO : 메시지 확인
    }

}