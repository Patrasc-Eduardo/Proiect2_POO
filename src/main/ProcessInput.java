package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.ActionData;
import data.AnnualChange;
import database.MainDB;
import designpatterns.command.Client;
import designpatterns.command.InitDatabaseCommand;
import designpatterns.command.ProcessChildListCommand;
import designpatterns.command.SendGiftCommand;
import designpatterns.command.UpdateOldDataCommand;
import fileio.Output;
import utilsprocess.UtilsProcess;
import java.io.File;
import java.io.IOException;

public final class ProcessInput {
  /**
   * Prelucreaza datele pentru runda 0.
   *
   * @param mainDB baza de date cu care lucram
   * @param input inputul de unde luam informatiile si le stocam in baza de date
   * @param output outputul in care stocam rezultatele
   */
  public void processRoundZero(final MainDB mainDB, final ActionData input, final Output output) {

    Client client = new Client();

    client.executeAction(new InitDatabaseCommand(input, mainDB));

    client.executeAction(new ProcessChildListCommand(mainDB));

    client.executeAction(new SendGiftCommand(mainDB, "id"));

    UtilsProcess.sendToOutput(mainDB, output);
  }

  /**
   * Prelucreaza datele pentru restul rundelor
   *
   * @param mainDB baza de date cu care lucram
   * @param input inputul de unde luam informatiile si le stocam in baza de date
   * @param output outputul in care stocam rezultatele
   */
  public void processAllRounds(final MainDB mainDB, final ActionData input, final Output output) {

    Client client = new Client();

    for (int i = 0; i < input.getNumberOfYears(); i++) {

      AnnualChange anChange = input.getAnnualChanges().get(i);

      client.executeAction(new UpdateOldDataCommand(anChange, mainDB));

      client.executeAction(new ProcessChildListCommand(mainDB));

      mainDB.makeCitiesList();

      client.executeAction(new SendGiftCommand(mainDB, anChange.getStrategy()));

      UtilsProcess.sendToOutput(mainDB, output);

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
  }
}
