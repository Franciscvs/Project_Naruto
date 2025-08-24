package naruto.model;

import naruto.visitor.ExportVisitor;
import naruto.visitor.Visitable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ninja implements Visitable {
    private final String name;
    private final Rank rank;
    private final Village village;
    private final Stats stats;
    private final List<Jutsu> jutsus = new ArrayList<>();

    public Ninja(String name, Rank rank, Village village, Stats stats, List<Jutsu> initialJutsus) {
        this.name = name;
        this.rank = rank;
        this.village = village;
        this.stats = stats;
        if (initialJutsus != null) this.jutsus.addAll(initialJutsus);
    }

    public String getName() { return name; }
    public Rank getRank() { return rank; }
    public Village getVillage() { return village; }
    public Stats getStats() { return stats; }
    public List<Jutsu> getJutsus() { return Collections.unmodifiableList(jutsus); }

    public void addJutsu(Jutsu j) { if (j != null) jutsus.add(j); }

    public void train(int atk, int def, int chak) { stats.increase(atk, def, chak); }

    @Override
    public void accept(ExportVisitor visitor) { visitor.visit(this); }

    @Override
    public String toString() {
        return name + " (" + rank + ", " + village + ") " + stats + ", jutsus=" + jutsus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ninja)) return false;
        Ninja ninja = (Ninja) o;
        return Objects.equals(name, ninja.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name); }
}
