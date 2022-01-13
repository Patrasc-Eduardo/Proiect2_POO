package data;

import entities.Child;
import entities.Gift;
import enums.Cities;
import java.util.ArrayList;

public final class InitialData {
  private ArrayList<Child> children = new ArrayList<>();
  private ArrayList<Gift> santaGiftsList = new ArrayList<>();
  private ArrayList<Cities> initCitiesList = new ArrayList<>();

  public InitialData() { }

  public InitialData(
      final ArrayList<Child> children,
      final ArrayList<Gift> santaGiftsList,
      final ArrayList<Cities> initCitiesList) {
    this.children = children;
    this.santaGiftsList = santaGiftsList;
    this.initCitiesList = initCitiesList;
  }

  public ArrayList<Child> getChildren() {
    return children;
  }

  public ArrayList<Gift> getSantaGiftsList() {
    return santaGiftsList;
  }

  @Override
  public String toString() {
    return "InitialData{"
        + "\n"
        + "children="
        + children
        + "\n"
        + ", santaGiftsList="
        + santaGiftsList
        + "\n"
        + ", initCitiesList="
        + initCitiesList
        + "\n"
        + '}';
  }
}
