package fileio;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import data.ActionData;
import data.AnnualChange;
import data.ChildUpdate;
import data.InitialData;
import entities.Child;
import entities.Gift;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class InputLoader {
  /** The path to the input file */
  private final String inputPath;

  public InputLoader(final String inputPath) {
    this.inputPath = inputPath;
  }

  public String getInputPath() {
    return inputPath;
  }

  /**
   * The method reads the database
   *
   * @return an Input object
   */
  @SuppressWarnings("unchecked")
  public ActionData readData() throws JsonProcessingException {
    JSONParser jsonParser = new JSONParser();

    int numberOfYears = 0;
    double santaBudget = 0.0;
    InitialData initData = new InitialData();
    ArrayList<AnnualChange> annualChanges = new ArrayList<>();

    try {
      JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));

      numberOfYears = Math.toIntExact((Long) jsonObject.get(Constants.NUMBER_OF_YEARS));

      santaBudget = Double.valueOf((Long) jsonObject.get(Constants.SANTA_BUDGET));

      JSONObject jsonInitialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
      JSONArray jsonChildArray = (JSONArray) jsonInitialData.get(Constants.CHILDREN);
      JSONArray jsonSantaGiftList = (JSONArray) jsonInitialData.get(Constants.SANTA_GIFTS_LIST);

      if (jsonChildArray != null) {
        for (Object jsonChild : jsonChildArray) {
          // assert false;
          try {
            initData
                .getChildren()
                .add(
                    new Child(
                        (Math.toIntExact((Long) ((JSONObject) jsonChild).get(Constants.ID))),
                        ((String) ((JSONObject) jsonChild).get(Constants.LAST_NAME)),
                        ((String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME)),
                        (Math.toIntExact((Long) ((JSONObject) jsonChild).get(Constants.AGE))),
                        ((String) ((JSONObject) jsonChild).get(Constants.CITY)),
                        (Double.valueOf((Long) ((JSONObject) jsonChild).get(Constants.NICE_SCORE))),
                        Utils.convertJSONArrayString(
                            (JSONArray) ((JSONObject) jsonChild).get(Constants.GIFTS_PREFERENCES)),
                        (Double.valueOf(
                            (Long) ((JSONObject) jsonChild).get(Constants.NICE_SCORE_BONUS))),
                        ((String) ((JSONObject) jsonChild).get(Constants.ELF))));
          } catch (NullPointerException ignored) {

            initData
                .getChildren()
                .add(
                    new Child(
                        (Math.toIntExact((Long) ((JSONObject) jsonChild).get(Constants.ID))),
                        ((String) ((JSONObject) jsonChild).get(Constants.LAST_NAME)),
                        ((String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME)),
                        (Math.toIntExact((Long) ((JSONObject) jsonChild).get(Constants.AGE))),
                        ((String) ((JSONObject) jsonChild).get(Constants.CITY)),
                        null,
                        Utils.convertJSONArrayString(
                            (JSONArray) ((JSONObject) jsonChild).get(Constants.GIFTS_PREFERENCES)),
                        (Double.valueOf(
                            (Long) ((JSONObject) jsonChild).get(Constants.NICE_SCORE_BONUS))),
                        ((String) ((JSONObject) jsonChild).get(Constants.ELF))));
          }
        }
      } else {
        System.out.println("NU EXISTA COPII");
      }

      if (jsonSantaGiftList != null) {
        for (Object jsonGift : jsonSantaGiftList) {
          assert false;
          try {
            initData
                .getSantaGiftsList()
                .add(
                    new Gift(
                        ((String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME)),
                        Double.valueOf((Long) ((JSONObject) jsonGift).get(Constants.PRICE)),
                        ((String) ((JSONObject) jsonGift).get(Constants.CATEGORY)),
                        Math.toIntExact((Long) ((JSONObject) jsonGift).get(Constants.QUANTITY))));
          } catch (NullPointerException ignored) {
            System.out.println("NU EXISTA gifts");
          }
        }
      } else {
        System.out.println("NU EXISTA CADOURI");
      }

      JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
      if (jsonAnnualChanges != null) {
        for (Object jsonAnnual : jsonAnnualChanges) {

          Double newBudget =
              Double.valueOf((Long) ((JSONObject) jsonAnnual).get(Constants.NEW_SANTA_BUDGET));
          JSONArray annGifts = (JSONArray) ((JSONObject) jsonAnnual).get(Constants.NEW_GIFTS);
          JSONArray annChildren = (JSONArray) ((JSONObject) jsonAnnual).get(Constants.NEW_CHILDREN);
          JSONArray annChildUpdate =
              (JSONArray) ((JSONObject) jsonAnnual).get(Constants.CHILDREN_UPDATES);
          ArrayList<Gift> newGifts = new ArrayList<>();
          if (annGifts != null) {
            for (Object gifts : annGifts) {
              try {
                newGifts.add(
                    new Gift(
                        ((String) ((JSONObject) gifts).get(Constants.PRODUCT_NAME)),
                        Double.valueOf((Long) ((JSONObject) gifts).get(Constants.PRICE)),
                        ((String) ((JSONObject) gifts).get(Constants.CATEGORY)),
                        Math.toIntExact((Long) ((JSONObject) gifts).get(Constants.QUANTITY))));
              } catch (NullPointerException ignored) {
                newGifts.add(
                    new Gift(
                        ((String) ((JSONObject) gifts).get(Constants.PRODUCT_NAME)),
                        null,
                        ((String) ((JSONObject) gifts).get(Constants.CATEGORY)),
                        Math.toIntExact((Long) ((JSONObject) gifts).get(Constants.QUANTITY))));
              }
            }
          }

          ArrayList<Child> newChilds = new ArrayList<>();
          if (annChildren != null) {
            for (Object child : annChildren) {
              newChilds.add(
                  new Child(
                      (Math.toIntExact((Long) ((JSONObject) child).get(Constants.ID))),
                      ((String) ((JSONObject) child).get(Constants.LAST_NAME)),
                      ((String) ((JSONObject) child).get(Constants.FIRST_NAME)),
                      (Math.toIntExact((Long) ((JSONObject) child).get(Constants.AGE))),
                      ((String) ((JSONObject) child).get(Constants.CITY)),
                      (Double.valueOf((Long) ((JSONObject) child).get(Constants.NICE_SCORE))),
                      Utils.convertJSONArrayString(
                          (JSONArray) ((JSONObject) child).get(Constants.GIFTS_PREFERENCES)),
                      (Double.valueOf((Long) ((JSONObject) child).get(Constants.NICE_SCORE_BONUS))),
                      ((String) ((JSONObject) child).get(Constants.ELF))));
            }
          }

          ArrayList<ChildUpdate> newChildUpdt = new ArrayList<>();
          if (annChildUpdate != null) {
            for (Object upd : annChildUpdate) {

              try {
                newChildUpdt.add(
                    new ChildUpdate(
                        (Math.toIntExact((Long) ((JSONObject) upd).get(Constants.ID))),
                        (Double.valueOf((Long) ((JSONObject) upd).get(Constants.NICE_SCORE))),
                        Utils.convertJSONArrayString(
                            (JSONArray) ((JSONObject) upd).get(Constants.GIFTS_PREFERENCES)),
                        ((String) ((JSONObject) upd).get(Constants.ELF))));
              } catch (NullPointerException ignored) {
                newChildUpdt.add(
                    new ChildUpdate(
                        (Math.toIntExact((Long) ((JSONObject) upd).get(Constants.ID))),
                        null,
                        Utils.convertJSONArrayString(
                            (JSONArray) ((JSONObject) upd).get(Constants.GIFTS_PREFERENCES)),
                        ((String) ((JSONObject) upd).get(Constants.ELF))));
              }
            }
          }
          String strategy = ((String) ((JSONObject) jsonAnnual).get(Constants.STRATEGY));
          annualChanges.add(
              new AnnualChange(newBudget, newGifts, newChilds, newChildUpdt, strategy));
        }
      } else {
        System.out.println("NU EXISTA SCHIMBARI ANUALE");
      }

    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }

    return new ActionData(numberOfYears, santaBudget, initData, annualChanges);
  }
}
