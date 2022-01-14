package designpatterns.strategy.gift;

import database.MainDB;
import entities.Child;
import utilsprocess.UtilsProcess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class NiceScoreStrategy implements GiftStrategy {

  public NiceScoreStrategy() {}

  @Override
  public void sendGifts(MainDB mainDB, Double santaBudget) {
    HashMap<Child, Double> map = mainDB.childAvgMap();

    ArrayList<Child> childList = new ArrayList<>();

    for (Map.Entry<Child, Double> entry : map.entrySet()) {
      childList.add(entry.getKey());
    }

    childList.sort(
        (o1, o2) -> {
          if (Double.compare(o1.getAverageScore(), o2.getAverageScore()) == 0) {
            return Integer.compare(o1.getId(), o2.getId());
          }
          if (Double.compare(o1.getAverageScore(), o2.getAverageScore()) < 0) {
            return 1;
          } else {
            return -1;
          }
        });

    UtilsProcess.sendGifts(mainDB, childList, santaBudget);
  }
}
