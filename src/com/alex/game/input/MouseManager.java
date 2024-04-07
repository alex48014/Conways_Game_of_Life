package com.alex.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {

	private int clickX;
	private int clickY;
	
	private boolean leftPressed, rightPressed;
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)	{
			leftPressed = true;
			clickX = e.getX();
			clickY = e.getY();
		}
		
		if(e.getButton() == MouseEvent.BUTTON3)	{
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)	{
			leftPressed = false;
		}
		
		if(e.getButton() == MouseEvent.BUTTON3)	{
			rightPressed = false;
		}
	}
	
	public boolean isLeftPressed()	{
		return leftPressed;
	}
	
	public boolean isRightPressed()	{
		return rightPressed;
	}
	
	public int getClickX()	{
		return clickX;
	}
	
	public int getClickY()	{
		return clickY;
	}

}
