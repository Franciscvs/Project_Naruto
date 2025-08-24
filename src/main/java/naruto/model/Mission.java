package naruto.model;

import naruto.visitor.ExportVisitor;
import naruto.visitor.Visitable;

import java.util.Objects;

public class Mission implements Visitable {
    private final String title;
    private final MissionRank rank;
    private final int rewardRyō;          // Recompensa en Ryō
    private final Rank minRequiredRank;   // Rango mínimo para tomarla

    public Mission(String title, MissionRank rank, int rewardRyō, Rank minRequiredRank) {
        this.title = title;
        this.rank = rank;
        this.rewardRyō = Math.max(0, rewardRyō);
        this.minRequiredRank = minRequiredRank;
    }

    public String getTitle() { return title; }
    public MissionRank getRank() { return rank; }
    public int getRewardRyō() { return rewardRyō; }
    public Rank getMinRequiredRank() { return minRequiredRank; }

    @Override
    public void accept(ExportVisitor visitor) { visitor.visit(this); }

    @Override
    public String toString() {
        return "Mission{" +
                "title='" + title + '\'' +
                ", rank=" + rank +
                ", rewardRyō=" + rewardRyō +
                ", minRequiredRank=" + minRequiredRank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mission)) return false;
        Mission mission = (Mission) o;
        return Objects.equals(title, mission.title);
    }

    @Override
    public int hashCode() { return Objects.hash(title); }
}
