/* GameState Class
 * 
 * Created: November 11, 2014
 * Last Modified: November 12, 2014
 * 
 * The Game State Class will collect all visual GUI data from the poker client including:
 * 
 * -Stack Sizes
 * -Bets Made
 * -Pot Size
 * -Players Names
 * -Active Players
 * 
 * TO DO: 
 * - Change coordinates in card methods using Aspect Ratio Class so that they can dynamically change to any window size.
 * - Develop Simple algorithm for figuring out how many seats are at the poker table (2, 6, 9 or 10)
 * - Either hard code button positions for each table size or create a txt. file that has all different button positions for different table sizes.
 * - Create a toString method that prints out the entire GameState of a table.
 * 
 */

import java.awt.AWTException;
import java.awt.Robot;

public class GameState {
	public static void main(String [] args) throws AWTException {
		
		//AspectRatio Calculator Goes here (Maybe send aspect ratio object to each method?)
		Hand hand = new Hand();
		
		getSeats();
		getHoleCards(hand);
		getButton();
		getFlop(hand);
		getTurn(hand);
		getRiver(hand);
		
		System.out.println("End of Processing.");
	}
	
	/****************************************Get Seats**************************************/
	public static void getSeats(){
		
		/* Checks how many seats are at the table.
		 * This allows the other methods to know where to look
		 * Must be compatible for 2, 6, 9 and 10 seats
		 */
	}
	
	/*************************************Get Hole Cards************************************/
	public static void getHoleCards(Hand hand) throws AWTException{
		//Reads Card identification squares and returns int[] for the creation of a new hand.
		
		int x = 985; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 625; 
		
		Robot robot = new Robot();
		int[] cardOrdinals = new int[2]; 
		
		wait(x,y);
		//The Green value of the pixel's RGB is equal to the Card's Ordinal Value
		cardOrdinals[0] = robot.getPixelColor(x, y).getGreen(); //Hole Card 1
		cardOrdinals[1] = robot.getPixelColor(x + 105, y).getGreen(); //Hole Card 2
		
		System.out.println("Hole Cards: " + Card.get(cardOrdinals[0]) + "" + Card.get(cardOrdinals[1]));
		hand.reset(cardOrdinals);
	}
	
	/****************************************Get Flop***************************************/
	public static void getFlop(Hand hand) throws AWTException{
		
		int x = 575; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 315; 
		int gap = 115;
		
		Robot robot = new Robot();
		
		wait(x,y);
		//The Green value of the pixel's RGB is equal to the Card's Ordinal Value
		hand.addCard(robot.getPixelColor(x, y).getGreen()); //Flop Card 1
		hand.addCard(robot.getPixelColor(x + gap, y).getGreen()); //Flop Card 2
		hand.addCard(robot.getPixelColor(x + (2*gap), y).getGreen()); //Flop Card 3
		
		int rank = Evaluator.evaluate(hand);
		System.out.println(rank);
	}
	
	/****************************************Get Turn***************************************/
	public static void getTurn(Hand hand) throws AWTException{
		int x = 575; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 315;
		int gap = 115;
		
		Robot robot = new Robot();
		
		wait(x + (3*gap), y);
		hand.addCard(robot.getPixelColor(x + (3*gap),y).getGreen());
		
		int rank = Evaluator.evaluate(hand);
		System.out.println(rank);
	}
	
	/****************************************Get River**************************************/
	public static void getRiver(Hand hand) throws AWTException{
		int x = 575; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 315;
		int gap = 115;
		
		Robot robot = new Robot();
		
		wait(x + (4*gap), y);
		hand.addCard(robot.getPixelColor(x + (4*gap),y).getGreen());
		
		int rank = Evaluator.evaluate(hand);
		System.out.println(rank);
	}
	
	/****************************************Get Button*************************************/
	public static void getButton() throws AWTException{
		//
	}
	
	/****************************************Wait*************************************/
	public static void wait(int x, int y) throws AWTException{
		//Creates a loop that waits until the desired card shows up in position x, y
		Robot robot = new Robot();
		while(!(robot.getPixelColor(x,y).getBlue() == 255)){}
	}
	
	/*****************************************To String*************************************/
	public String toString(){
		String s = "\\---------------------GAME STATE----------------------\\\n";
		String seats = "Seats: ";
		String button = "Button : \n";
		String hole = "Hole Cards: \n";
		String flop = "The Flop: \n";
		String Turn = "Turn: \n";
		String river = "River: \n";
		
		return s;
	}
}