package naruto.service;

import naruto.model.Ninja;

public class TrainingService {
    public void train(Ninja ninja, int attackDelta, int defenseDelta, int chakraDelta) {
        ninja.train(attackDelta, defenseDelta, chakraDelta);
    }
}
