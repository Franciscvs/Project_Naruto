package naruto.visitor;

public interface Visitable {
    void accept(ExportVisitor visitor);
}
