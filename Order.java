import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Order
{
  private double balance;
  private String name;
  private File file;
  
  public Order(double balance, String name)
  {
    this.balance = balance;
    this.name = name;
  }
  
  public void setAddbalance(double balance, double amount, String name)
    throws Exception
  {
    this.balance = (balance + amount);
    String balanceS = String.valueOf(this.balance);
    PrintStream writeFile = createFile(name);
    writeToFile(writeFile, balanceS);
  }
  
  public void setDeductbalance(double balance, double amount, String name)
    throws Exception
  {
    this.balance = (balance - amount);
    String balanceS = String.valueOf(this.balance);
    PrintStream writeFile = createFile(name);
    writeToFile(writeFile, balanceS);
  }
  
  public double getBalance()
  {
    return this.balance;
  }
  
  public static double readFile(File file)
    throws FileNotFoundException, IOException
  {
    System.out.println("Reading the file...");
    String text = null;
    BufferedReader brTest = new BufferedReader(new FileReader(file));
    text = brTest.readLine();
    return Double.parseDouble(text);
  }
  
  public static PrintStream createFile(String filePath)
    throws FileNotFoundException
  {
    return new PrintStream(new File(filePath));
  }
  
  public static void writeToFile(PrintStream file, String line)
  {
    System.out.println("Updating the balance...");
    file.println(line);
  }
}
