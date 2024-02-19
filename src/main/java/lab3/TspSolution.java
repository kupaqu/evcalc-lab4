package lab3;

import java.util.Random;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;

public class TspSolution {
    public List<Integer> route;
    public TspSolution(int dim, Random random) {
        route = IntStream.range(0, dim).boxed().collect(Collectors.toList());
        Collections.shuffle(route);
    }
}
