package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.ActionData;
import data.AnnualChange;
import database.MainDB;
import designpatterns.strategy.gift.GiftFactory;
import designpatterns.strategy.gift.GiftStrategy;
import entities.Child;
import fileio.Output;
import utilsprocess.UtilsProcess;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public final class ProcessInput {
  /**
   * Prelucreaza datele pentru runda 0.
   *
   * @param mainDB baza de date cu care lucram
   * @param input inputul de unde luam informatiile si le stocam in baza de date
   * @param output outputul in care stocam rezultatele
   */
  public void processRoundZero(final MainDB mainDB, final ActionData input, final Output output) {

    Double initialSantaBudget = input.getSantaBudget();

    mainDB.setChildrenList(input.getInitialData().getChildren());
    mainDB.getSanta().setSantaBudget(initialSantaBudget);
    mainDB.getSanta().setSantaGiftList(input.getInitialData().getSantaGiftsList());

    ArrayList<Child> childrenList = new ArrayList<>();
    UtilsProcess.createChildrenByAge(childrenList, mainDB.getChildrenList());

    UtilsProcess.eliminateAdults(childrenList);

    childrenList.sort((Comparator.comparingInt(Child::getId))); // sortam dupa ID lista de copii

    mainDB.setChildrenList(childrenList);

    mainDB
        .getChildrenList()
        .sort((Comparator.comparingInt(Child::getId))); // sortam dupa ID lista de copii

    ArrayList<Double> allChildAvg = new ArrayList<>();
    UtilsProcess.calculateAllAvgScores(allChildAvg, mainDB.getChildrenList());
    System.out.println("&&&&&&&&&&&&&&&&&&&&&&& ALL CHILD AVG " + allChildAvg);
//    for(Child ch : mainDB.getChildrenList()) {
//      if (ch.getElf().compareTo("black") == 0) {
//        Double assignedBudget = ch.getAssignedBudget() - (ch.getAssignedBudget() * (30/100));
//        //assignedBudget = assignedBudget - assignedBudget * (30.0 / 100.0);
//        ch.setAssignedBudget(assignedBudget);
//      }
//
//      if (ch.getElf().compareTo("pink") == 0) {
//        Double assignedBudget = ch.getAssignedBudget() + (ch.getAssignedBudget() * (30/100));
//        //assignedBudget = assignedBudget - assignedBudget * (30.0 / 100.0);
//        ch.setAssignedBudget(assignedBudget);
//      }
//    }
    Double budgetUnit = mainDB.getSanta().calculateBudgetUnit(allChildAvg);
    System.out.println("************* childs and niceScore bonuses " + mainDB.getChildrenList());
    UtilsProcess.calculateAllChildAssignedBudget(mainDB.getChildrenList(), budgetUnit);

    GiftStrategy sendGiftStrategy = GiftFactory.createStrategy("id");
    sendGiftStrategy.sendGifts(mainDB, initialSantaBudget);

    // UtilsProcess.sendGifts(mainDB, initialSantaBudget);

    UtilsProcess.sendToOutput(mainDB, output);

    // System.out.println("Output ");
    // System.out.println(output);
  }

  /**
   * Prelucreaza datele pentru restul rundelor
   *
   * @param mainDB baza de date cu care lucram
   * @param input inputul de unde luam informatiile si le stocam in baza de date
   * @param output outputul in care stocam rezultatele
   */
  public void processAllRounds(final MainDB mainDB, final ActionData input, final Output output)
      throws IOException {
    for (int i = 0; i < input.getNumberOfYears(); i++) {
      System.out.println("################################ ROUND " + i);
      AnnualChange anChange = input.getAnnualChanges().get(i);

      UtilsProcess.removeReceivedGifts(mainDB.getChildrenList());

      UtilsProcess.updateOldChilds(mainDB.getChildrenList(), anChange);

      UtilsProcess.addNewChildren(mainDB.getChildrenList(), anChange.getNewChildren());

      UtilsProcess.updateOldSanta(mainDB.getSanta(), anChange);

      Double initialSantaBudget = mainDB.getSanta().getSantaBudget();

      // mainDB.getCitiesList().clear();

      ArrayList<Child> childrenList = new ArrayList<>();
      UtilsProcess.createChildrenByAge(childrenList, mainDB.getChildrenList());

      UtilsProcess.eliminateAdults(childrenList);

      mainDB.setChildrenList(childrenList);

      mainDB
          .getChildrenList()
          .sort((Comparator.comparingInt(Child::getId))); // sortam dupa ID lista de copii

      ArrayList<Double> allChildAvg = new ArrayList<>();
      UtilsProcess.calculateAllAvgScores(allChildAvg, mainDB.getChildrenList());
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&& ALL CHILD AVG " + allChildAvg);
      System.out.println("((((((((((((((((((((((( santa budget " + mainDB.getSanta().getSantaBudget());
      Double budgetUnit = mainDB.getSanta().calculateBudgetUnit(allChildAvg);
      System.out.println("@@@@@@@@@@@@@@@@ budget unit " + budgetUnit);

      System.out.println("************* childs and niceScore bonuses " + mainDB.getChildrenList());
      UtilsProcess.calculateAllChildAssignedBudget(mainDB.getChildrenList(), budgetUnit);

      mainDB.makeCitiesList();
      // GiftStrategy sendGiftStrategy = GiftFactory.createStrategy("id");
      // System.out.println("CHILDREN LIST " + mainDB.getChildrenList());

      GiftStrategy sendGiftStrategy = GiftFactory.createStrategy(anChange.getStrategy());
      sendGiftStrategy.sendGifts(mainDB, initialSantaBudget);

      // UtilsProcess.sendGifts(mainDB, initialSantaBudget);

      UtilsProcess.sendToOutput(mainDB, output);

      // System.out.println("OUTPUT " + output);

    }
  }

  /**
   * Entry point-ul programului.
   *
   * @param input inputul de unde luam informatiile
   * @param filePath2 fisierul de output
   * @throws IOException exceptie generata de scrierea in JSON
   */
  public void init(final ActionData input, final String filePath2) throws IOException {

    MainDB mainDB = MainDB.getInstance();
    Output output = new Output();
    ObjectMapper objectMapper = new ObjectMapper();

    processRoundZero(mainDB, input, output);

    processAllRounds(mainDB, input, output);
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath2), output);
    // System.out.println("FINAL OUTPUT =++ " + output);
  }
}
