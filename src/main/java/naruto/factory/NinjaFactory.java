package naruto.factory;

import naruto.model.*;

import java.util.Arrays;

public abstract class NinjaFactory {
    public abstract Ninja createBasicNinja(String name, Rank rank);

    protected Ninja base(String name, Rank rank, Village village, Stats stats, Jutsu... jutsus) {
        return new Ninja(name, rank, village, stats, Arrays.asList(jutsus));
    }
}
