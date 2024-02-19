package lab3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class TspProblem {
    public ArrayList<int[]> nodes;

    public TspProblem(String path) {
        this.nodes = new ArrayList<int[]>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("NODE_COORD_SECTION")) {
                    break;
                }
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("EOF")) {
                    break;
                }
                String[] splitted = line.trim().split(" ");
                int[] coords = Arrays.stream(splitted).skip(1).mapToInt(Integer::parseInt).toArray();
                nodes.add(coords);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDim() {
        return nodes.size();
    }
}