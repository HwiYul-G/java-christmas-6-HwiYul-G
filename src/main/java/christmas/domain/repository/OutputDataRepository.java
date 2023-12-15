package christmas.domain.repository;

import christmas.domain.EventResult;
import christmas.domain.data.EventBadge;
import christmas.domain.data.Menu;
import java.util.Collections;
import java.util.List;

public class OutputDataRepository {

    // 샴폐인 증정 이벤트 관련
    private static OutputDataRepository instance;

    private List<EventResult> eventResults;


    private OutputDataRepository() {
    }

    public static OutputDataRepository getInstance() {
        if (instance == null) {
            instance = new OutputDataRepository();
        }
        return instance;
    }

    public List<EventResult> eventResults() {
        return Collections.unmodifiableList(eventResults);
    }


    public int getTotalBenefit() {
        return eventResults.stream()
            .mapToInt(EventResult::benefitPrice)
            .sum();
    }

    public void addEventResult(EventResult eventResult) {
        eventResults.add(eventResult);
    }
}
