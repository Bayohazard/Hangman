public class Letter {
  private char letter;
  private boolean beenGuessed;
  
  public Letter(char letter, boolean beenGuessed) {
    this.letter = letter;
    this.beenGuessed = beenGuessed;
  }
  
  public char getLetter() {
    return this.letter;
  }
  
  public boolean beenGuessed() {
    return beenGuessed;
  }
  
  public void setBeenGuessed() {
    this.beenGuessed = true;
  }
}
