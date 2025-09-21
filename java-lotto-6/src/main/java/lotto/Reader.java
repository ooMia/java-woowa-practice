package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import camp.nextstep.edu.missionutils.Console;

class Reader {
    public int readInt() {
        while (true) {
            try {
                return _readInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Integer> readIntegers(String delimiter) {
        while (true) {
            try {
                return _readIntegers(delimiter);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int _readInt() {
        try {
            String input = Console.readLine();
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닌 값이 입력되었습니다.");
        } catch (Exception e) {
            return -1;
        }
    }

    private List<Integer> _readIntegers(String delimiter) {
        try {
            String input = Console.readLine();
            StringTokenizer st = new StringTokenizer(input, delimiter);
            List<Integer> numbers = new ArrayList<>();
            while (st.hasMoreTokens()) {
                numbers.add(Integer.parseInt(st.nextToken().trim()));
            }
            return numbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닌 값이 입력되었습니다.");
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}