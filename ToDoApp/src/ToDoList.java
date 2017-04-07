import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
      checkTask(args);
    } else if ((!args[0].startsWith("-")) || (args[0].length() > 2)) {
      unsupportedArgument();
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
      System.out.println("Unable to add: no task provided!");
    }
  }

  public void removeTask(String[] args) {
    try {
      Path listPath = Paths.get("list.txt");
      List<String> lines = Files.readAllLines(listPath);
      int argLineNumber = Integer.parseInt(args[1]);

      if (lines.size() < argLineNumber) {
        System.out.println("Unable to remove: index out of bound");
      } else if (lines.size() >= 2 && lines.size() > argLineNumber) {
        lines.remove(argLineNumber - 1);
      }
      Files.write(listPath, lines, Charset.defaultCharset());

    } catch (NumberFormatException notANumber) {
      System.out.println("Unable to remove: index not a number");
    } catch (Exception noIndex) {
      System.out.println("Unable to remove: no index provided");
    }
  }

  public void checkTask (String[] args) {
    try {
      Path listPath = Paths.get("list.txt");
      List<String> lines = Files.readAllLines(listPath);

      int argLineNumber = Integer.parseInt(args[1]);
      int lineNr = 0;

      if (argLineNumber > lines.size()) {
        System.out.println("Unable to check: index is out of bound");
      }

      for (int i=0; i < lines.size(); i++) {
        if (!lines.get(i).startsWith("[ ]") && !lines.get(i).startsWith("[X]") ) {
          String buffer1 = lines.get(i);
          lines.remove(i);
          lines.add(i, "[ ]" + buffer1);
        }
      }

      for (int i=0; i < lines.size(); i++) {
        if (lines.get(i).startsWith("[X]")) {
          String buffer = lines.get(i);
          lines.remove(i);
          lines.add(i, buffer);
          lineNr++;
        } else if (lines.get(i).startsWith("[ ]") && (lineNr != argLineNumber -1)) {
          String buffer = lines.get(i);
          lines.remove(i);
          lines.add(i, buffer);
          lineNr++;
        } else if (lines.get(i).startsWith("[ ]") && (lineNr == argLineNumber -1)) {
          String buffer = lines.get(i).substring(3);
          lines.remove(i);
          lines.add(i, "[X]" + buffer);
          lineNr++;
        }
      }
      Files.write(listPath, lines, Charset.defaultCharset());
    } catch (NumberFormatException notANumber) {
      System.out.println("Unable to remove: index not a number");
    } catch (Exception noIndex) {
      System.out.println("Unable to remove: no index provided");
    }
  }

  public void unsupportedArgument() {
    System.out.println();
    System.out.println("Unsupported argument, please try one from the list below:");
    printUsageInformation();
  }
}
