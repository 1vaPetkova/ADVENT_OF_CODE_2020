package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class D1ReportRepairPt2 {
    private static final String PATH = "src/Day1/input_day_1_ex.txt";

    public static void main(String[] args) throws IOException {
        List<Long> expenses = Arrays.stream(Files.readString(Path.of(PATH)).split(System.lineSeparator()))
                .map(Long::parseLong).toList();
        long first = 0;
        long second = 0;
        long third = 0;
        boolean isFound = false;
        for (int i = 0; i < expenses.size(); i++) {
            for (int j = i + 1; j < expenses.size(); j++) {
                for (int k = j + 1; k < expenses.size(); k++) {
                    if (expenses.get(i) + expenses.get(j) + expenses.get(k) == 2020) {
                        first = expenses.get(i);
                        second = expenses.get(j);
                        third = expenses.get(k);
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    break;
                }
            }
            if (isFound) {
                break;
            }
        }
        long result = first * second * third;
        System.out.println(result);
    }
}
