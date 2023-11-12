package christmas.model;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(6_000, Category.APPETIZER, "양송이수프"),
    TAPAS(5_500, Category.APPETIZER, "타파스"),
    CAESAR_SALAD(8_000, Category.APPETIZER, "시저샐러드"),


    T_BONE_STEAK(55_000, Category.MAIN, "티본스테이크"),
    BBQ_RIBS(54_000, Category.MAIN, "바비큐립"),
    SEAFOOD_PASTA(35_000, Category.MAIN, "해산물파스타"),
    CHRISTMAS_PASTA(25_000, Category.MAIN, "크리스마스파스타"),


    CHOCOLATE_CAKE(15_000, Category.DESSERT, "초코케이크"),
    ICE_CREAM(5_000, Category.DESSERT, "아이스크림"),


    ZERO_COLA(3_000, Category.BEVERAGE, "제로콜라"),
    RED_WINE(60_000, Category.BEVERAGE, "레드와인"),
    CHAMPAGNE(25_000, Category.BEVERAGE, "샴페인");

    private final int price;
    private final Category category;
    private final String foodName;

    Menu(final int price, final Category category, final String foodName) {
        this.price = price;
        this.category = category;
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public String getFoodName() {
        return foodName;
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

    public static Menu getMenuByName(String foodName) {
        return Arrays.stream(Menu.values())
            .filter(menu -> menu.getFoodName().equals(foodName))
            .findFirst()
            .orElseThrow(IllegalStateException::new);
    }

}
