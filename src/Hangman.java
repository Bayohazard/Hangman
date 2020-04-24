import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
  private ArrayList<Character> lettersUsed;
  private ArrayList<String> wordList;
  
  private String wordToGuess;
  private ArrayList<Letter> wordLetters;
  private int numWrongGuesses = 0;
  
  private Scanner keyboard;
  
  public Hangman(ArrayList<String> wordList) {
    this.wordList = wordList;
    getRandomWord();
    this.lettersUsed = new ArrayList<>();
    changeWordToChars();
    keyboard = new Scanner(System.in);
    
  }
  
  public void startGame() {
    boolean gameOver = false;
    while(!gameOver) {
      char letterGuessed = getPlayerInput();
      checkPlayerInput(letterGuessed);
      updateGameStats(letterGuessed);
      
      if(isCorrectGuess(letterGuessed)) {
        updateLetters(letterGuessed);
      } else {
        drawPerson(++numWrongGuesses);
      }
      
      printLettersUsed();
      
      gameOver = hasGuessedWord() || hasLost();
      
    }
    if(wantsToPlayAgain()) {
      restartGame();
      startGame();
    }
  }
  
  private void restartGame() {
    getRandomWord();
    changeWordToChars();
    this.numWrongGuesses = 0;
    lettersUsed.clear();
  }
  
  private boolean hasGuessedWord() {
    for(Letter l : wordLetters) {
      if(!l.beenGuessed()) {
        return false;
      }
    }
    System.out.println("You guessed the word!");
    return true;
  }
  
  private boolean hasLost() {
    return numWrongGuesses >= 6;
  }
  
  private void printLettersUsed() {
    System.out.print("Letters used: ");
    for(char letter : lettersUsed) {
      System.out.print(letter + " ");
    }
    System.out.println();
  }
  
  private void printLetters() {
    for(Letter l : wordLetters) {
      System.out.print(l.beenGuessed() ? l.getLetter() + " " : "_ ");
    }
    System.out.println();
  }
  
  private void updateLetters(char letter) {
    System.out.println("Correct!");
    for(Letter l : wordLetters) {
      if(l.getLetter() == letter) {
        l.setBeenGuessed();
      }
    }
  }
  
  private void drawPerson(int numWrongGuesses) {
    System.out.println("Wrong.");
    switch(numWrongGuesses) {
      case 1:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("|");
        System.out.println("|");
        break;
        
      case 2:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("|  |");
        System.out.println("|");
        break;
        
      case 3:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|");
        System.out.println("|");
        break;
        
      case 4:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|\\");
        System.out.println("|");
        break;
        
      case 5:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|\\");
        System.out.println("| /");
        break;
        
      case 6:
        System.out.println("___");
        System.out.println("|  O ");
        System.out.println("| /|\\");
        System.out.println("| / \\");
        printLosingMessage();
        break;
        
      default:
        System.out.println("You should not see this message");
    }
  }
  
  private void printLosingMessage() {
    System.out.println("You lost!");
    System.out.println("The word was " + this.wordToGuess);
  }
  
  private void checkPlayerInput(char letterGuessed) {
    while(!isValidInput(letterGuessed)) {
      System.out.println("That letter has already been used. Try again.");
      letterGuessed = getPlayerInput();
    }
  }
  
  private char getPlayerInput() {
    printLetters();
    System.out.println("Enter a letter as a guess: ");
    String input = keyboard.nextLine();
    while(input.isEmpty()) {
      System.out.println("You did not enter a letter. Try again.");
      input = keyboard.nextLine();
    }
    input = input.toLowerCase();
    return input.charAt(0);
  }
  
  private boolean isCorrectGuess(char letter) {
    boolean isCorrectGuess = false;
    for(Letter l : wordLetters) {
      if(l.getLetter() == letter) {
        l.setBeenGuessed();
        isCorrectGuess = true;
      }
    }
    return isCorrectGuess;
  }
  
  private void updateGameStats(char letter) {
    lettersUsed.add(letter);
  }
  
  private void changeWordToChars() {
    wordLetters = new ArrayList<>();
    for(int i = 0; i < wordToGuess.length(); i++) {
      char letter = wordToGuess.charAt(i);
      wordLetters.add(new Letter(letter, false));
    }
  }
  
  private boolean isValidInput(char letter) {
    for(char l : lettersUsed) {
      if(l == letter) {
        return false;
      }
    }
    return true;
  }
  
  private boolean wantsToPlayAgain() {
    System.out.println("Do you want to play again? y | n");
    String answer = keyboard.nextLine();
    answer = answer.toLowerCase();
    return answer.charAt(0) == 'y';
  }
  
  private void getRandomWord() {
    this.wordToGuess = wordList.get(new Random().nextInt(wordList.size()));
  }
}
