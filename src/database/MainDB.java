package database;

import entities.Child;
import entities.City;
import entities.Santa;
import java.util.ArrayList;
import java.util.HashMap;

public final class MainDB {
  private static MainDB instance = null;
  private final Santa santa = new Santa();
  private final ArrayList<City> citiesList = new ArrayList<>();
  private ArrayList<Child> childrenList = new ArrayList<>();

  private MainDB() { }

  /** @return instanta bazei de date de tip Singleton. */
  public static MainDB getInstance() {
    if (instance == null) {
      instance = new MainDB();
    }
    return instance;
  }

  /**
   * Formeaza lista de orase prezenta in lista copiilor.
   */
  public void makeCitiesList() {

    for (Child ch : childrenList) {
      if (getCityByName(ch.getCity()) == null) {
        citiesList.add(new City(ch.getCity(), 0.0));
      }
    }

    for (City city : citiesList) {
      if (city != null) {
        city.calculateAvgScore(childrenList);
      }
    }
  }

  /**
   * Creaza un dictionar ce are drept cheie orasul si drept valoare scorul  average
   * @return dictionarul respectiv
   */
  public HashMap<City, Double> citiesToMap() {
    HashMap<City, Double> map = new HashMap<>();

    for (City city : citiesList) {
      map.put(city, city.getAverageScore());
    }

    return map;
  }

  public ArrayList<City> getCitiesList() {
    return citiesList;
  }

  /**
   *  Creaza un dictionar ce are drept cheie copilul si drept valoare scorul lui average
   * @return dictionarul respectiv
   */
  public HashMap<Child, Double> childAvgMap() {
    HashMap<Child, Double> map = new HashMap<>();

    for (Child ch : childrenList) {
      map.put(ch, ch.getAverageScore());
    }

    return map;
  }

  /**
   * @param name Numele orasului pe care vrem sa-l returnam.
   * @return instanta de oras
   */
  public City getCityByName(final String name) {
    for (City city : citiesList) {
      if (city.getName().compareTo(name) == 0) {
        return city;
      }
    }

    return null;
  }

  public Santa getSanta() {
    return santa;
  }

  public ArrayList<Child> getChildrenList() {
    return childrenList;
  }

  public void setChildrenList(final ArrayList<Child> childrenList) {
    this.childrenList = childrenList;
  }

  @Override
  public String toString() {
    return "MainDB{" + "santa=" + santa + ", childrenList=" + childrenList + '}';
  }
}
