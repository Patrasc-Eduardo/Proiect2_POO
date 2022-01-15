package designpatterns.command;

public interface Command {
    /**
     * Executa o comanda
     */
    void execute();
    /**
     * Undo la o comanda
     */
    void undo();
}
