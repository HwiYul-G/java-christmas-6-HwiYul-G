package christmas.controller.status;

public enum ApplicationStatus {
    INPUT_DATA,
    OUTPUT_DATA,
    EXIT;

    public boolean runnable() {
        return this != ApplicationStatus.EXIT;
    }
}
