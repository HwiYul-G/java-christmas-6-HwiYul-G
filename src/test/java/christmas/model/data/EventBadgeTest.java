package christmas.model.data;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBadgeTest {

    @DisplayName("5000원 미만은 받을 배지가 없다.")
    @Test
    void verifyDetermineCorrectBadgeUnder5000(){
        int discountAmount = 1000;
        EventBadge expectedBadge = EventBadge.NONE;
        assertThat(EventBadge.getBadgeByDiscountAmount(discountAmount)).isEqualTo(expectedBadge);
    }

    @DisplayName("5000원 이상 10000원 미만의 금액에 해당하는 배지 - 중간 범위")
    @Test
    void verifyDetermineCorrectBadgeBetween5000to10000(){
        int discountAmount = 6000;
        EventBadge expectedBadge = EventBadge.STAR;
        assertThat(EventBadge.getBadgeByDiscountAmount(discountAmount)).isEqualTo(expectedBadge);
    }

}