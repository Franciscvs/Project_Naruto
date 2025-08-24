package naruto.repo;

import naruto.model.Ninja;

import java.util.*;

public class NinjaRepository {
    private final Map<String, Ninja> byName = new LinkedHashMap<>();

    public void add(Ninja n) { if (n != null) byName.put(n.getName(), n); }
    public Optional<Ninja> findByName(String name) { return Optional.ofNullable(byName.get(name)); }
    public List<Ninja> getAll() { return new ArrayList<>(byName.values()); }
}
