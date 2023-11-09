package christmas.model;

import christmas.utils.Constants;
import christmas.utils.ExceptionMessage;

public record VisitDate(int visitDate) {

    public VisitDate {
        validate(visitDate);
    }

    private void validate(final int visitDate) {
        validateDateRange(visitDate);
    }

    private void validateDateRange(final int visitDate){
        if (visitDate < Constants.FIRST_DATE || visitDate > Constants.LAST_DATE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

}
