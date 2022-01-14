package database;

import entities.Child;
import entities.City;
import entities.Santa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class MainDB {
  private static MainDB instance = null;
  private final Santa santa = new Santa();
  private ArrayList<Child> childrenList = new ArrayList<>();
  private ArrayList<City> citiesList = new ArrayList<>();
  //private HashMap<City, Double> citiesAndAvg = new HashMap<>();


  private MainDB() {

  }

  /** @return instanta bazei de date de tip Singleton. */
  public static MainDB getInstance() {
    if (instance == null) {
      instance = new MainDB();
    }
    return instance;
  }

  public void makeCitiesList() {
    //Set<City> cities = new HashSet<City>();

//    for(Child ch : childrenList) {
//      cities.add(new City(ch.getCity(), 0.0));
//    }

    for(Child ch : childrenList) {
      if (getCityByName(ch.getCity()) == null) {
        citiesList.add(new City(ch.getCity(), 0.0));
      }
    }
    //System.out.println("City set " + cities);

    for(City city : citiesList) {

      if (city != null) {
        city.calculateAvgScore(childrenList);
        //citiesList.add(city);
      }

    }

  }

  public HashMap<City, Double> citiesToMap(){
    HashMap<City, Double> map = new HashMap<>();

    for(City city : citiesList){
      map.put(city, city.getAverageScore());
    }

    return map;
  }

  public ArrayList<City> getCitiesList() {
    return citiesList;
  }

  public void setCitiesList(ArrayList<City> citiesList) {
    this.citiesList = citiesList;
  }

  public HashMap<Child, Double> childAvgMap() {
    HashMap<Child, Double> map = new HashMap<>();

    for(Child ch : childrenList) {
      map.put(ch, ch.getAverageScore());
    }

    return map;
  }

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
