package designpatterns.command;

public final class Client {
  private final Invoker invoker;

  public Client() {
    invoker = new Invoker();
  }

  /**
   * @param command comanda de executat
   */
  public void executeAction(final Command command) {
    invoker.execute(command);
  }

  /**
   * Face undo la comanda
   */
  public void undo() {
        invoker.undo();
    }
}
