package data;

import java.util.ArrayList;

public final class ChildUpdate {
  private final int id;
  private final Double niceScore;
  private final ArrayList<String> giftsPreferences;
  private final String elf;

  public ChildUpdate(
      final int id,
      final Double niceScore,
      final ArrayList<String> giftsPreferences,
      final String elf) {
    this.id = id;
    this.niceScore = niceScore;
    this.giftsPreferences = giftsPreferences;
    this.elf = elf;
  }

  public String getElf() {
    return elf;
  }

  public int getId() {
    return id;
  }

  public Double getNiceScore() {
    return niceScore;
  }

  public ArrayList<String> getGiftsPreferences() {
    return giftsPreferences;
  }

  @Override
  public String toString() {
    return "ChildUpdate{"
        + "id="
        + id
        + ", niceScore="
        + niceScore
        + ", giftsPreferences="
        + giftsPreferences
        + ", elf='"
        + elf
        + '\''
        + '}';
  }
}
