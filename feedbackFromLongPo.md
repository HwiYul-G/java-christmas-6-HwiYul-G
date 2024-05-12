- `christmas.controller.OrderController`
- `christmas.view.InputView`
- `christmas.view.OutputView`

보통 MVC를 보면 웹도 그렇고 앱도 그렇고 쌍방향인 경우가 많은데, 컨트롤러가 뷰를 참조하고 뷰는 컨트롤러를 모르는 상황은 일반적이지 않은 것 같습니다. 보통 뷰의 함수가 이벤트 핸들러에 의해 호출되고, 그 뷰의 함수가 컨트롤러의 함수를 호출하는 식의 구조로 이루어져있습니다.
다만 간단한 콘솔앱인 것을 고려하면 이벤트 핸들러에 해당하는 부분을 상상하기 힘들고, 사실 콘솔앱이니만큼 전형적인 구조가 따로 없어 이 부분에서 다양한 선택지를 생각해볼 수 있습니다. 예를 들어,

```java
interface View {
    String getName();
    boolean isWaitingForInput();
    String getCurrentMessage();
    void setInput(ViewManager manager, String message);
}

class ViewManager {
    @Nonnull
    private List<View> views;
    @Nonnull
    private View focusedView;

    public ViewManager(String initialViewName, List<View> views) {
        this.views = views;
        open(initialViewName);
    }

    public void run() {
        while (focusedView.isWaitingForInput()) {
        String input = Console.readLine();
        focusedView.setInput(this, input);
        }
    }

    public void open(String viewName) {
        focusedView = findViewByName(viewName);
    }

    @Nonnull
    private View findViewByName(String viewName) {
        for (View view : views) {
            if (view.getName().equals(viewName)) {
                return view;
            }
        }
        throw new NullPointerException();
    }
}
```

이런 식으로 뷰 매니저를 만든 뒤,
```java
class InputView implements View {
    @Nonnull
    private InputViewController viewController;

    public InputView(@Nonnull InputViewController viewController) {
        this.viewController = viewController;
    }

    ...

    @Override
    String getName() { return "InputView"; }

    // 이벤트 핸들러에 해당
    @Override
    void setInput(ViewManager manager, String message) {
        if (visitDate == null) {
            handleVisitDate(manager, message);
        } else if (orderItems == null) {
            handleOrderItems(manager, message);
        }
    }

    ...

    @Override
    void handleOrderItems(ViewManager manager, String message) {
        ...
        viewController.setOrderItems(InputParser.parseToOrderItems(message));
        manager.open("OutputView");
    }
}
```

이런 식으로 `InputView`와 `OutputView`를 구현해서,

```java
class Application {
    public static void main(String[] args) {
        // 데이터베이스의 역할을 할 클래스입니다. 이번 앱은 간단해서 생략된 것으로 보입니다.
        // 구현체는 인프라 레이어에 있습니다. `ApplicationState` 인터페이스는 불러오기
        // 및 저장 함수만 갖고 있고, 구현체에 따라 그 정보가 저장되는 공간이 달라집니다.
        ApplicationState state = new JsonApplicationState("state.json");

        // 인프라 레이어의 구현을 컨트롤러에 DI로 삽입합니다.
        InputController inputController = new InputController(state);
        OutputController outputController = new OutputController(state);

        // 컨트롤러를 참조하는 뷰를 만듭니다.
        List<View> views = Arrays.asList(
            new InputView(inputController),
            new OutputView(outputController)
        );

        // 뷰 내 이벤트 핸들러를 호출해줄 클래스입니다.
        ViewManager viewManager = new ViewManager("InputView", views);
        viewManager.run();
    }
}
```

이런 식으로 작동하게 만들 수 있습니다.

지금 당장은 앱이 간단해서 `OrderController.run` 내 지역변수만으로 앱의 모든 상태를 저장할 수 있지만, 앱이 더
커짐에 따라 위 예시의 `ApplicationState`와 같은 역할을 할 클래스/인터페이스가 필요해질 것입니다.

- `christmas.model.calculator.ChampagneComplimentaryEvent`

샴페인 무료 증정 이벤트는 `FreeChampagneEvent` 정도가 적당한 것 같습니다.

