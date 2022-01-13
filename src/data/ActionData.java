package data;

import java.util.ArrayList;

public final class ActionData {
  private final int numberOfYears;
  private final Double santaBudget;
  private final InitialData initialData;
  private final ArrayList<AnnualChange> annualChanges;

  public ActionData(
      final int numberOfYears,
      final Double santaBudget,
      final InitialData initialData,
      final ArrayList<AnnualChange> annualChanges) {
    this.numberOfYears = numberOfYears;
    this.santaBudget = santaBudget;
    this.initialData = initialData;
    this.annualChanges = annualChanges;
  }

  public int getNumberOfYears() {
    return numberOfYears;
  }

  public Double getSantaBudget() {
    return santaBudget;
  }

  public InitialData getInitialData() {
    return initialData;
  }

  public ArrayList<AnnualChange> getAnnualChanges() {
    return annualChanges;
  }

  @Override
  public String toString() {
    return "ActionData{"
        + "\n"
        + "numberOfYears="
        + numberOfYears
        + "\n"
        + ", santaBudget="
        + santaBudget
        + "\n"
        + ", initialData="
        + initialData
        + "\n"
        + ", annualChanges="
        + annualChanges
        + "\n"
        + '}';
  }
}
