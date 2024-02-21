package lab3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class TspProblem {
    public ArrayList<double[]> nodes;

    public TspProblem(String path) {
        this.nodes = new ArrayList<double[]>();
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
                double[] coords = Arrays.stream(splitted).skip(1).mapToDouble(Double::parseDouble).toArray();
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