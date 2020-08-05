# kotlin-lotto

## Step1. 문자열 계산기 TDD

1. ~~실패하는 테스트 코드 작성~~
2. ~~테스트를 통과시키는 코드를 빠르게 작성(죄악 허용)~~
3. ~~테스트 통과를 유지하면서 구현 & 리팩토링~~

* 피드백1
1. ~~리터럴 0을 상수 ZERO로 바꾼다~~
2. ~~Regex 객체 생성은 비싼 행위이기 때문에 미리 만들어서 사용한다~~
3. ~~getNumbers함수의 delimiters인자를 없애본다~~
4. ~~Number 클래스를 만든다~~
5. ~~상태를 가지는 클래스는 싱글턴으로 만들지 않는다~~

## Step2. 로또(자동)

1. ~~빠르게 구현해서 도메인 지식을 익힌다~~
2. ~~실패하는 테스트 코드를 먼저 작성한다~~
3. ~~테스트 코드를 통과시키는 코드를 빠르게 작성한다~~
4. ~~테스트 통과를 유지하면서 리팩토링한다~~

1. 여러 customer를 만들 경우 enum의 count가 초기화 되지 않는다. 하나의 customer가 하나의 count를 가질 수 있도록 변경해야 한다.
    1. count는 enum이 가지는게 맞는 것 같다.
    2. customer는 winLottoCount를 했을 때 자기가 가지고 있는 로또들이 몇등에 당첨되었는지 알 수 있도록 List<WinLotto>타입을 리턴한다.
        * 이걸 고객이 로또객체에게 직접 시키는 것이 맞는 것인가?
        * LottoMarket의 의미는? -> 그렇다고 고객이 자기가 몇개 맞았는지 Market한테 시키나?
        * 일단 직접 시키는 것으로 가자
    3. 2에서 받은 List<WinLotto>를 돌면서 WinLotto에 count를 시킨다. 다 돌았으면 WinLotto.resultList()를 호출한다.
        * 이러면 이전 로직이 그대로 사용되는데..
    4. enum객체의 count를 초기화 한다
2. Lotto의 winCount의 리턴타입이 Unit이기 때문에 테스트하기 어렵고 이해하기 어려운 코드가 만들어졌다. 위 count로직이 분리되면 고민해서 바꿔본다.
    1. 위와 같이 변경되면 WinLotto 타입을 리턴하도록 변경할 수 있다.
3. WinLottoTest의 각 테스트를 @Order를 사용하지 말고 독립적으로 실행가능하게 한다.
    1. enum의 plusCount 자체가 문제인가?
