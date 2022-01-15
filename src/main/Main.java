package main;

import checker.Checker;
import common.Constants;
import data.ActionData;
import fileio.InputLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/** Class used to run the code */
public final class Main {

  private Main() {
    /// constructor for checkstyle
  }
  /**
   * This method is used to call the checker which calculates the score
   *
   * @param args the arguments used to call the main method
   */
  public static void main(final String[] args) throws IOException {

    File directory = new File(Constants.TESTS_PATH);
    Path path = Paths.get(Constants.OUTPUT_DIR);

    if (!Files.exists(path)) {
      Files.createDirectories(path);
    }

    File outputDirectory = new File(Constants.OUTPUT_DIR);

    Checker.deleteFiles(outputDirectory.listFiles());

    int i = 1;
    // Pentru a putea parcurge testele in ordinea lor, nu in ordine alfabetica
    // cum sunt preluate de sistem.
    for (File ignored : Objects.requireNonNull(directory.listFiles())) {

      String filepath = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;
      String inputFile = Constants.TESTS_PATH + "test" + i + Constants.FILE_EXTENSION;

      File out = new File(filepath);
      File in = new File(inputFile);

      boolean isCreated = out.createNewFile();
      if (isCreated) {
        action(in.getAbsolutePath(), filepath);
      }

      i++;
    }

    Checker.calculateScore();
  }

  /**
   * @param filePath1 for input file
   * @param filePath2 for output file
   * @throws IOException in case of exceptions to reading / writing
   */
  public static void action(final String filePath1, final String filePath2) throws IOException {
    InputLoader inputLoader = new InputLoader(filePath1);
    ActionData input = inputLoader.readData();

    ProcessInput processInput = new ProcessInput();
    processInput.init(input, filePath2);
  }
}
