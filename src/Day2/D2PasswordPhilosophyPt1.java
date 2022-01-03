package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D2PasswordPhilosophyPt1 {
    private static final String PATH = "src/Day2/input_day2.txt";

    public static void main(String[] args) throws IOException {
        List<String> inputs = Files.readAllLines(Path.of(PATH));
        int validPasswords = 0;
        for (String input : inputs) {
            String[] tokens = input.split(": ");
            String password = tokens[1];
            String[] criteria = tokens[0].split(" ");
            String letter = criteria[1];
            int start = Integer.parseInt(criteria[0].split("-")[0]);
            int end = Integer.parseInt(criteria[0].split("-")[1]);
            Pattern pattern = Pattern.compile(letter);
            Matcher matcher = pattern.matcher(password);
            int occurrences = 0;
            while (matcher.find()) {
                occurrences++;
            }
            if (occurrences >= start && occurrences <= end) {
                validPasswords++;
            }
        }
        System.out.println(validPasswords);
    }

}
