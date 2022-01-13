package entities;

import java.util.ArrayList;
import java.util.HashMap;

public final class Santa {
  private Double santaBudget;
  private ArrayList<Gift> santaGiftList;
  private Double budgetUnit;

  public Santa() { }

  public Santa(final Double santaBudget, final ArrayList<Gift> santaGiftList) {
    this.santaBudget = santaBudget;
    this.santaGiftList = santaGiftList;
    this.budgetUnit = 0.0;
  }

  /**
   * Calculeaza Budget Unit in functie de lista cu scorurile average ale tuturor copiilor
   * @param allChildAvg lista cu scorurile average ale tuturor copiilor
   * @return Budget Unit
   */
  public Double calculateBudgetUnit(final ArrayList<Double> allChildAvg) {

    if (Double.compare(santaBudget, 0.0) != 0 && allChildAvg != null) {

      double sum = 0.0;

      for (Double db : allChildAvg) {
        if (db != null) {
          sum += db;
        }
      }

      budgetUnit = santaBudget / sum;
      return budgetUnit;
    }

    return 0.0;
  }

  /**
   * Metoda care transforma lista de cadouri ale lui Santa intr-un map ce are drept cheie ->
   * categoria cadoului si drept valoare -> lista de cadouri din acea categorie
   * @return HashMap
   */
  public HashMap<String, ArrayList<Gift>> giftListToMap() {
    HashMap<String, ArrayList<Gift>> giftMap = new HashMap<>();

    for (Gift gift : santaGiftList) {

      if (!giftMap.containsKey(gift.getCategory())) {
        ArrayList<Gift> gifts = new ArrayList<>();
        gifts.add(gift);
        giftMap.put(gift.getCategory(), gifts);
      } else {
        giftMap.get(gift.getCategory()).add(gift);
      }
    }
    return giftMap;
  }

  public Double getSantaBudget() {
    return santaBudget;
  }

  public void setSantaBudget(final Double santaBudget) {
    this.santaBudget = santaBudget;
  }

  public ArrayList<Gift> getSantaGiftList() {
    return santaGiftList;
  }

  public void setSantaGiftList(final ArrayList<Gift> santaGiftList) {
    this.santaGiftList = santaGiftList;
  }

  @Override
  public String toString() {
    return "Santa{" + "santaBudget=" + santaBudget + ", santaGiftList=" + santaGiftList + '}';
  }
}
