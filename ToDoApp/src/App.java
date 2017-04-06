import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

  public static void main(String[] args) {

    if (args.length == 0) {
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
    } else if (args[0].contains("l") && args[0].startsWith("-")) {
      System.out.println("LETTER");
    } else if (args[0].contains("a") && args[0].startsWith("-")) {
      System.out.println("APPLE");
    } else if (args[0].contains("r") && args[0].startsWith("-")) {
      System.out.println("ROBOT");
    } else if (args[0].contains("c") && args[0].startsWith("-")) {
      System.out.println("CENTER");
    }
  }
}
