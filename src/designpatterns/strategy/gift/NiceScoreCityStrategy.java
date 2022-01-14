package designpatterns.strategy.gift;

import database.MainDB;
import entities.Child;
import entities.City;
import utilsprocess.UtilsProcess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NiceScoreCityStrategy implements GiftStrategy {

  public NiceScoreCityStrategy() {}

  @Override
  public void sendGifts(MainDB mainDB, Double santaBudget) {
    System.out.println("NICE SCORE CITY ");

    HashMap<City, Double> map = mainDB.citiesToMap();

    System.out.println("cities to map  " + map);

//    ArrayList<City> citiesList = new ArrayList<>();
//
//    for (Map.Entry<City, Double> entry : map.entrySet()) {
//      citiesList.add(entry.getKey());
//    }

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

    for(City city : citiesList){
        System.out.println("CITY " + city.getName() + " avg score " + city.getAverageScore());
    }

    for (City city : citiesList) {
      for (Child ch : mainDB.getChildrenList()) {
        if (ch.getCity().compareTo(city.getName()) == 0) {
          childrenToVisit.add(ch);
        }
      }
    }

    //System.out.println("CHILDREN TO VISIT " + childrenToVisit);
      //System.out.println("cities to visit " + citiesList);
    //System.out.println("children to visit " + childrenToVisit);
    UtilsProcess.sendGifts(mainDB, childrenToVisit, santaBudget);
  }
}
