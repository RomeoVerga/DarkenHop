/* HoleCard Class
 * 
 * Created On: November 13, 2014
 * Last Modified: November 13, 2014
 * Created By: Matthew Nemetchek
 * 
 * Purpose: The Hole Card Class stores and returns statistical data on Hole Cards
 * Data is dependent only on the number of players dealt hands.
 * 
 * Currently the hole class finds what position your holecards are in the starting_hands array
 * then uses that index to find your corresponding handstrength for 6 players in the handStrength method
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HoleCards {
	private static final String[] STARTING_HANDS;
	
	static{
		STARTING_HANDS = readStartingHands("C:\\Users\\Matthew\\git\\DarkenHop\\PokerBot\\StartingHands");
	}
	
	/****************************************Read Starting Hands From File*******************************************/
	public static String[] readStartingHands(String fileName){
		//Loads all 169 Possible Starting Hands from .txt file.
		String tempArray[] = new String[1000000];
		String textArray[];
		int length = 0;

		try{

			BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
			String input = br.readLine();

			for(int i = 0 ; input != null && i < tempArray.length ; i++) {
				tempArray[i] = input;
				input = br.readLine();
				length++;
			}

			br.close();

		}catch(IOException e) {
			System.out.println(e.getMessage());
		}

		textArray = new String[length];
		System.arraycopy(tempArray, 0, textArray, 0 , length);

		return textArray;
	}
	
	/************************************************Index*******************************************/
	public static int index(int[] array){
		//Could do binary search instead with a sorted STARTING HAND list if it saves significant time
		int index = -1;
		
		String s = quickForm(array);
		
		for(int i = 0; i < STARTING_HANDS.length ; i++){
			if(STARTING_HANDS[i].equals(s)){
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	/*********************************************Quick Form***************************************/
	public static String quickForm(int[] array){
		//Converts Hole Cards to take AKo form where the large card comes first and the last character is s or o for suited or offsuit
		String c1 = Card.get(array[0]).toString();
		String c2 = Card.get(array[1]).toString();
		String s = "";
		
		if(array[0]%13 > array[1]%13)
			s = c1.substring(0,1) + c2.substring(0,1);
		else
			s = c2.substring(0,1) + c1.substring(0,1);
		
		if(c1.substring(1).equals(c2.substring(1)))
			return s + "s";
		else
			return s + "o";
	}
	
	/*********************************************Hand Strength***************************************/
	public static double handStrength(int index){
		
		//Pre-Flop Hand Strengths for 6 Players
		double[] hs6 = {49.51, 43.32, 38.3, 34.05, 32.15,
				30.56, 30.44, 29.55, 29.28, 28.96, 28.28,
				28.27, 27.57, 27.32, 27.21, 27.11, 26.64,
				26.33, 26.28, 25.79, 25.75, 24.98, 24.88,
				24.73, 24.66, 24.51, 24.27, 24.26, 24.19,
				24.07, 23.9, 23.82, 23.8, 23.63, 23.5, 23.2,
				23.05, 23.03, 22.97, 22.39, 22.37, 22.35,
				22.24, 22.12, 22.01, 21.93, 21.75, 21.72,
				21.29, 21.07, 20.98, 20.75, 20.65, 20.59,
				20.46, 20.44, 20.41, 20.39, 20.38, 20.3,
				20.28, 20.25, 20.25, 20.19, 20.15, 19.76,
				19.72, 19.51, 19.43, 19.31, 19.22, 19.11,
				19.05, 19.04, 18.95, 18.92, 18.87, 18.82,
				18.69, 18.56, 18.5, 18.45, 18.43, 18.38,
				18.31, 18.25, 18.15, 18.14, 18.1, 18.03,
				17.82, 17.76, 17.73, 17.72, 17.62, 17.57,
				17.41, 17.19, 17.18, 17.17, 17.11, 16.76,
				16.72, 16.71, 16.7, 16.69, 16.66, 16.61,
				16.59, 16.59, 16.51, 16.27, 16.25, 16.17,
				16.05, 16.03, 15.9, 15.87, 15.77, 15.67,
				15.66, 15.62, 15.49, 15.35, 15.25, 15.22,
				15.19, 15.08, 15.06, 15, 14.94, 14.94, 14.71,
				14.68, 14.58, 14.53, 14.52, 14.37, 14.34,
				14.05, 14.02, 14, 13.97, 13.96, 13.87, 13.63,
				13.48, 13.42, 13.34, 13.16, 12.92, 12.78,
				12.71, 12.62, 12.29, 12.12, 12.01, 11.99,
				11.82, 11.58, 11.24, 11.23, 11.08, 10.86,
				10.67, 10.45, 10.32, 9.98, 9.87};
	
		return hs6[index];
	}
}
