package naruto.visitor;

import naruto.model.Jutsu;
import naruto.model.Mission;
import naruto.model.Ninja;

public class JsonExportVisitor implements ExportVisitor {
    private final StringBuilder sb = new StringBuilder();
    private boolean first = true;

    @Override
    public void start() { sb.append("{\n  \"items\": [\n"); }

    @Override
    public void visit(Ninja ninja) {
        if (!first) sb.append(",\n");
        first = false;
        sb.append("    {\n");
        sb.append("      \"type\": \"ninja\",\n");
        sb.append("      \"name\": \"").append(escape(ninja.getName())).append("\",\n");
        sb.append("      \"rank\": \"").append(ninja.getRank()).append("\",\n");
        sb.append("      \"village\": \"").append(ninja.getVillage()).append("\",\n");
        sb.append("      \"stats\": { \"attack\": ").append(ninja.getStats().getAttack())
          .append(", \"defense\": ").append(ninja.getStats().getDefense())
          .append(", \"chakra\": ").append(ninja.getStats().getChakra()).append(" },\n");
        sb.append("      \"jutsus\": [");
        for (int i=0;i<ninja.getJutsus().size();i++) {
            Jutsu j = ninja.getJutsus().get(i);
            sb.append("{ \"name\": \"").append(escape(j.getName())).append("\", \"power\": ")
              .append(j.getPower()).append(", \"cost\": ").append(j.getChakraCost()).append(" }");
            if (i < ninja.getJutsus().size()-1) sb.append(", ");
        }
        sb.append("]\n");
        sb.append("    }");
    }

    @Override
    public void visit(Mission mission) {
        if (!first) sb.append(",\n");
        first = false;
        sb.append("    {\n");
        sb.append("      \"type\": \"mission\",\n");
        sb.append("      \"title\": \"").append(escape(mission.getTitle())).append("\",\n");
        sb.append("      \"rank\": \"").append(mission.getRank()).append("\",\n");
        sb.append("      \"reward\": ").append(mission.getRewardRyō()).append(",\n");
        sb.append("      \"minRank\": \"").append(mission.getMinRequiredRank()).append("\"\n");
        sb.append("    }");
    }

    @Override
    public void end() { sb.append("\n  ]\n}\n"); }

    @Override
    public String getResult() { return sb.toString(); }

    private static String escape(String s) { return s.replace("\\", "\\\\").replace("\"", "\\\""); }
}
