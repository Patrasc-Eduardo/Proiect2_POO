package designpatterns.command;

import java.util.LinkedList;

public class Invoker {
  private final LinkedList<Command> history = new LinkedList<>();

  /**
   * Executa o comanda data
   *
   * @param command
   */
  public void execute(final Command command) {
    command.execute();
    this.history.add(command);
  }

  /** Undo la o ultima comanda */
  public void undo() {

    if (this.history.isEmpty()) {
      return;
    }

    for (Command comm : this.history) {
      comm.undo();
    }
  }
}
