package Day4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class D4PassportProcessingPt2 {
    private static final String PATH = "src/Day4/input_day_4.txt";

    public static void main(String[] args) throws IOException {
        List<Map<String, String>> passportsData = getPassportsData();
        int valid = 0;
        passportsData = passportsData.stream()
                .filter(D4PassportProcessingPt2::containsNeededFields)

//                .filter(D4PassportProcessingPt2::checkFieldsData)
                .collect(Collectors.toList());
        for (Map<String, String> passportsDatum : passportsData) {
            if (checkFieldsData(passportsDatum)) {
                valid++;
            }
        }

        System.out.println(valid);

    }


    private static boolean containsNeededFields(Map<String, String> passport) {
        boolean containsAllFields = passport.values().stream().noneMatch(value -> value.equals(""));
        boolean cidMissing = passport.get("cid").equals("");
        boolean allOthersPresent = passport.entrySet().stream().filter(e -> !e.getKey().equals("cid")).
                noneMatch(e -> e.getValue().equals(""));

        return containsAllFields || (cidMissing && allOthersPresent);
    }

    private static boolean checkFieldsData(Map<String, String> passport) {
        String byr = passport.get("byr");
        if (byr.length() != 4 || !areAllDigits(byr) || numberValueOf(byr) < 1920 || numberValueOf(byr) > 2002) {
            return false;
        }

        String iyr = passport.get("iyr");
        if (iyr.length() != 4 || !areAllDigits(iyr) || numberValueOf(iyr) < 2010 || numberValueOf(iyr) > 2020) {
            return false;
        }

        String eyr = passport.get("eyr");
        if (eyr.length() != 4 || !areAllDigits(eyr) || numberValueOf(eyr) < 2020 || numberValueOf(eyr) > 2030) {
            return false;
        }

        String hgt = passport.get("hgt");
        if (!hgt.endsWith("cm") && !hgt.endsWith("in")) {
            return false;
        }
        String value = hgt.substring(0, hgt.length() - 2);
        if (!areAllDigits(value)) {
            return false;
        }
        int height = Integer.parseInt(value);
        if (hgt.endsWith("cm")) {
            if (height < 150 || height > 193) {
                return false;
            }
        } else if (hgt.endsWith("in")) {
            if (height < 59 || height > 76) {
                return false;
            }
        }

        String hcl = passport.get("hcl");
        Pattern pattern = Pattern.compile("^#[0-9a-f]{6}$");
        Matcher matcher = pattern.matcher(hcl);
        if (!matcher.find()) {
            return false;
        }

        String ecl = passport.get("ecl");
        if (getEyeColours().stream().noneMatch(c -> c.equals(ecl))) {
            return false;
        }

        String pid = passport.get("pid");
        if (pid.length() != 9 || !areAllDigits(pid)) {
            return false;
        }

        return true;
    }

    private static List<String> getEyeColours() {
        return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    }


    private static int numberValueOf(String byr) {
        return Integer.parseInt(byr);
    }

    private static boolean areAllDigits(String byr) {
        char[] chars = byr.toCharArray();
        for (char ch : chars) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }


    private static List<Map<String, String>> getPassportsData() throws IOException {
        String[] lines = readInput().split("\n\n");
        List<Map<String, String>> passports = new ArrayList<>();
        for (String line : lines) {
            Map<String, String> passportFields = getPassportDataFields();
            String[] data = line.replaceAll("\n", " ").split("\\s+");
            for (String info : data) {
                String field = info.split(":")[0];
                String fieldInfo = info.split(":")[1];
                if (passportFields.containsKey(field)) {
                    passportFields.put(field, fieldInfo);
                }
            }
            passports.add(passportFields);
        }
        return passports;
    }

    private static Map<String, String> getPassportDataFields() {
        Map<String, String> passports = new LinkedHashMap<>();
        passports.put("byr", "");
        passports.put("iyr", "");
        passports.put("eyr", "");
        passports.put("hgt", "");
        passports.put("hcl", "");
        passports.put("ecl", "");
        passports.put("pid", "");
        passports.put("cid", "");
        return passports;
    }

    private static String readInput() throws IOException {
        return Files.readString(Path.of(PATH));
    }


}
