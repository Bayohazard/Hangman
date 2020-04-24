import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println("What is the minimum amount of letters per word?");
    int letterLimit = 3;
    try {
      letterLimit = keyboard.nextInt();
    } catch(InputMismatchException e) {
      System.out.println("Something went wrong.");
      System.exit(0);
    }
    
    WordParser parser = new WordParser(letterLimit);
    ArrayList<String> parsedList = parser.getParsedList();
    
    Hangman hangman = new Hangman(parsedList);
    hangman.startGame();
    System.out.println("Goodbye.");
  }
}

