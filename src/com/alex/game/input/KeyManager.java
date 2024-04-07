package com.alex.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.alex.game.Board.Board;

public class KeyManager implements KeyListener	{
	
	boolean pressed = true;
	
	public KeyManager()	{
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE)	{
			if(!pressed)
				pressed = true;
				Board.runSimulation = false;
				
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE)	{
			if(pressed)
				pressed = false;
				Board.runSimulation = true;
				
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
