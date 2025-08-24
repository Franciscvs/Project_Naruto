package naruto.model;

import java.util.Objects;

public class Jutsu {
    private final String name;
    private final Element element;
    private final int power;      // Impacto ofensivo
    private final int chakraCost; // Coste en chakra por uso

    public Jutsu(String name, Element element, int power, int chakraCost) {
        this.name = name;
        this.element = element;
        this.power = Math.max(0, power);
        this.chakraCost = Math.max(0, chakraCost);
    }

    public String getName() { return name; }
    public Element getElement() { return element; }
    public int getPower() { return power; }
    public int getChakraCost() { return chakraCost; }

    @Override
    public String toString() {
        return name + "[" + element + ", pow=" + power + ", cost=" + chakraCost + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jutsu)) return false;
        Jutsu jutsu = (Jutsu) o;
        return power == jutsu.power && chakraCost == jutsu.chakraCost &&
                Objects.equals(name, jutsu.name) && element == jutsu.element;
    }

    @Override
    public int hashCode() { return Objects.hash(name, element, power, chakraCost); }
}
