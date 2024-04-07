package com.alex.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.alex.game.input.KeyManager;
import com.alex.game.input.MouseManager;
import com.alex.game.states.GameState;
import com.alex.game.states.State;

public class Game implements Runnable {
	
	private String title;
	private int width, height;
	private Thread thread;
	private boolean running = false;
	BufferStrategy bs;
	
	private Display display;
	private Graphics g;
	
	private GameState gameState;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private Handler handler;
	
	public Game(String title, int width, int height)	{
		this.title = title;
		this.height = height;
		this.width = width;
		
	}
	
	private void init()	{
		handler = new Handler(this);
		display = new Display(title, width, height);
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
		
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addKeyListener(keyManager);
		
		display.getFrame().addMouseListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		gameState = new GameState(handler);
		
		State.setState(gameState);
		
	}
	
	private void tick()	{
		
		if(State.getState() == gameState)	{
			gameState.tick();
		}
		
	}
	
	private void render()	{
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)	{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
			
		g = bs.getDrawGraphics();
		
		//clear everything
		g.clearRect(0, 0, width, height);
		//draw here
		
		if(State.getState() == gameState)	{
			gameState.render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run()	{
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)	{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)	{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000)	{
				System.out.println("FPS : " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop(); 
	}
	
	public synchronized void start()	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()		{
		if(!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public MouseManager getMouseManager()	{
		return mouseManager;
	}
	
	
	

}
