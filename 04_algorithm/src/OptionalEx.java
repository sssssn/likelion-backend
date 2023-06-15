import java.util.Optional;

public class OptionalEx {
    public static String nullableMethod() {
        return null;
    }

    public static void main(String[] args) {
        String result = nullableMethod();

        // Optional
        Optional<String> optionalValue = Optional.ofNullable(result);

        if (optionalValue.isPresent()) { // 데이터가 존재할 때
            System.out.println("Value is present: " + optionalValue.get());
        } else { // 데이터가 존재하지 않을 때
            System.out.println("Value is not present");
        }

        // Optional<T>.orElse(T other): 데이터가 존재하지 않을 때 other 를 대신 사용
        System.out.println("Value is: " + optionalValue.orElse("null"));

        String value = "Hello, World!";

        // 데이터를 가지고 있는 Optional 을 만들어 봅시다.
        optionalValue = Optional.of(value);

        // 데이터를 가지고 있는지 판단한 뒤
        if (optionalValue.isPresent()) {
            // 데이터가 있으면 실행하는 코드
            System.out.println("Value is present: " + optionalValue.get());
        } else {
            // 데이터가 없으면 실행하는 코드
            System.out.println("Value is not present");
        }

        // 데이터를 가지지 않는 Optional 을 만들어 봅시다.
        Optional<String> optionalNull = Optional.ofNullable(null);

        // 데이터를 가지고 있는지 판단한 뒤
        if (optionalNull.isPresent()) {
            // 데이터가 있으면 실행하는 코드
            System.out.println("Value is present: " + optionalNull.get());
        } else {
            // 데이터가 없으면 실행하는 코드
            System.out.println("Value is not present");
        }

        // orElse()를 사용하면 기본값을 전달하는 방식으로 활용할 수 있습니다.
        result = optionalNull.orElse("Default Value");
        System.out.println("Value (orElse): " + result);
    }
}
