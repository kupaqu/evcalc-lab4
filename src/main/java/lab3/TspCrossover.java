package lab3;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TspCrossover extends AbstractCrossover<TspSolution> {
    protected TspCrossover() {
        super(1);
    }

    protected List<TspSolution> mate(TspSolution p1, TspSolution p2, int n, Random random) {
        ArrayList<TspSolution> children = new ArrayList<>();

        children.add(orderedCrossover(p1, p2, n, random));
        children.add(orderedCrossover(p2, p1, n, random));

        return children;
    }
    protected TspSolution orderedCrossover(TspSolution p1, TspSolution p2, int n, Random random) {
        int dim = p1.getDim();
        int a = random.nextInt(dim - n);
        int b = a + n;

        return orderedCrossover(p1, p2, a, b);
    }
    protected static TspSolution orderedCrossover(TspSolution p1, TspSolution p2, int a, int b) {
        int dim = p1.getDim();

        List<Integer> sharedMiddle = new ArrayList<>();
        for (int i = a; i < b; i++) {
            sharedMiddle.add(p1.route.get(i));
        }

        List<Integer> sharedRight = new ArrayList<>();
        List<Integer> sharedLeft = new ArrayList<>();

        for (int i = b; i < dim; i++) {
            int point = p2.route.get(i);
            int sizeRight = sharedRight.size();
            int sizeLeft = sharedLeft.size();
            if (!sharedMiddle.contains(point)) {
                if (sizeRight < dim - b) sharedRight.add(point);
                else if (sizeLeft < a) sharedLeft.add(point);
            }
        }

        for (int i = 0; i < b; i++) {
            int point = p2.route.get(i);
            int sizeRight = sharedRight.size();
            int sizeLeft = sharedLeft.size();
            if (!sharedMiddle.contains(point)) {
                if (sizeRight < dim - b) sharedRight.add(point);
                else if (sizeLeft < a) sharedLeft.add(point);
            }
        }

        List<Integer> shared = Stream.concat(sharedLeft.stream(), sharedMiddle.stream()).collect(Collectors.toList());
        shared = Stream.concat(shared.stream(), sharedRight.stream()).collect(Collectors.toList());

        return new TspSolution(shared);
    }
}
