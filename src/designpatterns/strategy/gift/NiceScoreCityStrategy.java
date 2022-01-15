package designpatterns.strategy.gift;

import database.MainDB;
import entities.Child;
import entities.City;
import utilsprocess.UtilsProcess;
import java.util.ArrayList;
import java.util.HashMap;

public final class NiceScoreCityStrategy implements GiftStrategy {

  public NiceScoreCityStrategy() {

  }

  /**
   * Strategia de asignare a cadourilor in functie de nice score-ul oraselor
   * @param mainDB baza de date principala
   * @param santaBudget bugetul lui santa
   * */
  @Override
  public void sendGifts(final MainDB mainDB, final Double santaBudget) {

    HashMap<City, Double> map = mainDB.citiesToMap();

    ArrayList<City> citiesList = mainDB.getCitiesList();

    citiesList.sort(
        (o1, o2) -> {
          if (Double.compare(o1.getAverageScore(), o2.getAverageScore()) == 0) {
            return o1.getName().compareTo(o2.getName());
          }
          if (Double.compare(o1.getAverageScore(), o2.getAverageScore()) < 0) {
            return 1;
          } else {
            return -1;
          }
        });

    ArrayList<Child> childrenToVisit = new ArrayList<>();

    for (City city : citiesList) {
      for (Child ch : mainDB.getChildrenList()) {
        if (ch.getCity().compareTo(city.getName()) == 0) {
          childrenToVisit.add(ch);
        }
      }
    }
    UtilsProcess.sendGifts(mainDB, childrenToVisit, santaBudget);
  }
}
