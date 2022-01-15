package designpatterns.command;

import data.ActionData;
import database.MainDB;

public final class InitDatabaseCommand implements Command {
  private ActionData input;
  private MainDB mainDB;
  private MainDB beforeDB;

  public InitDatabaseCommand(final ActionData input, final MainDB mainDB) {
    this.input = input;
    this.mainDB = mainDB;
  }

  @Override
  public void execute() {

    this.beforeDB = null;

    Double initialSantaBudget = input.getSantaBudget();

    mainDB.setChildrenList(input.getInitialData().getChildren());
    mainDB.getSanta().setSantaBudget(initialSantaBudget);
    mainDB.getSanta().setSantaGiftList(input.getInitialData().getSantaGiftsList());
  }

  @Override
  public void undo() {
    mainDB = beforeDB;
  }

  public ActionData getInput() {
    return input;
  }

  public void setInput(final ActionData input) {
    this.input = input;
  }

  public MainDB getMainDB() {
    return mainDB;
  }

  public void setMainDB(final MainDB mainDB) {
    this.mainDB = mainDB;
  }

  public MainDB getBeforeDB() {
    return beforeDB;
  }

  public void setBeforeDB(final MainDB beforeDB) {
    this.beforeDB = beforeDB;
  }
}
