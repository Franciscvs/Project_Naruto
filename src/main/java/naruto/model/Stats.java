package naruto.model;

import java.util.Objects;

public class Stats {
    private int attack;
    private int defense;
    private int chakra;

    public Stats(int attack, int defense, int chakra) {
        this.attack = Math.max(0, attack);
        this.defense = Math.max(0, defense);
        this.chakra = Math.max(0, chakra);
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getChakra() { return chakra; }

    public void increase(int attackDelta, int defenseDelta, int chakraDelta) {
        this.attack = Math.max(0, this.attack + attackDelta);
        this.defense = Math.max(0, this.defense + defenseDelta);
        this.chakra = Math.max(0, this.chakra + chakraDelta);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "attack=" + attack +
                ", defense=" + defense +
                ", chakra=" + chakra +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stats)) return false;
        Stats stats = (Stats) o;
        return attack == stats.attack && defense == stats.defense && chakra == stats.chakra;
    }

    @Override
    public int hashCode() { return Objects.hash(attack, defense, chakra); }
}
