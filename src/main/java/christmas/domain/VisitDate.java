package christmas.domain;

import christmas.util.ExceptionMessage;

public record VisitDate(int visitDate) {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 31;

    public VisitDate {
        validate();
    }

    private void validate(){
        validateMinRange();
        validateMaxRange();
    }

    private void validateMinRange(){
        if(visitDate < MIN_RANGE){
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISITDATE_MIN_RANGE.getMessage());
        }

    }

    private void validateMaxRange(){
        if(visitDate > MAX_RANGE){
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISITDATE_MAX_RANGE.getMessage());
        }
    }

    public static VisitDate of(String input) {
        try{
            return new VisitDate(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_VISITDATE_NUMBER.getMessage());
        }
    }
}
