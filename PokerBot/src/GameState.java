/* GameState Class
 * 
 * Created on: November 11, 2014
 * Last Modified: November 13, 2014
 * Created By: Matthew Nemetchek
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
 * - Maybe instead of creating a new robot object each time we should just send the same one?
 */

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameState {
	
	public static boolean fold = false;
	
	public static void main(String [] args) throws AWTException, IOException {
		
		//AspectRatio Calculator Goes here (Maybe send aspect ratio object to each method?)
		Hand hand = new Hand();
		
		getBets();
		//getHoleCards(hand);
		//getButton();
		//getFlop(hand);
		//getTurn(hand);
		//getRiver(hand);
		
		System.out.println("End of Processing.");
	}
	
	/****************************************Fold **************************************/
	public static void fold() throws AWTException {
		int x = 675;
		int y = 975;
		fold = true;
		
		Robot robot = new Robot();
		
		robot.mouseMove(x, y);
		robot.delay(400);
		robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	/****************************************Get Seats**************************************/
	
	/****************************************Get Seats**************************************/
	public static int activePlayers() throws AWTException, IOException{
		//6 seater opponent card coordinates
		int[] x6 ={285, 90, 290, 535, 745};
		int[] y6 = {660, 345, 85, 85, 345};
		int seats = 1; //initialize to 1 because I'm playing
		
		BufferedImage img = OCR.screenCapture();

		for(int i = 0 ; i < x6.length ; i ++){
			if(img.getRGB(x6[i], y6[i])== -1)//checks if pixel is white (opponent cards are white)
				seats++;
		}
		
		System.out.println("Active Players: " + seats);
		return seats;
	}
	
	/*************************************Get Hole Cards************************************/
	public static void getHoleCards(Hand hand) throws AWTException, IOException{
		//Reads Card identification squares and returns int[] for the creation of a new hand.
		
		int x = 500; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 620; 
		
		Robot robot = new Robot();
		int[] cardOrdinals = new int[2]; 
		
		wait(x,y);
		activePlayers();
		
		//The Green component of the pixel's RGB is equal to the Card's Ordinal Value
		cardOrdinals[0] = robot.getPixelColor(x, y).getGreen(); //Hole Card 1
		cardOrdinals[1] = robot.getPixelColor(x + 55, y).getGreen(); //Hole Card 2
		double hs = HoleCards.handStrength(HoleCards.index(cardOrdinals));
		
		System.out.println("Hole Cards: " + Card.get(cardOrdinals[0]) + "" + Card.get(cardOrdinals[1]));
		System.out.println("Hand Strength: " + hs);
		hand.reset(cardOrdinals);
		
		if(hs < 23){
			fold();
			getHoleCards(hand);
		}
	}
	
	/****************************************Get Flop***************************************/
	public static void getFlop(Hand hand) throws AWTException{
		
		int x = 300; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 320; 
		int gap = 55;
		
		Robot robot = new Robot();
		
		wait(x,y);
		
		//The Green component of the pixel's RGB is equal to the Card's Ordinal Value
		hand.addCard(robot.getPixelColor(x, y).getGreen()); //Flop Card 1
		hand.addCard(robot.getPixelColor(x + gap, y).getGreen()); //Flop Card 2
		hand.addCard(robot.getPixelColor(x + (2*gap), y).getGreen()); //Flop Card 3
		
		int rank = Evaluator.evaluate(hand);
		System.out.println(rank);
	}
	
	/****************************************Get Turn***************************************/
	public static void getTurn(Hand hand) throws AWTException{
		int x = 300; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 320;
		int gap = 55;
		
		Robot robot = new Robot();
		
		wait(x + (3*gap), y);
		hand.addCard(robot.getPixelColor(x + (3*gap),y).getGreen());
		
		int rank = Evaluator.evaluate(hand);
		System.out.println(rank);
	}
	
	/****************************************Get River**************************************/
	public static void getRiver(Hand hand) throws AWTException{
		int x = 300; //Get coordinates of Hole Cards from Romeo's Aspect Ratio Calculator
		int y = 320;
		int gap = 55;
		
		Robot robot = new Robot();
		
		wait(x + (4*gap), y);
		hand.addCard(robot.getPixelColor(x + (4*gap),y).getGreen());
		
		int rank = Evaluator.evaluate(hand);
		System.out.println(rank);
	}
	
	/****************************************Get Button*************************************/
	public static void getButton() throws AWTException{
		//Button positions need to dynamically change with Aspect Ratio and vary depending on number of seats
		//hard coded for 6 seater Matthew's desktop
		int[] x6 = {600, 350, 190, 350, 600, 650 };
		int[] y6 = {580, 580, 330, 215, 215, 330 };
		int pos = -1;
		
		Robot robot = new Robot();
		
		//check all positions for button
		for(int i = 0; i < x6.length ; i++){
			if(robot.getPixelColor(x6[i], y6[i]).getRed() == 255){
				pos = i;
				System.out.println("Position " + pos);
				break;
			}
		}
		
		if(pos == -1)
			System.out.println("Button not found");
	}
	
	/****************************************Get Bets***************************************/
	public static void getBets() throws AWTException, IOException {
		int[] x6 = {270, 210, 270, 530, 605};
		int[] y6 = {578, 428, 250, 250, 428};
		for(int i = 0; i < x6.length; i ++)
			System.out.println("Position " + i + ": " + OCR.partialCapture(x6[i], y6[i]));
	}
	
	/*******************************************Wait****************************************/
	public static void wait(int x, int y) throws AWTException{
		//Creates a loop that waits until the desired card shows up in position x, y
		Robot robot = new Robot();
		while(!(robot.getPixelColor(x,y).getBlue() == 255)){
			robot.delay(100);
		}
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