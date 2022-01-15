package entities;

import java.util.ArrayList;

public final class City {
  private final String name;
  private Double averageScore;

  public City(final String name, final Double averageScore) {
    this.name = name;
    this.averageScore = averageScore;
  }

  /**
   * Calculeaza scorul average pentru oras.
   * @param childList lista de copii in care vom cauta orasul respectiv
   */
  public void calculateAvgScore(final ArrayList<Child> childList) {
    double sum = 0.0;
    double nr = 0.0;

    for (Child ch : childList) {
      if (ch.getCity().compareTo(name) == 0) {
        Double avg = ch.getAverageScore();
        if (avg != null) {
          sum += ch.getAverageScore();
          nr++;
        }
      }
    }
    if (Double.compare(sum, 0.0) != 0 && Double.compare(nr, 0.0) != 0) {
      this.averageScore = (sum / nr);
    } else {
      this.averageScore = 0.0;
    }
  }

  public String getName() {
    return name;
  }

  public Double getAverageScore() {
    return averageScore;
  }

  public void setAverageScore(final Double averageScore) {
    this.averageScore = averageScore;
  }
}
