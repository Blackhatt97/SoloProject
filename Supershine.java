import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Supershine
{
  public static void main(String[] args)
    throws Exception
  {
    Console console = System.console();
    String name = console.readLine("Please insert your name ", new Object[0]);
    File file = new File(name);
    double balance;
    if (file.exists())
    {
      System.out.println("Welcome to your account Mr." + name);
      balance = readFile(file);
      boolean hasbought = false;
      Order newOrder = new Order(balance, name);
      
      int yourOp = 1;
      String option = null;
      while (yourOp != 0)
      {
        System.out.println("Welcome to the SuperShine Car Wash! Please choose from the \nlist below what would you like to do today...");
        System.out.println("1 - Services");
        System.out.println("2 - Recharge");
        System.out.println("3 - Wash Statistics");
        System.out.println("4 - Balance");
        System.out.println("5 - Exit");
        String yourOption = console.readLine("You selected: ", new Object[0]);
        yourOp = Integer.parseInt(yourOption);
        switch (yourOp)
        {
        case 1: 
          hasbought = caseOne(hasbought, name, balance, option, newOrder);
          break;
        case 2: 
          caseTwo(option, newOrder, balance, name);
          break;
        case 3: 
          caseThree(option, balance, name, newOrder);
          break;
        case 4: 
          caseFour(option, balance, name, newOrder);
          break;
        case 5: 
          option = "Have a nice day!";
          System.out.println(option);
          yourOp = 0;
        }
      }
    }
    else
    {
      System.out.println("You do noit have an account in our Database. Please pay the 1000kr subscription fee.");
      balance = new Subscription(name, file);
    }
  }
  
  public static void readStats(File file)
    throws FileNotFoundException
  {
    System.out.println("Reading the file...");
    Scanner input = new Scanner(file);
    while (input.hasNextLine()) {
      System.out.println(input.nextLine());
    }
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
    System.out.println("Writing to file");
    file.println(line);
  }
  
  public static boolean caseOne(boolean hasbought, String name, double balance, String option, Order newOrder)
    throws Exception
  {
    Console console = System.console();
    if (!hasbought)
    {
      option = "You have chosen services!";
      System.out.println(option);
      System.out.println("1 - Regular Wash - 200kr");
      System.out.println("2 - Ocean Flush Wash - 400kr");
      System.out.println("3 - Royal Wash - 500kr");
      System.out.println("4 - Once In A Lifetime Wash - 550kr");
      
      int program = Integer.parseInt(console.readLine("Your program is: ", new Object[0]));
      
      DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      switch (program)
      {
      case 1: 
        if (balance >= 200.0D)
        {
          String serviceOption = "You have chosen the regular wash.";
          System.out.println(serviceOption);
          newOrder.setDeductbalance(balance, 200.0D, name);
          hasbought = true;
          FileWriter fw = new FileWriter(name + "stats", true);
          fw.write(date + serviceOption + System.getProperty("line.separator"));
          fw.close();
        }
        else
        {
          System.out.println("Not enough founds. Please recharge your card");
        }
        break;
      case 2: 
        if (balance >= 400.0D)
        {
          String serviceOption = "You have chosen the ocean flush wash.";
          System.out.println(serviceOption);
          newOrder.setDeductbalance(balance, 400.0D, name);
          hasbought = true;
          FileWriter fw = new FileWriter(name + "stats", true);
          fw.write(date + serviceOption + System.getProperty("line.separator"));
          fw.close();
        }
        else
        {
          System.out.println("Not enough founds. Please recharge your card");
        }
        break;
      case 3: 
        if (balance >= 500.0D)
        {
          String serviceOption = "You have chosen the royal wash.";
          System.out.println(serviceOption);
          newOrder.setDeductbalance(balance, 500.0D, name);
          hasbought = true;
          FileWriter fw = new FileWriter(name + "stats", true);
          fw.write(date + serviceOption + System.getProperty("line.separator"));
          fw.close();
        }
        else
        {
          System.out.println("Not enough founds. Please recharge your card");
        }
        break;
      case 4: 
        if (balance >= 550.0D)
        {
          String serviceOption = "You have chosen the once in a lifetime wash.";
          System.out.println(serviceOption);
          newOrder.setDeductbalance(balance, 550.0D, name);
          hasbought = true;
          FileWriter fw = new FileWriter(name + "stats", true);
          fw.write(date + serviceOption + System.getProperty("line.separator"));
          fw.close();
        }
        else
        {
          System.out.println("Not enough founds. Please recharge your card");
        }
        break;
      case 5: 
        System.out.println("Invalid option");
      }
    }
    else
    {
      System.out.println("You have already bought a wash, please exit and proceed to the washing machine.");
    }
    return true;
  }
  
  public static void caseTwo(String option, Order newOrder, double balance, String name)
    throws Exception
  {
    Console console = System.console();
    
    option = "You want to charge your account!Enter an amount between 200 and 1000...";
    System.out.println(option);
    String rechargeAmountString = console.readLine("Amount: ", new Object[0]);
    double rechargeAmountDouble = Double.parseDouble(rechargeAmountString);
    if ((rechargeAmountDouble > 199.0D) && (rechargeAmountDouble < 1001.0D)) {
      newOrder.setAddbalance(balance, rechargeAmountDouble, name);
    } else {
      System.out.println("The amount you entered is invalid");
    }
  }
  
  public static void caseThree(String option, double balance, String name, Order newOrder)
    throws Exception
  {
    option = "You have chosen to see your wash statistics";
    System.out.println(option);
    File stats = new File(name + "stats");
    readStats(stats);
  }
  
  public static void caseFour(String option, double balance, String name, Order newOrder)
    throws Exception
  {
    option = "You have chosen to see your balance!";
    System.out.println(option);
    System.out.println("Your current balance is: " + newOrder.getBalance());
  }
}
