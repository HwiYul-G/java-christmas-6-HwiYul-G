package christmas.model;

import christmas.utils.ExceptionMessage;

public record VisitDate(int visitDate) {

    private static final int FIRST_DATE = 1;
    private static final int LAST_DATE = 31;

    public VisitDate {
        validate(visitDate);
    }

    private void validate(final int visitDate) {
        validateDateRange(visitDate);
    }

    private void validateDateRange(final int visitDate){
        if (visitDate < FIRST_DATE || visitDate > LAST_DATE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISIT_DATE.getMessage());
        }
    }

}
