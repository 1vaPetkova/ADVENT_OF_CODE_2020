package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class D3TobogganTrajectoryPt2 {
    private static final String PATH = "src/Day3/input_day_3_ex.txt";

    public static void main(String[] args) throws IOException {
        char[][] map = extendMap();
        System.out.println(countTrees(map));
    }

    private static int countTrees(char[][] map) {
        int trees = 0;
        int col = 3;
        for (int row = 1; row < map.length; row++) {
            if (map[row][col] == '#') {
                trees++;
            }
            col += 3;
        }
        return trees;
    }

    private static char[][] extendMap() throws IOException {
        List<String> lines = readInput();
        int rows = lines.size();
        int cols = (rows - 1) * 3 + 1;
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
