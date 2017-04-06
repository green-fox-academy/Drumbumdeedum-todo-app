import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ToDoList {

  public void listTasks (String[] args) {
    if (args.length == 0) {
      printUsageInformation();
    } else if (args[0].contains("l") && args[0].startsWith("-")) {
      readListLines();
    } else if (args[0].contains("a") && args[0].startsWith("-")) {
      addNewTask(args);
    } else if (args[0].contains("r") && args[0].startsWith("-")) {
      removeTask(args);
    } else if (args[0].contains("c") && args[0].startsWith("-")) {
      System.out.println("complete task");
    }
  }

  public void printUsageInformation() {
    System.out.println(
        "\n" +
            "*****************************\n" +
            "* Java Todo application     *\n" +
            "* =====================     *\n" +
            "*                           *\n" +
            "* Command line arguments:   *\n" +
            "*  -l   Lists all the tasks *\n" +
            "*  -a   Adds a new task     *\n" +
            "*  -r   Removes a task      *\n" +
            "*  -c   Completes a task    *\n" +
            "*****************************\n" +
            "\n"
    );
  }

  public void readListLines() {
    try {
      Path listPath = Paths.get("list.txt");
      List<String> lines = Files.readAllLines(listPath);

      int lineNr = 1;
      if (lines.get(0) != null) {
        for (int i = 0; i < lines.size(); i++) {
          System.out.println(lineNr + " - " + lines.get(i));
          lineNr++;
        }
      }
    } catch (Exception e) {
      System.out.println("No todos for today! :)");
    }
  }

  public void addNewTask(String[] args) {
    try {
      Path listPath = Paths.get("list.txt");
      List<String> lines = Files.readAllLines(listPath);
      lines.add(args[1]);
      Files.write(listPath, lines, Charset.defaultCharset());
    } catch (Exception e) {
      System.out.println("Whooopsie, and error has occurred!");
    }
  }

  public void removeTask(String[] args) {
    try {
      Path listPath = Paths.get("list.txt");
      List<String> lines = Files.readAllLines(listPath);

      if (lines.size() >= 2) {
        int argLineNumber = Integer.parseInt(args[1]);
        lines.remove(argLineNumber - 1);
      }
      Files.write(listPath, lines, Charset.defaultCharset());
    } catch (Exception e) {
      System.out.println("Whooopsie, and error has occurred!");
    }
  }
}
