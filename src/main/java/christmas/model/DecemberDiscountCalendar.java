package christmas.model;

import christmas.model.Menu.Category;

public enum DecemberDiscountCalendar {
    DEC_1(Category.MAIN, false, 1000),
    DEC_2(Category.MAIN, false, 1100),
    DEC_3(Category.DESSERT, true, 1200),
    DEC_4(Category.DESSERT, false, 1300),
    DEC_5(Category.DESSERT, false, 1400),
    DEC_6(Category.DESSERT, false, 1500),
    DEC_7(Category.DESSERT, false, 1600),
    DEC_8(Category.MAIN, false, 1700),
    DEC_9(Category.MAIN, false, 1800),
    DEC_10(Category.DESSERT, true, 1900),
    DEC_11(Category.DESSERT, false, 2000),
    DEC_12(Category.DESSERT, false, 2100),
    DEC_13(Category.DESSERT, false, 2200),
    DEC_14(Category.DESSERT, false, 2300),
    DEC_15(Category.MAIN, false, 2400),
    DEC_16(Category.MAIN, false, 2500),
    DEC_17(Category.DESSERT, true, 2600),
    DEC_18(Category.DESSERT, false, 2700),
    DEC_19(Category.DESSERT, false, 2800),
    DEC_20(Category.DESSERT, false, 2900),
    DEC_21(Category.DESSERT, false, 3000),
    DEC_22(Category.MAIN, false, 3100),
    DEC_23(Category.MAIN, false, 3200),
    DEC_24(Category.DESSERT, true, 3300),
    DEC_25(Category.DESSERT, true, 3400),
    DEC_26(Category.DESSERT, false, 0),
    DEC_27(Category.DESSERT, false, 0),
    DEC_28(Category.DESSERT, false, 0),
    DEC_29(Category.MAIN, false, 0),
    DEC_30(Category.MAIN, false, 0),
    DEC_31(Category.DESSERT, true, 0);


    private final Category weekdayDiscountCategory;
    private final boolean isSpecialDiscount;
    private final int christmasDdayDiscount;

    DecemberDiscountCalendar(final Category weekdayDiscountCategory,
        final boolean isSpecialDiscount,
        final int christmasDdayDiscount) {
        this.weekdayDiscountCategory = weekdayDiscountCategory;
        this.isSpecialDiscount = isSpecialDiscount;
        this.christmasDdayDiscount = christmasDdayDiscount;
    }

    public Category getWeekdayDiscountCategory() {
        return weekdayDiscountCategory;
    }

    public boolean isSpecialDiscount() {
        return isSpecialDiscount;
    }

    public int getChristmasDdayDiscount() {
        return christmasDdayDiscount;
    }
}
