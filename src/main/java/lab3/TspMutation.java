package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TspMutation implements EvolutionaryOperator<TspSolution> {

    private int mode;
    private double probs;

    public TspMutation(int mode, double probs) {
        this.mode = mode;
        this.probs = probs;
    }
    public List<TspSolution> apply(List<TspSolution> population, Random random) {

        for(TspSolution individual : population) {
            if (random.nextDouble() < probs) {
                if (mode == 1) swap(individual, random);
                else if (mode == 2) inverse(individual, random);
                else if (mode == 3) scramble(individual, random);
            }
        }

        return population;
    }

    private void swap(TspSolution solution, Random random) {
        int dim = solution.getDim();
        int i = random.nextInt(dim);
        int j;
        do {
            j = random.nextInt(dim);
        } while (i == j);

        Collections.swap(solution.route, i, j);
    }

    private void inverse(TspSolution solution, Random random) {
        int dim = solution.getDim();
        int a = random.nextInt(dim);
        int b;
        do {
            b = random.nextInt(dim);
        } while (a == b);

        int i, j;
        if (a < b) {
            i = a;
            j = b;
        } else {
            i = b;
            j = a;
        }

        while(i < j) {
            Collections.swap(solution.route, i, j);
            i++;
        }
    }

    private void scramble(TspSolution solution, Random random) {
        int dim = solution.getDim();
        int a = random.nextInt(dim);
        int b;
        do {
            b = random.nextInt(dim);
        } while (a == b);

        int i, j;
        if (a < b) {
            i = a;
            j = b;
        } else {
            i = b;
            j = a;
        }

        Collections.shuffle(solution.route.subList(i, j), random);
    }
}
