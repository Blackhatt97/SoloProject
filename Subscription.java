import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Subscription
{
  private String name;
  private File file;
  
  public Subscription(String name, File file)
    throws FileNotFoundException
  {
    Console console = System.console();
    this.name = name;
    this.file = new File(name);
    if (file.exists())
    {
      System.out.println("The file already exists.");
    }
    else
    {
      System.out.println("The file is being created... ");
      PrintStream writeFile = createFile(name);
      PrintStream writeFiles = createFile(name + "stats");
      writeToFile(writeFile, "1000.0");
    }
  }
  
  public static void readFile(File file)
    throws FileNotFoundException
  {
    System.out.println("Reading the file...");
    Scanner input = new Scanner(file);
    while (input.hasNextLine()) {
      System.out.println(input.nextLine());
    }
  }
  
  public static PrintStream createFile(String filePath)
    throws FileNotFoundException
  {
    System.out.println("Creating file...");
    return new PrintStream(new File(filePath));
  }
  
  public static void writeToFile(PrintStream file, String line)
  {
    System.out.println("Writing to file");
    file.println(line);
  }
}
