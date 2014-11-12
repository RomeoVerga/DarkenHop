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
 * - Have the getHoleCards method actually create a hand object (Does hand object just want hole cards or hole cards + community board?)
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
		getSeats();
		getHoleCards();
		getButton();
		getFlop();
		getTurn();
		getRiver();
		
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
	public static int[] getHoleCards() throws AWTException{
		//Reads Card identification squares and returns int[] for the creation of a new hand.
		
		int x = 985; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 625; 
		
		Robot robot = new Robot();
		int[] cardOrdinals = new int[2]; 
		
		//The Green value of the pixel's RGB is equal to the Card's Ordinal Value
		cardOrdinals[0] = robot.getPixelColor(x, y).getGreen(); //Hole Card 1
		cardOrdinals[1] = robot.getPixelColor(x + 105, y).getGreen(); //Hole Card 2
		
		if(cardOrdinals[0] > 51 || cardOrdinals[1] > 51)
			System.out.println("NO HOLE CARDS DETECTED");
		else
			System.out.println("Hole Cards: " + Card.get(cardOrdinals[0]) + "" + Card.get(cardOrdinals[1]));
		
		return cardOrdinals;
	}
	
	/****************************************Get Flop***************************************/
	public static void getFlop() throws AWTException{
		
		int x = 575; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 314; 
		
		Robot robot = new Robot();
		int[] cardOrdinals = new int[3]; 
		
		//The Green value of the pixel's RGB is equal to the Card's Ordinal Value
		cardOrdinals[0] = robot.getPixelColor(x, y).getGreen(); //Flop Card 1
		cardOrdinals[1] = robot.getPixelColor(x + 115, y).getGreen(); //Flop Card 2
		cardOrdinals[2] = robot.getPixelColor(x + 230, y).getGreen(); //Flop Card 3
		
		if(cardOrdinals[0] > 51 || cardOrdinals[1] > 51 || cardOrdinals[2] > 51)
			System.out.println("NO FLOP CARDS DETECTED");
		else
			System.out.println("The Flop: " + Card.get(cardOrdinals[0]) + "" + Card.get(cardOrdinals[1]) + "" + Card.get(cardOrdinals[2]));
		
	}
	
	/****************************************Get Turn***************************************/
	public static void getTurn() throws AWTException{
		int x = 575; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 314;
		int cardOrdinal;
		
		Robot robot = new Robot();
		cardOrdinal = robot.getPixelColor(x,y).getGreen();
	}
	
	/****************************************Get River**************************************/
	public static void getRiver() throws AWTException{
		int x = 575; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 314;
		int cardOrdinal;
		
		Robot robot = new Robot();
		cardOrdinal = robot.getPixelColor(x,y).getGreen();
	}
	
	/****************************************Get Button*************************************/
	public static void getButton() throws AWTException{
		//
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