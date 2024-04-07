package com.alex.game.states;

import java.awt.Graphics;

import com.alex.game.Handler;
import com.alex.game.Board.Board;

public class GameState extends State {

	Board board;
	Handler handler;
	
	public GameState(Handler handler) {
		this.handler = handler;
		init();
		
	}
	
	private void init()	{
		board = new Board(handler);
	}
	
	@Override
	public void tick() {
		board.tick();
		
	}

	@Override
	public void render(Graphics g) {
		board.render(g);
		
	}

}
