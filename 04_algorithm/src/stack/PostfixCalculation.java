package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// Reverse Polish Notation -> Postfix Notation
public class PostfixCalculation {

    public void solution() throws IOException {
        // 입력 처리
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        Stack<Integer> digitStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char token = input.charAt(i);

            // token 이 숫자가 표현된 글자인지 판단하는 메서드
            if (Character.isDigit(token)) {
                // 숫자니까 변환 후 push
                digitStack.push(Character.digit(token, 10));
//                digitStack.push(token - '0'); // token 을 int 로 변환 => token - '0'
            }
            // 숫자가 아니라면,
            else {
                //  (연산자) 스택에서 2번 pop 하고 계산한다.
                int numRight = digitStack.pop();
                int numLeft = digitStack.pop();
                switch (token) {
                    case '+':
                        digitStack.push(numLeft + numRight);
                        break;
                    case '-':
                        digitStack.push(numLeft - numRight);
                        break;
                    case '*':
                        digitStack.push(numLeft * numRight);
                        break;
                    case '/':
                        digitStack.push(numLeft / numRight);
                        break;
                    default:
                        throw new IllegalArgumentException("invalid operator");
                }
            }
        }
        int answer = digitStack.pop();
        if (digitStack.empty())
            System.out.println(answer);
        else System.out.println("error");
    }
        // MEMO 531*+49-3*+
        public static void main (String[]args) throws IOException {
            new PostfixCalculation().solution();
        }
    }
