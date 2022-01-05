package Day4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class D4PassportProcessingPt1 {
    private static final String PATH = "src/Day4/input_day_4.txt";

    public static void main(String[] args) throws IOException {
        List<Map<String, Boolean>> passports = readPassports();
        int validPassports = 0;
        for (Map<String, Boolean> passport : passports) {
            if (checkValidPassports(passport)) {
                validPassports++;
            }
        }
        System.out.println(validPassports);

    }

    private static boolean checkValidPassports(Map<String, Boolean> passport) {
        boolean containsAllFields = passport.values().stream().allMatch(value -> value.equals(true));
        boolean cidMissing = passport.get("cid").equals(false);
        boolean allOthersPresent = passport.entrySet().stream().filter(e -> !e.getKey().equals("cid")).
                allMatch(e -> e.getValue().equals(true));
        return containsAllFields || (cidMissing && allOthersPresent);
    }


    private static List<Map<String, Boolean>> readPassports() throws IOException {
        String[] lines = readInput().split("\n\n");
        List<Map<String, Boolean>> passports = new ArrayList<>();
        for (String line : lines) {
            Map<String, Boolean> passportFields = getPassportFields();
            String[] data = line.replaceAll("\n", " ").split("\\s+");
            for (String field : data) {
                field = field.substring(0, field.indexOf(":"));
                if (passportFields.containsKey(field)) {
                    passportFields.put(field, true);
                }
            }
            passports.add(passportFields);
        }
        return passports;
    }

    private static Map<String, Boolean> getPassportFields() {
        Map<String, Boolean> passports = new LinkedHashMap<>();
        passports.put("byr", false);
        passports.put("iyr", false);
        passports.put("eyr", false);
        passports.put("hgt", false);
        passports.put("hcl", false);
        passports.put("ecl", false);
        passports.put("pid", false);
        passports.put("cid", false);
        return passports;
    }

    private static String readInput() throws IOException {
        return Files.readString(Path.of(PATH));
    }


}
