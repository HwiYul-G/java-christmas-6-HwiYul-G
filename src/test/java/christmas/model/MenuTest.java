package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("메뉴판에 존재하지 않는 메뉴는 IllegalStateException이 발생한다.")
    @Test
    void canNotRetrieveNonExistingMenu() {
        String nonExistingMenuName = "감자튀김";
        assertThatThrownBy(() -> Menu.getMenuByName(nonExistingMenuName)).isInstanceOf(
            IllegalStateException.class);
    }

}