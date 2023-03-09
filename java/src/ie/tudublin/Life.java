package ie.tudublin;

import com.jogamp.newt.event.InputEvent;

import processing.core.PApplet;

public class Life extends PApplet
{
	LifeBoard board;
	boolean looping = true;
	int mouseEffects = 1;

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		frameRate(50);
		board = new LifeBoard(40, this);
		board.randomise();
		board.displayFunctions();
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
			looping = board.pausePlay(looping);
		}

		if (key == 'e') {
			mouseEffects++;
		}
	}

	public void mouseMoved() {
		if (mouseEffects % 3 == 0) {
			board.dragDraw(mouseX, mouseY);
		}
	}

	public void mouseClicked() {
		if (mouseEffects % 3 == 1) {
			board.gliderDraw(mouseX, mouseY);
		}

		if (mouseEffects % 3 == 2) {
			board.gosperGunDraw(mouseX, mouseY);
		}
	}
}
