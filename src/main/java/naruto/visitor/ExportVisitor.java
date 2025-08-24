package naruto.visitor;

import naruto.model.Mission;
import naruto.model.Ninja;

public interface ExportVisitor {
    void start();
    void visit(Ninja ninja);
    void visit(Mission mission);
    void end();
    String getResult();
}
