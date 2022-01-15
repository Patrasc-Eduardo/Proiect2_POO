package designpatterns.strategy.gift;

import common.Constants;

public class GiftFactory {

  public GiftFactory() {
    // for coding style
  }

  /**
   * Creaza o strategie de asignare a cadourilor
   */
  public final GiftStrategy createStrategy(final String strategyType) {
    switch (strategyType) {
      case Constants.ID:
        return new IdStrategy();
      case Constants.NICE_SCORE:
        return new NiceScoreStrategy();
      case Constants.NICE_SCORE_CITY:
        return new NiceScoreCityStrategy();

      default:
        System.out.println("Strategy type not recognized");
    }
    throw new IllegalArgumentException("The strategy type " + strategyType + " is not recognized.");
  }
}
