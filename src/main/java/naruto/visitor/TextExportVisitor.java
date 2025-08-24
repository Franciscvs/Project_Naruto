package naruto.visitor;

import naruto.model.Jutsu;
import naruto.model.Mission;
import naruto.model.Ninja;

public class TextExportVisitor implements ExportVisitor {
    private final StringBuilder sb = new StringBuilder();

    @Override public void start() { sb.append("=== Informe Texto ===\n"); }

    @Override
    public void visit(Ninja ninja) {
        sb.append("Ninja: ").append(ninja.getName())
          .append(" | ").append(ninja.getRank())
          .append(" | ").append(ninja.getVillage())
          .append(" | ").append(ninja.getStats()).append('\n');
        sb.append("  Jutsus: ");
        for (Jutsu j : ninja.getJutsus()) {
            sb.append(j.getName()).append("(pow=").append(j.getPower()).append(") ");
        }
        sb.append('\n');
    }

    @Override
    public void visit(Mission mission) {
        sb.append("Misión: ").append(mission.getTitle())
          .append(" | Rango=").append(mission.getRank())
          .append(" | Recompensa=").append(mission.getRewardRyō()).append(" ryō")
          .append(" | Mín. ").append(mission.getMinRequiredRank())
          .append('\n');
    }

    @Override public void end() { sb.append("=== Fin Informe ===\n"); }
    @Override public String getResult() { return sb.toString(); }
}
