

import java.util.Random;
public class Cards {
 private int NUM_CARDS = 52;
 private int[] card = new int[NUM_CARDS];

 private Random rn = new Random();
 private int top = -1;
   private char[] suit = {'C', 'D', 'H', 'S'};
 public Cards() {
  for(int i = 0; i < NUM_CARDS; i++) {
   card[i] = i;
   
  }
 }
 
  //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Draw the top card from the deck.
     */
 
 /*
    def draw (): Int = 
    {
        top += 1
        if (top >= NUM_CARDS) -1 else card(top)
    } // draw
    */
 
 public int draw() {
  top+=1;
  if(top >= NUM_CARDS) return -1;
  else return card[top];
 }

   //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** Shuffle the deck of cards.
     *//*
    def shuffle ()
    {
        for (i <- 0 until NUM_CARDS) swap (card, i, rn.igen)
        top = -1
    } // shuffle
    */
 public void shuffle() {
  for(int i =0; i < NUM_CARDS; i++) {
   swap(i,rn.nextInt(NUM_CARDS)); //RANDOM Number
  }
 }
 
 public String value(int x) {
 return x%13+1 + "." + suit[x/13]; //def value (i : Int): Tuple2 [Int, Char] = (i % 13 + 1, suit (i / 13)

 }
 
 public String toString() {
  String temp ="";
  temp+= "Cards ( " ;
   for (int c : card) {temp+= value(c) +", ";} 
  temp+= " )";
  return temp;
 }
 
 public void swap(int x, int y) {
  int temp = card[x];
  card[x] = card[y];
  card[y] = temp;
 }
 
 
 
 
}
 
 
