package christmas.domain;

import christmas.domain.data.Menu;
import christmas.util.ExceptionMessage;
import christmas.util.Util;
import java.util.List;

public record Order(Menu menu, int quantity) {

    private static final int MIN_QUANTITY = 1;

    public Order{
        validate();
    }

    private void validate(){
        validateQuantity();
    }

    private void validateQuantity(){
        if(quantity < MIN_QUANTITY){
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER_QUANTITY.getMessage());
        }
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public static Order of(String input){
        // input은 메뉴이름-수량 형태로 들어온다.
        // 메뉴이름과 수량을 분리해서 Order 객체를 생성한다.
        List<String> orderData= Util.splitByHyphen(input);
        Menu menu = Menu.of(orderData.get(0));
        int quantity = Integer.parseInt(orderData.get(1));
        return new Order(menu, quantity);
    }

}