- `christmas.model.calculator.DiscountCalculator`

`Discount`로 이름을 줄일 수 있을 것 같습니다.

- `christmas.model.calculator.OrderCalculator`

`isEligibleForChampagne`의 로직이 `ChampagneComplimentaryEvent.isEligibleForChampagneComplimentaryEvent`의 로직과 동일합니다. `DiscountCalculator`의 모든 구현체에 `EVENT_NAME`이 있는 만큼, 각 이벤트별 특수 텍스트 표시 기능을
`DiscountCalculator`에 포함시킬 수 있습니다. `DiscountCalculator`라는 이름에 `Calculator`가 들어가있어 그렇게 리팩토링하면 이름과 어울리지 않는 느낌이 듭니다. `DiscountCalculator`를 `Discount`로만 바꾸어도 `isEligibleForChampagne`을 더 알맞은 곳에 넣을 수 있는 환경이 만들어질 것 같습니다.

- `christmas.model.data.EventBadge`

`determineBadge`의 `determine`은 일상생활에서 흔히 사용되는 동사이긴 하지만, 프로그래밍에선 그렇게 자주 쓰이는 함수명은 아닙니다. 잘 생각해보면 `getBadge`와 동의어임을 알 수 있습니다. 어차피 파라미터 이름이 있으니 컨벤션에 따라 다르게 할 수 있지만, `getBadgeByDiscountAmount` 정도가 괜찮을지 모릅니다.

- `christmas.model.data.Menu`

(이 부분은 `EventBadge`에도 적용할 수 있습니다.) 메뉴가 하드코딩되어있습니다. 컨트롤러에서 적절한 인프라 구현체를 가져오는 DI 로직이 있었다면 메뉴나 Badge 정보를 파일에 저장하고 컨트롤러에서 인프라 구현체를 통해 읽어오는 로직을 구현할 수 있습니다.

- `christmas.utils`

`christmas.utils` 패키지 내 클래스는 크게 두가지 역할을 합니다.
1. `InputView`에서 사용할 것들
2. 날짜, 개수, 가격 등에서 사용하는 정수 상수

이 두가지는 작은 규모에선 한곳에 모을만하나, 큰 규모에선 더 알맞은 위치가 있습니다. `InputView`의 경우, 문자열로부터 익셉션을 만들고 던지는 함수는 `View` 근처의 `utils`로 넣을 수 있고, 정수 상수의 경우는 각 상수가 실제로 쓰이는 곳에 넣을 수 있습니다. 예를 들어, `MINIMUM_ORDER_QUANTITY`나 `MAXIMUM_TOTAL_ORDER_QUANTITY`는 `OrderItems`에 선언하는 것이 더 적절할지도 모릅니다.

- 그 외

위에 있는 코멘트들은 이 프로그램에 기능이 더 추가될 때 필요할지 모르는 구조적 변화에 대한 서술이니, 규모에 따라선 오히려 위와 같은 방법을 채택한 경우 개발하기 더 불편한 상황이 발생할 수도 있습니다. 위와 같은 구조는 앞으로 앱이나 웹 개발을 시작하시면 금방 체화하실 것입니다.

전체적으로 `Stream`을 이용하여 함수형 프로그래밍이 적용된 점이나, `record`가 적절히 사용된 점이 좋습니다.

유닛테스트도 적절하게 잘 작성하셨습니다. 다만 유닛테스트 앞에 `verify`나 `canNot`이 계속 붙는점은 고민할 여지가 있는 것 같습니다. 저는 유닛테스트 이름을 (다른 언어라는 점을 감안해주세요)

`(테스트하는 함수이름)_(조건)_(예상되는 행동)`

이라는 형태로 짓습니다. 예를 들어, `OrderItemTest.canNotOrderNonExistingMenu` 같은 경우 저는

`constructor_nonExistingMenu_throwsIllegalArgumentException` 같이 지을 것 같습니다. "can not"과 "cannot" 둘 다 영문법적으로 괜찮으니, `cannot`이라 사용하실 수 있습니다. (Formal한 글쓰기일수록 "cannot"이 더 많이 쓰입니다.)