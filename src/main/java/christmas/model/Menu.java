package christmas.model;

public enum Menu {
    MUSHROOM_SOUP(6000, Category.APPETIZER),
    TAPAS(5500, Category.APPETIZER),
    CAESAR_SALAD(8000, Category.APPETIZER),


    T_BONE_STEAK(55000, Category.MAIN),
    BBQ_RIBS(54000, Category.MAIN),
    SEAFOOD_PASTA(35000, Category.MAIN),
    CHRISTMAS_PASTA(25000, Category.MAIN),


    CHOCOLATE_CAKE(15000, Category.DESSERT),
    ICE_CREAM(5000, Category.DESSERT),


    ZERO_COLA(3000, Category.BEVERAGE),
    RED_WINE(60000, Category.BEVERAGE),
    CHAMPAGNE(25000, Category.BEVERAGE);

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
