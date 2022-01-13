package designpatterns.strategy.gift;

import common.Constants;

public class GiftFactory {
  public static GiftStrategy createStrategy(String strategyType) {
    switch (strategyType) {
      case Constants.ID:
        return new IdStrategy();
      case Constants.NICE_SCORE:
        return new NiceScoreStrategy();
      case Constants.NICE_SCORE_CITY:
        return new NiceScoreCityStrategy();
    }
    throw new IllegalArgumentException("The strategy type " + strategyType + " is not recognized.");
  }
}
