package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class TspFactory extends AbstractCandidateFactory<TspSolution> {
    public int dim;
    public TspFactory(int dim) {
        this.dim = dim;
    }
    public TspSolution generateRandomCandidate(Random random) {
        return new TspSolution(dim, random);
    }
}

