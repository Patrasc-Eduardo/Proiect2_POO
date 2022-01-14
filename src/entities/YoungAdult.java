package entities;

public final class YoungAdult extends Child {

  public YoungAdult(final Child ch) {
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

//  @Override
//  public void calculateAvgScore() {
//    double avg = this.getAverageScore();
//    if (Double.compare(this.getNiceScoreBonus(), 0.0) != 0) {
//      avg += avg * this.getNiceScoreBonus() / 100;
//    }
//
//    if (Double.compare(avg, 10.0) > 0) {
//      avg = 10.0;
//    }
//
//    this.setAverageScore(avg);
//  }
}
