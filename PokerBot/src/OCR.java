/* JAVA OCR
 * 
 * Created on: November 12, 2014
 * Last Modified: November 13, 2014
 * Created By: Matthew Nemetchek
 * 
 * OCR works, Simply put in the top left corner of text OCR will take a screen shot of that area
 * and covert the pixel data to text.
 * 
 * A method to calibrate the ocr to new text would be ideal.
 * A method which detects which colour pixels are text and background would be useful
 * An enum system would probably work better once we have all characters.
 * 
 * A way to eliminate the creation of new strings could perhaps speed up the OCR
 * 
 */

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class OCR {

	public static final String ZERO = "101410644614128"; //"912146444814126";
	public static final String ONE = "44416161622"; //"44151616222";
	public static final String TWO = "36777791072"; //"366667911972";
	public static final String THREE = "2546661013111"; //"254666814129";
	public static final String FOUR = "455554161622"; //"4555555161622";
	public static final String FIVE = "2111066681094"; //"21110106679108";
	public static final String SIX = "81311876711105"; //"71113876681194";
	public static final String SEVEN = "2357777764"; //"23577777753";
	public static final String EIGHT = "91314666913134"; //"71313106661013123";
	public static final String NINE = "6108667813116"; //"5101186661013115";
	public static final String DOT = "333";

	public static void capture(int x, int y) throws IOException, HeadlessException, AWTException{
		int height = 25;// height of characters being read
		int width = 100; // max width of characters

		double l = System.currentTimeMillis();

		
		Rectangle partial = new Rectangle(625, 483, width, height); //first 2 digits are top left corner of text 

		BufferedImage img = new Robot().createScreenCapture(partial);
		int w = img.getWidth();
		int h = img.getHeight();

		//Gets the rgbArray for OCR
		int[] rgbArray = new int[w*h];
		double num = stringCode(img.getRGB(0, 0, w, h, rgbArray, 0, w), w ,h);
		System.out.println(num);

		//Saves an identical images to ensure that the image we're getting is the right one
		BufferedImage bfImage= new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		bfImage.setRGB(0, 0, w, h, rgbArray, 0, w);
		ImageIO.write(bfImage, "png", new File("C:\\Users\\Matthew\\Desktop\\PokerTest.png"));

		l = (System.currentTimeMillis() - l)/1000;
		System.out.println(l + " seconds");
	}
	
	/****************************************Screen Capture -- Takes a Screen shot***************************************/
	public static BufferedImage screenCapture() throws AWTException, IOException{
		
		Rectangle fullScreen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		return new Robot().createScreenCapture(fullScreen);
		
	}
	
	/****************************************Partial Capture -- Takes a subimage of Screen Shot***************************************/
	public static double partialCapture(int x, int y) throws AWTException, IOException{
		
		int textW = 100;
		int textH = 25;
		int[] rgb = new int[textW*textH];
		BufferedImage img = screenCapture();
		img = img.getSubimage(x, y, textW, textH);
		rgb = img.getRGB(0, 0, textW, textH, rgb, 0, textW);
		//ImageIO.write(img, "png", new File("C:\\Users\\Matthew\\Desktop\\PokerT.png"));
		
		return stringCode(rgb, textW, textH);
	}
	
	/****************************************Get String Code***************************************/
	public static double stringCode(int[] rgb, int w, int h){

		int whiteCount = 0;
		int numCount = 0;
		String s = "";
		String[] code = new String[10];

		for(int j = 0; j < w ; j++){
			for(int i = j; i <= ((w*h) - w +j) ; i+=w){
				int[] argb = pixelInfo(rgb[i]);

				if(argb[1] > 100)
					whiteCount++;
			}

			if(whiteCount == 0 && s.length() >= 3){//Adds new string code to code array
				code[numCount] = s;
				numCount++;
				s = "";
			}
			
			else if(whiteCount != 0){
				s = s + whiteCount;
				whiteCount = 0;
			}
		}
		return convertStringCode(code);
	}
	
	/****************************************Pixel Info-- Gets ARGB info***************************************/
	public static int[] pixelInfo(int pixel){
		
		int[] argb = new int[4];
		
		argb[0] = (pixel >> 24) & 0xff; //alpha
		argb[1] = (pixel >> 16) & 0xff; //red
		argb[2] = (pixel >> 8) & 0xff; //green
		argb[3] = (pixel) & 0xff; //blue
		
		return argb;
		
	}

	/****************************************Convert String Code***************************************/
	public static double convertStringCode(String[] code){
		String s = "0";
		//System.out.println(Arrays.toString(code));

		for(int i = 0; i < code.length && code[i] != null; i ++) {

			if(code[i].equals(ZERO))
				s = s + "0";
			else if(code[i].equals(ONE))
				s = s + "1";
			else if(code[i].equals(TWO))
				s = s + "2";
			else if(code[i].equals(THREE))
				s = s + "3";
			else if(code[i].equals(FOUR))
				s = s + "4";
			else if(code[i].equals(FIVE))
				s = s + "5";
			else if(code[i].equals(SIX))
				s = s + "6";
			else if(code[i].equals(SEVEN))
				s = s + "7";
			else if(code[i].equals(EIGHT))
				s = s + "8";
			else if(code[i].equals(NINE))
				s = s + "9";
			else if(code[i].equals(DOT))
				s = s + ".";
		}

		return Double.parseDouble(s);
	}
}
