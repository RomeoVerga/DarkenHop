/* GameState Class
 * 
 * Created November 11, 2014
 * 
 * The Game State Class will collect all GUI data from the poker client including:
 * 
 * -Hole Cards
 * -Flop Cards
 * -Turn Card
 * -River Card
 * 
 * -Stack Sizes
 * -Bets Made
 * -Pot Size
 * 
 * -Players Names
 * -Button Position
 * -Active Players
 * 
 */

import java.awt.AWTException;
import java.awt.Robot;

public class GameState {
	public static void main(String [] args) throws AWTException {
		
		//AspectRatio Calculator Goes here (Maybe send Aspect Ratio object to each method?)
		getHoleCards();
		
	}
	
	//Reads Card identification squares and returns int[] for the creation of a new hand.
	public static int[] getHoleCards() throws AWTException{
		
		int x = 0; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 0; 
		
		Robot robot = new Robot();
		int[] cardOrdinals = new int[2]; 
		
		//The Green value of the pixel's RGB is equal to the Card's Ordinal Value
		cardOrdinals[0] = robot.getPixelColor(x, y).getGreen(); //Hole Card 1
		cardOrdinals[1] = robot.getPixelColor(x, y).getGreen(); //Hole Card 2
		
		return cardOrdinals;
	}
}