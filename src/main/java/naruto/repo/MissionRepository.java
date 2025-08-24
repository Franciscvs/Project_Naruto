package naruto.repo;

import naruto.model.Mission;

import java.util.*;

public class MissionRepository {
    private final Map<String, Mission> byTitle = new LinkedHashMap<>();

    public void add(Mission m) { if (m != null) byTitle.put(m.getTitle(), m); }
    public Optional<Mission> findByTitle(String title) { return Optional.ofNullable(byTitle.get(title)); }
    public List<Mission> getAll() { return new ArrayList<>(byTitle.values()); }
}
