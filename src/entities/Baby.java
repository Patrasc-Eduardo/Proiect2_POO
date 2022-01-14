package entities;

import designpatterns.strategy.average.AverageScoreStrategy;

public final class Baby extends Child implements AverageScoreStrategy {

  public static final double MAX_SCORE = 10.0;

  public Baby(final Child ch) {

    this.setFirstName(ch.getFirstName());
    this.setLastName(ch.getLastName());
    this.setAge(ch.getAge());
    this.setCity(ch.getCity());
    this.setAssignedBudget(ch.getAssignedBudget());
    this.setNiceScore(ch.getNiceScore());
    this.setId(ch.getId());
    this.setGiftsPreferences(ch.getGiftsPreferences());
    this.setNiceScoreHistory(ch.getNiceScoreHistory());
    this.setReceivedGifts(ch.getReceivedGifts());
    this.setNiceScoreBonus(ch.getNiceScoreBonus());
    this.setElf(ch.getElf());
  }

  @Override
  public void calculateAvgScore() {
    this.setAverageScore(MAX_SCORE);
  }
}
