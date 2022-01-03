package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class D2PasswordPhilosophyPt2 {
    private static final String PATH = "src/Day2/input_day2.txt";

    public static void main(String[] args) throws IOException {
        List<String> inputs = Files.readAllLines(Path.of(PATH));
        int validPasswords = 0;
        for (String input : inputs) {
            String[] tokens = input.split(": ");
            String password = tokens[1];
            String[] criteria = tokens[0].split(" ");
            char letter = criteria[1].charAt(0);
            int fIndex = Integer.parseInt(criteria[0].split("-")[0]) - 1;
            int sIndex = Integer.parseInt(criteria[0].split("-")[1]) - 1;
            char f = password.charAt(fIndex);
            char s = password.charAt(sIndex);
            if ((f == letter && s != letter) || (f != letter && s == letter)) {
                validPasswords++;
            }
        }
        System.out.println(validPasswords);
    }

}
