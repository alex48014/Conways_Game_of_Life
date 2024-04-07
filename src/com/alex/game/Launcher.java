package com.alex.game;

public class Launcher {
	
	public static void main(String[] args)	{
		Game game = new Game("Conway's Game of Life", 640, 640);
		game.start();
		
	}

}
