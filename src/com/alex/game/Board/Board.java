package com.alex.game.Board;

import java.awt.Color;
import java.awt.Graphics;

import com.alex.game.Handler;
import com.alex.game.cell.Cell;

public class Board {
	
	public static final int ROWS = 32;
	public static final int COLUMNS = 32;
	public static boolean runSimulation = true;
	Cell[][] logicArray = new Cell[ROWS][COLUMNS];
	Cell[][] drawArray = new Cell[ROWS][COLUMNS];
	
	private Handler handler;
	
	public Board(Handler handler)	{
		this.handler = handler;
		createGrid();
	}
	
	private void createGrid()	{
		
		for(int x = 0; x < ROWS; x++)	{
			for(int y = 0; y < COLUMNS; y++)	{
				logicArray[x][y] = new Cell(x, y, logicArray);
				
			}
		}
	}
	
	public void tick()	{
		
		for(int x = 0; x < ROWS; x++)	{
			for(int y = 0; y < COLUMNS; y++)	{
				checkCells(logicArray[x][y]);
				logicArray[x][y].tick();
			}
		}
		
		for(int x = 0; x < ROWS; x++)	  {
			for(int y = 0; y < COLUMNS; y++)	{
				drawArray[x][y] = logicArray[x][y];
			}
		}
		
		for(int x = 0; x < ROWS; x++)	{
			for(int y = 0; y < COLUMNS; y++)	{
				if(runSimulation)	{
					if(drawArray[x][y].isAliveNextTick())	{
						drawArray[x][y].setAlive(true);
					} else {
						drawArray[x][y].setAlive(false);
					}
				}
			}
		}
		
		for(int x = 0; x < ROWS; x++)	{
			for(int y = 0; y < COLUMNS; y++)	{
				logicArray[x][y] = drawArray[x][y];
				
				
			}
		}
		
		
		
	}
	
	public void render(Graphics g)	{
		
		for(int x = 0; x < ROWS; x++)	{
			for(int y = 0; y < COLUMNS; y++)	{
				
				if(drawArray[x][y].getIsAlive())	{
					g.setColor(Color.CYAN);
					g.fillRect(x * Cell.DEFAULT_WIDTH, y * Cell.DEFAULT_HEIGHT, Cell.DEFAULT_WIDTH, Cell.DEFAULT_HEIGHT);
				} else	{
					g.setColor(Color.GRAY);
					g.fillRect(x * Cell.DEFAULT_WIDTH, y * Cell.DEFAULT_HEIGHT, Cell.DEFAULT_WIDTH, Cell.DEFAULT_HEIGHT);
				}
				g.setColor(Color.BLACK);
				g.drawRect(x * Cell.DEFAULT_WIDTH, y * Cell.DEFAULT_HEIGHT, Cell.DEFAULT_WIDTH, Cell.DEFAULT_HEIGHT);
			}
		}
	}

	private void checkCells(Cell cell)	{
		if(handler.getGame().getMouseManager().isLeftPressed())	{
			if(handler.getGame().getMouseManager().getClickX() >= cell.getX() * Cell.DEFAULT_WIDTH &&
			   handler.getGame().getMouseManager().getClickX() <= cell.getX() * Cell.DEFAULT_WIDTH + cell.getWidth() &&
			   handler.getGame().getMouseManager().getClickY() >= cell.getY() * Cell.DEFAULT_HEIGHT &&
			   handler.getGame().getMouseManager().getClickY() <= cell.getY() * Cell.DEFAULT_HEIGHT + cell.getHeight())	{
			   cell.setAlive(true);
			}
		}
		
		
		  
	}

}
