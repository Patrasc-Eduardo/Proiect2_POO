package fileio;

import entities.Child;
import java.util.ArrayList;

public final class AnnualOutput {
  private final ArrayList<Child> children = new ArrayList<>();

  public ArrayList<Child> getChildren() {
    return children;
  }

  @Override
  public String toString() {
    return "AnnualOutput{" + "annualChildren=" + children + '}';
  }
}
