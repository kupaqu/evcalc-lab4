package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class TspFitnessFunction implements FitnessEvaluator<TspSolution> {

    TspProblem problem;

    public TspFitnessFunction(TspProblem problem) {
        this.problem = problem;
    }

    public double getFitness(TspSolution solution, List<? extends TspSolution> list) {
        double sum = 0.0;
        for (int i = 0; i<solution.getDim()-1; i++) {
            double[] x1y1 = problem.nodes.get(i);
            double[] x2y2 = problem.nodes.get(i+1);

            sum += dist(x1y1[0], x1y1[1], x2y2[0], x2y2[1]);
        }
        return sum;
    }

    public double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public boolean isNatural() {
        return false;
    }
}
