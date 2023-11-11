package christmas.model;

public enum EventBadge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badge;
    private final int thresholdDiscountAmount;

    EventBadge(final String badge, final int thresholdDiscountAmount) {
        this.badge = badge;
        this.thresholdDiscountAmount = thresholdDiscountAmount;
    }

    public String getBadge() {
        return badge;
    }

    public int getThresholdDiscountAmount() {
        return thresholdDiscountAmount;
    }


}
