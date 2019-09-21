////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Jungle Park
// Files:           JunglePark.java
// Course:          CS300, Fall 2018
//
// Author:          Stephen Fan
// Email:           sfan54@wisc.edu
// Lecturer's Name: Alexi Brooks
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;

public class JunglePark {
	private static PApplet processing; // PApplet object that represents the graphic 
    	// interface of the JunglePark application
	private static PImage backgroundImage; // PImage object that represents the 
        // background image
	private static Tiger[] tigers; // array storing the current tigers present 
		// in the Jungle Park

	private static Random randGen; // Generator of random numbers
	
	public static void main (String[] args) {
		//main method to start the application
		Utility.startApplication();
	}
	
	/**
 	* Defines the initial environment properties of the application
 	* @param processingObj represents a reference to the graphical
 	*  interface of the application
 	*/
	public static void setup(PApplet processingObj) {
		processing = processingObj; // initialize the processing field to the one passed into 
	    	// the input argument parameter
		
		// Set the color used for the background of the Processing window
		processing.background(245, 255, 250); // Mint cream color
		
		// initialize and load the image of the background
		backgroundImage = processing.loadImage("images/background.png");
		
		// Draw the background image at the center of the screen
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		// width [resp. height]: System variable of the processing library that stores the width 
		// [resp. height] of the display window.
		
		tigers = new Tiger[8]; //array of Tiger objects of size 8
		randGen = new Random(); // create a Random object and store its reference in randGen
	}
	
	/**
	 * will continuously draw the application display window and updates its content with 
	 * respect to any change or any event that affects its appearance.
	 */
	public static void update() {
		// Set the color used for the background of the Processing window
		processing.background(245, 255, 250); // Mint cream color
		
		// initialize and load the image of the background
		backgroundImage = processing.loadImage("images/background.png");
		
		// Draw the background image at the center of the screen
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		// width [resp. height]: System variable of the processing library that stores the width 
		// [resp. height] of the display window.
		
		for (int i = 0; i < tigers.length; i++) {
			if (tigers[i] != null) {
				tigers[i].draw();
			}
		}
	}
	
	/**
	 * checks if the mouse is over a Tiger object
	 * @param tiger is a Tiger object from the tigers array
	 * @return returns true if the mouse is over a Tiger object and false if it's not
	 */
	public static boolean isMouseOver(Tiger tiger) {
		//gets the dimensions of the tiger image
		float width = tiger.getImage().width;
		float height = tiger.getImage().height;
		float centerX = tiger.getPositionX();
		float centerY = tiger.getPositionY();
		
		//calculate the coordinates of the image's corners
		float topRightCornerX = centerX + width/2;
		float topRightCornerY = centerY + height/2;
		float topLeftCornerX = centerX - width/2;
		float botRightCornerY = centerY - height/2;
		
		boolean insideImage = false;
		
		//checks if the mouse coordinates are in the area of the image
		//sets insideImage to true if they are
		if (processing.mouseX < topRightCornerX && processing.mouseX > topLeftCornerX && 
				processing.mouseY < topRightCornerY && processing.mouseY > botRightCornerY) {
			insideImage = true;
		}
		
		return insideImage;
				
	}
	
	public static void mouseDown() {
		//allows user to drag a tiger image when the mouse is pressed down
		for (int i = 0; i < tigers.length; i++) {
			if (tigers[i] != null) {
				if (isMouseOver(tigers[i]) == true) {
					tigers[i].setDragging(true);
					i = 9;
				}
			}
		}
	}
	
	public static void mouseUp() {
		//allows the user to let go of a tiger image when the mouse is no longer pressed down
		for (int i = 0; i < tigers.length; i++) {
			if (tigers[i] != null) {
				tigers[i].setDragging(false);
			}
		}
	}
	
	/**
	 *runs when the user presses any key
	 *the only 2 relevant keys that produce results are T and R
	 *T creates a new tiger and R removes the tiger that the mouse is currently on
	 */
	public static void keyPressed() {
		//creates a new tiger at random coordinates if the user presses T
		if (processing.key == 't' || processing.key == 'T') {
			for (int i = 0; i < tigers.length; i++) {
				if (tigers[i] == null) {
					tigers[i] = new Tiger(processing, 
							(float)randGen.nextInt(processing.width),
							(float)randGen.nextInt(processing.height));
					i = 9;
				}
			}
		}
		
		//removes the tiger that the mouse is hovering over
		else if (processing.key == 'r' || processing.key == 'R') {
			for (int i = 0; i < tigers.length; i++) {
				if (tigers[i] != null) {
					if (isMouseOver(tigers[i]) == true) {
						tigers[i] = null;
						i = 9;
					}
				}
			}
		}
	}
}
