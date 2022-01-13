package entities;

public final class Teen extends Child {

  public Teen(final Child ch) {
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
  /**
   * Calculeaza average score ca o medie ponderata.
   */
  @Override
  public void calculateAvgScore() {
    double nr = 0.0;
    double sum = 0.0;
    Double index = 1.0;

    for (Double score : this.getNiceScoreHistory()) {
      sum += (score * index);
      nr += index;
      index++;
    }

    if (Double.compare(nr, 0.0) == 0 || Double.compare(sum, 0.0) == 0) {
      this.setAverageScore(getNiceScore());
    }

    double avg = sum / nr;

    if (Double.compare(this.getNiceScoreBonus(), 0.0) != 0) {
      avg += avg * (this.getNiceScoreBonus() / 100);
    }

    if (Double.compare(avg, 10.0) > 0) {
      avg = 10.0;
    }

    this.setAverageScore(avg);
  }
}
