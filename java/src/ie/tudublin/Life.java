package ie.tudublin;

import com.jogamp.newt.event.InputEvent;

import processing.core.PApplet;

public class Life extends PApplet
{
	LifeBoard board;
	boolean looping = true;

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		frameRate(5);
		board = new LifeBoard(100, this);
		board.randomise();
		frameRate(50);

	}

	public void draw()
	{	
		background(0);
		board.render();
		board.applyRules();

	}

	public void keyPressed() {
		if (key == '1') {
			board.randomise();
		}

		if (key == '2') {
			board.kill();
		}

		if (key == '3') {
			board.cross();
		}

		if (key == ' ') {
			if (looping) {
				noLoop();
				looping = false;
			} else {
				loop();
				looping  = true;
			}
		}
	}
}
