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
  }
}
