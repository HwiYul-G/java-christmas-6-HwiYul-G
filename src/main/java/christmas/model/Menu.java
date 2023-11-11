package christmas.model;

public enum Menu {
    MUSHROOM_SOUP(6_000, Category.APPETIZER),
    TAPAS(5_500, Category.APPETIZER),
    CAESAR_SALAD(8_000, Category.APPETIZER),


    T_BONE_STEAK(55_000, Category.MAIN),
    BBQ_RIBS(54_000, Category.MAIN),
    SEAFOOD_PASTA(35_000, Category.MAIN),
    CHRISTMAS_PASTA(25_000, Category.MAIN),


    CHOCOLATE_CAKE(15_000, Category.DESSERT),
    ICE_CREAM(5_000, Category.DESSERT),


    ZERO_COLA(3_000, Category.BEVERAGE),
    RED_WINE(60_000, Category.BEVERAGE),
    CHAMPAGNE(25_000, Category.BEVERAGE);

    private final int price;
    private final Category category;

    Menu(final int price, final Category category) {
        this.price = price;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBeverage() {
        return category == Category.BEVERAGE;
    }

    public Category getCategory() {
        return category;
    }

    public enum Category {
        APPETIZER, MAIN, DESSERT, BEVERAGE
    }
}
