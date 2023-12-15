package christmas.domain.data;

import christmas.util.ExceptionMessage;

public enum Category {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");


    private final String name;
    Category(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category of(String name){
        for(Category category : Category.values()){
            if(category.name.equals(name)){
                return category;
            }
        }
        throw new IllegalArgumentException(ExceptionMessage.INVALID_CATEGORY.getMessage());
    }
}
