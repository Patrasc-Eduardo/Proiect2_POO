package utilsprocess;

import designpatterns.factory.ChildFactory;
import data.AnnualChange;
import data.ChildUpdate;
import database.MainDB;
import entities.Child;
import entities.Gift;
import entities.Santa;
import fileio.AnnualOutput;
import fileio.Output;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public final class UtilsProcess {
  /** Pentru checkstyle */
  public static final int ADULT_AGE = 18;

  private UtilsProcess() {}

  /**
   * Elimina copiii cu varsta de peste 18 ani.
   *
   * @param childList lista de copii
   */
  public static void eliminateAdults(final ArrayList<Child> childList) {
    childList.removeIf(ch -> ch.getAge() > ADULT_AGE);
  }

  /**
   * Elimina cadourile primite de la runda anterioara.
   *
   * @param childList lista de copii
   */
  public static void removeReceivedGifts(final ArrayList<Child> childList) {
    for (Child ch : childList) {
      ch.getReceivedGifts().clear();
    }
  }

  /**
   * Adauga noi copii (care nu au peste 18 ani) in lista de copii a mosului.
   *
   * @param childList lista de copii
   * @param newChildList noua lista de copii
   */
  public static void addNewChildren(
      final ArrayList<Child> childList, final ArrayList<Child> newChildList) {
    for (Child ch : newChildList) {
      if (ch.getAge() <= ADULT_AGE) {
        childList.add(ch);
      }
    }
  }

  /**
   * Cauta si returneaza un copil dupa ID
   *
   * @param childList lista de copii
   * @param id ID-ul copilului pe care vrem sa-l gasim si returnam
   * @return copilul gasit
   */
  public static Child findById(final ArrayList<Child> childList, final int id) {
    for (Child ch : childList) {
      if (ch.getId() == id) {
        return ch;
      }
    }
    return null;
  }

  /**
   * Actualizam informatiile despre copiii de la runda anterioara prin clasa AnnualChange.
   *
   * @param childList lista de copii
   * @param anChange schimbarea anuala
   */
  public static void updateOldChilds(
      final ArrayList<Child> childList, final AnnualChange anChange) {

    for (Child ch : childList) {
      ch.setAge(ch.getAge() + 1);
    }

    childList.removeIf(ch -> ch.getAge() > ADULT_AGE);

    for (ChildUpdate chUpd : anChange.getChildrenUpdates()) {

      Child child = findById(childList, chUpd.getId());

      if (child != null) {

        if (chUpd.getNiceScore() != null) {
          child.getNiceScoreHistory().add(chUpd.getNiceScore());
        }

        if (!chUpd.getGiftsPreferences().isEmpty()) {

          Collections.reverse(chUpd.getGiftsPreferences());
          for (String pref : chUpd.getGiftsPreferences()) {

            if (child.getGiftsPreferences().toString().contains(pref)) {

              child.getGiftsPreferences().remove(pref);
              child.getGiftsPreferences().add(0, pref);

            } else {
              child.getGiftsPreferences().add(0, pref);
            }
          }
        }
        if (chUpd.getElf() != null) {
          child.setElf(chUpd.getElf());
        }
      }
    }
  }

  /**
   * Metoda care verifica daca deja exista un cadou in lista de cadouri data ca parametru.
   *
   * @param newGift noul cadou
   * @param giftList lista de cadouri
   * @return 1 daca exista cadoul duplicat, 0 altfel
   */
  public static int checkForDuplicateGifts(final String newGift, final ArrayList<Gift> giftList) {
    for (Gift gift : giftList) {
      if (gift.getProductName().compareTo(newGift) == 0) {
        return 1;
      }
    }
    return 0;
  }

  /**
   * Actualizeaza informatiile despre Santa in functie de informatiile stocate in clasa
   * AnnualChange.
   *
   * @param santa informatiile despre Santa.
   * @param anChange schimbarea anuala.
   */
  public static void updateOldSanta(final Santa santa, final AnnualChange anChange) {
    santa.setSantaBudget(anChange.getNewSantaBudget());

    for (Gift gift : anChange.getNewGifts()) {
      if (checkForDuplicateGifts(gift.getProductName(), santa.getSantaGiftList()) == 0) {
        santa.getSantaGiftList().add(gift);
      }
    }
  }

  /**
   * Metoda plaseaza in childrenList noile instante de Child create in functie de varsta.
   *
   * @param childrenList lista de copii
   * @param databaseChild lista de copii din baza de date
   */
  public static void createChildrenByAge(
      final ArrayList<Child> childrenList, final ArrayList<Child> databaseChild) {
    ChildFactory chFactory = new ChildFactory();

    for (Child ch : databaseChild) {

      ch = chFactory.createChild(ch.getAge(), ch);

      childrenList.add(ch);
    }
  }

  /**
   * Metoda care calculeaza si plaseaza in avgScoreList scorurile average ale tuturor copiilor.
   *
   * @param avgScoreList lista cu scoruri average
   * @param childList lista cu copii
   */
  public static void calculateAllAvgScores(
      final ArrayList<Double> avgScoreList, final ArrayList<Child> childList) {
    for (Child ch : childList) {
      ch.calculateAvgScore();
      avgScoreList.add(ch.getAverageScore());
    }
  }

  /**
   * Calculeaza pentru fiecare copil din childList bugetul asignat, in functie de budget unit
   *
   * @param childList lista cu copii
   * @param budgetUnit budget unit
   */
  public static void calculateAllChildAssignedBudget(
      final ArrayList<Child> childList, final Double budgetUnit) {
    for (Child ch : childList) {
      ch.calculateAssignedBudget(budgetUnit);
    }
  }

  /**
   * Trimite la output lista de copiii DUPA ce au fost efectuate prelucrarile cerute. (Dupa ce au
   * primit cadourile)
   *
   * @param mainDB baza de date principala
   * @param output outputul in care vom stoca rezultatele
   */
  public static void sendToOutput(final MainDB mainDB, final Output output) {
    AnnualOutput anOutput = new AnnualOutput();
    ArrayList<Child> firstRoundChildren = new ArrayList<>();

    for (Child ch : mainDB.getChildrenList()) {
      firstRoundChildren.add(new Child(ch));
    }
    //System.out.println("FINAL &&&&&&& " + firstRoundChildren);
    anOutput.getChildren().addAll(firstRoundChildren);
    //System.out.println("FINAL &&&&&&&  AN OUTPUT" + anOutput);
    output.getAnnualChildren().add(anOutput);
  }

  /**
   * Metoda care implementeaza algoritmul de trimitere a cadourilor.
   *
   * @param mainDB baza de date principala
   * @param santaBudget bugetul al lui Santa
   */
  public static void sendGifts(
      final MainDB mainDB, final ArrayList<Child> childrenList, Double santaBudget) {
    HashMap<String, ArrayList<Gift>> santaGiftMap = mainDB.getSanta().giftListToMap();
    ArrayList<Gift> arr;
    //System.out.println("Send gifts from UTILS");
    for (Child ch : childrenList) {
      //System.out.println("here 1");
      Double childAssignedBudget = ch.getAssignedBudget();
      int hasReceived = 0;
      System.out.println();
      System.out.println(ch.getLastName() + " " + ch.getFirstName());
      System.out.println();
      if (Double.compare(childAssignedBudget, 0.0) > 0) {
        for (String prefs : ch.getGiftsPreferences()) {

          if (santaGiftMap.containsKey(prefs)) {
            System.out.println("Category " + prefs);
            arr = santaGiftMap.get(prefs); // preia lista de cadouri dintr-o anumita categorie

            if (!arr.isEmpty()) {

              arr.sort((Comparator.comparing(Gift::getPrice)));
              System.out.println("Gift arr " + arr + " and budget " + childAssignedBudget);

              Gift minGift = null;

              for (Gift gift : arr) {
                if (gift.getQuantity() > 0) {
                  minGift = gift;
                  break;
                }
              }
              //minGift = arr.get(0);

              if (minGift != null) {
                int compAssignedBudget = Double.compare(minGift.getPrice(), childAssignedBudget);
                int compsantaBudget = Double.compare(minGift.getPrice(), santaBudget);

                if ((compAssignedBudget < 0 || compAssignedBudget == 0) &&
                        (compsantaBudget < 0 || compsantaBudget == 0)) {

                  int giftQuantity = minGift.getQuantity();
                  System.out.println("Received " + minGift.getProductName() + " and quantity -> " + minGift.getQuantity());
                  ch.getReceivedGifts().add(minGift); // primeste cadoul

                  childAssignedBudget -= minGift.getPrice();
                  santaBudget -= minGift.getPrice();

                  giftQuantity--;
                  minGift.setQuantity(giftQuantity);

                  hasReceived++;
                }
              }
            }
          }
        }
      }

      if (ch.getElf().compareTo("yellow") == 0 && hasReceived == 0) {
        String category = ch.getGiftsPreferences().get(0);

        if (category != null) {
          ArrayList<Gift> giftArray = santaGiftMap.get(category);

          giftArray.sort((Comparator.comparing(Gift::getPrice)));

          Gift minGift = giftArray.get(0);

          if (minGift.getQuantity() != 0) {
            ch.getReceivedGifts().add(minGift);
          }
        }
      }
    }
    mainDB.getChildrenList().sort((Comparator.comparingInt(Child::getId))); // sortam dupa ID lista de copii
    //System.out.println("FINAL %%%% CHILDREN %%%%%% " + childrenList);
    //mainDB.setChildrenList(new ArrayList<>(childrenList));
  }
}
