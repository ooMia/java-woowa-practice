package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.ExceptionHandler.ErrorCode;

class Reader {
    int readInt() {
        try {
            String input = Console.readLine();
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw ErrorCode.INVALID_INTEGER_INPUT.toException();
        }
    }

    List<Integer> readIntegers(String delimiter) {
        try {
            String input = Console.readLine();
            StringTokenizer st = new StringTokenizer(input, delimiter);
            List<Integer> numbers = new ArrayList<>();
            while (st.hasMoreTokens()) {
                numbers.add(Integer.parseInt(st.nextToken().trim()));
            }
            return numbers;
        } catch (NumberFormatException e) {
            throw ErrorCode.INVALID_INTEGER_INPUT.toException();
        }
    }
}