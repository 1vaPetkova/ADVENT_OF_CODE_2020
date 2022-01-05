package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class D3TobogganTrajectoryPt2 {
    private static final String PATH = "src/Day3/input_day_3.txt";

    public static void main(String[] args) throws IOException {

        long rOneDOne = countTrees(1, 1);
        long   rThreeDOne = countTrees(3, 1);
        long  rFiveDOne = countTrees(5, 1);
        long  rSevenDOne = countTrees(7, 1);
        long  rOneDTwo = countTrees(1, 2);
        System.out.println(rOneDOne * rThreeDOne * rFiveDOne * rSevenDOne * rOneDTwo);
    }


    private static int countTrees(int deltaRight, int deltaDown) throws IOException {
        char[][] map = extendMap(deltaRight);
        int trees = 0;
        int col = deltaRight;
        for (int row = deltaDown; row < map.length; row += deltaDown) {
            if (map[row][col] == '#') {
                trees++;
            }
            col += deltaRight;
        }
        return trees;
    }

    private static char[][] extendMap(int deltaRight) throws IOException {
        List<String> lines = readInput();
        int rows = lines.size();
        int cols = (rows - 1) * deltaRight + 1;
        char[][] map = new char[rows][cols];
        for (int i = 0; i < lines.size(); i++) {
            StringBuilder sb = new StringBuilder(lines.get(i));
            while (sb.length() < cols) {
                sb.append(lines.get(i));
            }
            lines.set(i, sb.toString());
        }
        for (int row = 0; row < map.length; row++) {
            map[row] = lines.get(row).toCharArray();
        }
        return map;
    }

    private static List<String> readInput() throws IOException {
        return Files.readAllLines(Path.of(PATH));
    }
}
