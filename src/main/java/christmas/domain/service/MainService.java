package christmas.domain.service;

import christmas.domain.repository.InputDataRepository;
import christmas.domain.repository.OutputDataRepository;
import java.util.List;

public class MainService {

    private final List<Event> events;
    private final InputDataRepository inputDataRepository;
    private final OutputDataRepository outputDataRepository;

    public MainService(List<Event> events, InputDataRepository inputDataRepository,
        OutputDataRepository outputDataRepository) {
        this.events = events;
        this.inputDataRepository = inputDataRepository;
        this.outputDataRepository = outputDataRepository;
    }

    public void processEvents() {
        for (Event event : events) {
            outputDataRepository.addEventResult(
                event.calculateEventResult(inputDataRepository.getVisitDate(),
                    inputDataRepository.getOrders()));
        }
    }


}
