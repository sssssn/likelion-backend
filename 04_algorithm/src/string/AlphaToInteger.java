package string;

public class AlphaToInteger {
    /*숫자로만 이루어진 value 문자열에 대해서
    각 글자를 숫자 데이터로 해석한 뒤
    - 48(0) 하면 숫자가 된다.*/
    public int atoi(String value) {
        int result = 0;
        // 첫 번째 문자를 사전에 확인
        boolean negative = false;
        int i = 0; // 선언 시 for 문 i 사용 안해도 됨
        // 첫 번째 문자가 '-' 인지?
        if (value.charAt(i) == '-') {
            // 맞다면 미리 기록
            negative = true;
            // 두 번째 글자부터 확인
            i++;
        }

        // 문자열을 한 글자(한 자리)씩 확인
        for (/*i = 0*/; i < value.length(); i++) {
            // 자릿수 변환
            result *= 10;
            // 글자를 숫자로 변환
            result += value.charAt(i) - 48;
//            result += value.charAt(i) - '0';'
        }
        if (negative) result *= -1;
        return result;
//        return negative ? result * -1 : result;
    }

    public static void main(String[] args) {
        AlphaToInteger atoi = new AlphaToInteger();
        System.out.println(atoi.atoi("12345") + atoi.atoi("-4291"));
        System.out.println(atoi.atoi("-4291"));
    }
}
