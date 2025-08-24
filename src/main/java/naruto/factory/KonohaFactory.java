package naruto.factory;

import naruto.model.*;

public class KonohaFactory extends NinjaFactory {
    @Override
    public Ninja createBasicNinja(String name, Rank rank) {
        // Konoha suele destacar en equilibrio y ninjutsu versátil
        Stats stats = new Stats(60, 55, 70);
        Jutsu shadowClone = new Jutsu("Kage Bunshin", Element.NINJUTSU, 20, 10);
        Jutsu rasenganLite = new Jutsu("Rasengan (básico)", Element.NINJUTSU, 35, 25);
        return base(name, rank, Village.KONOHA, stats, shadowClone, rasenganLite);
    }
}
