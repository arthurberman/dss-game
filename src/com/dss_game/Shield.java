package com.dss_game;

import com.example.dss_game.R;

import java.util.Timer;
import java.util.TimerTask;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Shield implements Weapon {
	Bitmap image;
	int readiness =0;
	GameMenu menu;
	//TODO we need to give this shield stats like defence.
	private int defence = 10;
	private int bash_damage = 5;
	
	private int menuX;
	private int menuY;
	Engine engine;
	public Shield (Engine e) {
		engine = e;
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(engine.getResources(), R.drawable.shield), (int) (400 * Engine.scaleX),(int)(900 * Engine.scaleY), false);
		
        menu = new GameMenu();
		menu.addOption("Raise", new Raise());
		menu.addOption("Bash", new Bash());
	}
	@Override
	public Bitmap image() {
		return image;
	}

	@Override
	public int readiness() {
		return (readiness >= 0) ? readiness-- : readiness;
	}
	@Override
	public Bitmap menu(int x, int y, Paint p) {
		menuX = x;
		menuY = y;
		return menu.render(300, 400, p);
	}
	
	@Override
	public boolean tapped(int x, int y) {
		Action a = menu.click(x - menuX, y - menuY);
<<<<<<< HEAD
		if (a != null)
			readiness = a.execute();
		else 
			System.out.println("No action");
		return a != null;

=======
		
		System.out.println("Checking");
		if (readiness <= 0) {
			readiness = 100;
			if (a != null)
				a.execute();
			else 
				System.out.println("No action");

			return true;
		}
		System.out.println("Not ready yet");
		return false;
>>>>>>> a6690e193e249575ae352f07816a47e13f579c9d
	}
	
	private class Raise implements Action {

		@Override
		public int execute() {
			// TODO polish up and see if this how we want to do this
			engine.player.changeDef( defence);
			Timer def_T = new Timer();
			def_T.schedule(new TimerTask() {
				@Override
				public void run() {
					engine.player.changeDef((-1 * defence));
					return;
				}
			}, 1000*3);
			return 100;
			
		}}
	
	private class Bash implements Action {

		@Override
		public int execute() {
			// TODO select an enemy from monster array in engine
			engine.monster.take_dmg( (-1 * bash_damage));
			return 200;
		}}

}
