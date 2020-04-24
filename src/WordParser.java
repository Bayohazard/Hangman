import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordParser {
  private int letterLimit;
  
  private Scanner inputFile;
  private PrintWriter filteredList;
  
  private ArrayList<String> parsedList;
  
  public WordParser(int letterLimit) {
    this.letterLimit = letterLimit;
    
    openInputFile("words.txt");
    createNewFile();
    filterList();
    parseList();
  }
  
  /**
   * Returns the list of words after words of certain length
   * have been filtered, as an ArrayList
   *
   * @return An ArrayList of words after filtering out words of
   * a certain length
   */
  public ArrayList<String> getParsedList() {
    return this.parsedList;
  }
  
  /**
   * Converts lines from a text file into strings of an ArrayList
   */
  private void parseList() {
    openInputFile("new_words.txt");
    parsedList = new ArrayList<>();
    while(inputFile.hasNextLine()) {
      parsedList.add(inputFile.nextLine());
    }
    inputFile.close();
  }
  
  /**
   * Copies strings from one file into another only if the string
   * is a certain length
   */
  private void filterList() {
    while(inputFile.hasNextLine()) {
      String word = inputFile.nextLine();
      if(word.length() >= letterLimit) {
        filteredList.println(word);
      }
    }
    inputFile.close();
  }
  
  /**
   * Creates a PrintWriter object
   */
  private void createNewFile() {
    try {
      filteredList = new PrintWriter("new_words.txt");
    } catch(FileNotFoundException e) {
      System.out.println("Sorry. The file could not be found.");
      System.exit(0);
    }
  }
  
  /**
   * Creates a Scanner object using the given file name
   *
   * @param fileName The name of the file to be used to create the
   *                 Scanner object
   */
  private void openInputFile(String fileName) {
    try {
      inputFile = new Scanner(new File(fileName));
    } catch(FileNotFoundException e) {
      System.out.println("Sorry. The file could not be found.");
      System.exit(0);
    }
  }
}
