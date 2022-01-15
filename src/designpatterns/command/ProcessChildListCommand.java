package designpatterns.command;

import database.MainDB;
import entities.Child;
import utilsprocess.UtilsProcess;

import java.util.ArrayList;

public final class ProcessChildListCommand implements Command {
  private ArrayList<Child> beforeChildList;
  private ArrayList<Child> childrenList;
  private MainDB mainDB;

  public ProcessChildListCommand(final MainDB mainDB) {
    this.mainDB = mainDB;
  }

  /**
   * Proceseaza lista de copii : se creaza copii in functie de varsta, elimina adulti,
   * calculeaza scorul average, calculeaza scorul asignat pentru fiecare copil.
   */
  @Override
  public void execute() {

    this.beforeChildList = new ArrayList<>(mainDB.getChildrenList());

    childrenList = new ArrayList<>();

    UtilsProcess.createChildrenByAge(childrenList, mainDB.getChildrenList());

    UtilsProcess.eliminateAdults(childrenList);

    mainDB.setChildrenList(childrenList);

    ArrayList<Double> allChildAvg = new ArrayList<>();
    UtilsProcess.calculateAllAvgScores(allChildAvg, mainDB.getChildrenList());

    Double budgetUnit = mainDB.getSanta().calculateBudgetUnit(allChildAvg);

    UtilsProcess.calculateAllChildAssignedBudget(mainDB.getChildrenList(), budgetUnit);
  }

  @Override
  public void undo() {
    mainDB.setChildrenList(beforeChildList);
  }

  public ArrayList<Child> getBeforeChildList() {
    return beforeChildList;
  }

  public void setBeforeChildList(final ArrayList<Child> beforeChildList) {
    this.beforeChildList = beforeChildList;
  }

  public ArrayList<Child> getChildrenList() {
    return childrenList;
  }

  public void setChildrenList(final ArrayList<Child> childrenList) {
    this.childrenList = childrenList;
  }

  public MainDB getMainDB() {
    return mainDB;
  }

  public void setMainDB(final MainDB mainDB) {
    this.mainDB = mainDB;
  }
}
