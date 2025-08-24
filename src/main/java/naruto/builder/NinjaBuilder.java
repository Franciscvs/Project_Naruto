package naruto.builder;

import naruto.model.*;

import java.util.ArrayList;
import java.util.List;

public class NinjaBuilder {
    private String name;
    private Rank rank = Rank.GENIN;
    private Village village = Village.KONOHA;
    private Stats stats = new Stats(50,50,50);
    private final List<Jutsu> jutsus = new ArrayList<>();

    public NinjaBuilder setName(String name) { this.name = name; return this; }
    public NinjaBuilder setRank(Rank rank) { this.rank = rank; return this; }
    public NinjaBuilder setVillage(Village village) { this.village = village; return this; }
    public NinjaBuilder setStats(int attack, int defense, int chakra) { this.stats = new Stats(attack, defense, chakra); return this; }
    public NinjaBuilder addJutsu(Jutsu j) { if (j!=null) this.jutsus.add(j); return this; }

    public Ninja build() {
        if (name == null || name.trim().isEmpty()) throw new IllegalStateException("El ninja debe tener nombre");
        return new Ninja(name, rank, village, stats, new ArrayList<>(jutsus));
    }
}
