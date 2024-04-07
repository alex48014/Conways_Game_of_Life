package com.alex.game.cell;

import java.awt.Graphics;

public class Cell {
	
	public static final int DEFAULT_WIDTH = 20;
	public static final int DEFAULT_HEIGHT = 20;
	private int x, y, width, height;
	private boolean alive = false;
	private boolean isAliveNextTick = false;
	private boolean nextTick = false;
	private int neighbours = 0;
	private Cell[][] cellArray;

	public Cell(int x, int y, Cell[][] cellArray)	{
		this.x = x;
		this.y = y;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.cellArray = cellArray;
	}
	
	public void tick()	{
		checkForNeighbours();
		checkCircumstances();
	}
	
	public void render(Graphics g)	{
		
	}
	
	private void checkForNeighbours()	{
		
		neighbours = 0;
		
		if(!(getLeftNeighbour() == null) && getLeftNeighbour().alive)	{
			neighbours ++;
		} else {
			//If the neighbours are 5, it can be subtracted (Did that because only so there can be min 3 neighbours
			if(neighbours > 5)
				neighbours --;
		}
		
		if(!(getUpperLeftNeighbour() == null) && getUpperLeftNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
		
		if(!(getLowerLeftNeighbour() == null) && getLowerLeftNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
		
		if(!(getRightNeighbour() == null) && getRightNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
		
		if(!(getUpperRightNeighbour() == null) && getUpperRightNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
		
		
		if(!(getLowerRightNeighbour() == null) && getLowerRightNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
		
		if(!(getUpperNeighbour() == null) && getUpperNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
		
		if(!(getLowerNeighbour() == null) && getLowerNeighbour().alive)	{
			neighbours ++;
		} else {
			if(neighbours > 5)
				neighbours --;
		}
	}
	
	private void checkCircumstances()	{
		if(alive && neighbours <= 1)	{
			isAliveNextTick = false;
		}
		
		if(alive && neighbours >= 2 && neighbours <= 3)	{
			isAliveNextTick = true;
		}
		
		if(alive && neighbours >= 4)	{
			isAliveNextTick = false;
		}
		
		if(!alive && neighbours == 3)	{
			isAliveNextTick = true;
		}
	}
	
	private Cell getLeftNeighbour()	{
		if(x > 0)
			return cellArray[x - 1][y];
		return null;
	}
	
	private Cell getRightNeighbour()	{
		if(x < 31)
			return cellArray[x + 1][y];
		return null;
	}
	
	private Cell getUpperNeighbour()	{
		if(y > 0)
			return cellArray[x][y - 1];
		return null;
	}
	
	private Cell getLowerNeighbour()	{
		if(y < 31)
			return cellArray[x][y + 1];
		return null;
	}
	
	private Cell getUpperLeftNeighbour()	{
		if(x > 0 && y > 0)
			return cellArray[x - 1][y - 1];
		return null;
	}
	
	private Cell getUpperRightNeighbour()	{
		if(x < 31 && y > 0)
			return cellArray[x + 1][y - 1];
		return null;
	}
	
	private Cell getLowerLeftNeighbour()	{
		if(x > 0 && y < 31)
			return cellArray[x - 1][y + 1];
		return null;
	}
	
	private Cell getLowerRightNeighbour()	{
		if(x < 31 && y < 31)
			return cellArray[x + 1][y + 1];
		return null;
	}
		
	
	//getters and setters
	
	
	public boolean isAliveNextTick()	{
		return isAliveNextTick;
	}
	
	public boolean isNextTick()	{
		return nextTick;
	}
	
	public void setNextTick(boolean nextTick)	{
		this.nextTick = nextTick;
	}
	
	public int getNeighbours()	{
		return neighbours;
	}
	
	public void setAlive(boolean isAlive)	{
		this.alive = isAlive;
	}
	
	public boolean getIsAlive()	{
		return alive;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	

}
