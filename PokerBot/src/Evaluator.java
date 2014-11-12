/* Evaluator Class
 * 
 * Created: November 12, 2014
 * Last Modified: November 12, 2014
 * Created By: Matthew Nemetchek
 * 
 * Purpose: The evaluator class determines the size of the hand and calls the correct class to
 * evaluate that hand. As there are 3 different evaluators.
 */

public class Evaluator {
	private static FiveCardEvaluator eval5;
	private static SixCardEvaluator eval6;
	private static SevenCardEvaluator eval7;
	
	static{
		eval5 = new FiveCardEvaluator();
		eval6 = new SixCardEvaluator();
		eval7 = new SevenCardEvaluator();
	}
	public static int evaluate(Hand h){
		if(h.noCards() == 5)
			return eval5.evaluate(h.toCards());
		else if(h.noCards() == 6)
			return eval6.evaluate(h.toCards());
		else if(h.noCards() == 7)
			return eval7.evaluate(h.toCards());
		else{
			System.out.println("Hand of incorrect size. Hand must have 5, 6 or 7 Cards: Number of Cards = " + h.noCards());
			return -1;
		}
	}
}
