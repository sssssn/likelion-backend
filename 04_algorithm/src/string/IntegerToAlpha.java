package string;

public class IntegerToAlpha {
    public String itoa(int value) {
        StringBuilder answerBuilder = new StringBuilder();
        // 음수인지 확인
        boolean negative = false;
        if (value < 0) {
            negative = true;
            value *= -1;
        }
        // value 가 0보다 큰 동안
        while (value > 0) {
            // value 를 10으로 나눈 나머지를 문자로 변환
            char digitChar = (char)(value % 10 + '0');
            answerBuilder.append(digitChar);

            // value 나누기 10
            value /= 10;
        }

        if (negative) answerBuilder.append('-');
        return answerBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        IntegerToAlpha itoa = new IntegerToAlpha();
        // MEMO 123456789
        System.out.println(itoa.itoa(1234) + itoa.itoa(56789));
    }
}
