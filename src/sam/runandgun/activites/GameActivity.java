package sam.runandgun.activites;

import sam.runandgun.gen.R;
import sam.runandgun.views.Controls;
import sam.runandgun.views.GameBoard;
import sam.runandgun.weapons.Bullet;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class GameActivity extends Activity {
	
	//Start of Class
	
	//Globals
	
	private Handler frame = new Handler();
	private static final int FPS = 20;
	//private static SoundPool soundEffects = new SoundPool(5,AudioManager.STREAM_MUSIC,0); //Unused right now
	

	
	private FrameUpdate frameRunnable = new FrameUpdate();
	
	private ControlThread controlThread = new ControlThread();
	
	//End of globals
	
	//Views
	
	private GameBoard gameBoard;
	private Controls controls;
	
	//End of Views
	
	//Event Listeners for view
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		gameBoard = (GameBoard)this.findViewById(R.id.board);
		
		frame.postDelayed(frameRunnable.initiateGraphics(), 1000); //start graphics thread
		
		controls = (Controls)this.findViewById(R.id.controls);
		
		controls.getLeftButton().setOnTouchListener(new MoveButtonListener());
		controls.getRightButton().setOnTouchListener(new MoveButtonListener());
		controls.getFireButton().setOnTouchListener(new FireButtonListener());
		
		frame.postDelayed(controlThread, 1000); //fire up the control thread
		
	}
	
	//End of view Listeners
	
	//Nested Classes
	
	//threads
	
	private class FrameUpdate implements Runnable{ //update the graphics
		public FrameUpdate(){
			
		}
		synchronized public void run(){
			//frame.removeCallbacks(frameRunnable);
			gameBoard.updateBoard();
			gameBoard.invalidate();
			frame.postDelayed(frameRunnable, FPS);
		}
		synchronized public FrameUpdate initiateGraphics(){
			//inititaion stuff here (if I need it)
			return this;
		}
	}
	
	
	
	private class ControlThread implements Runnable{ //Thread to check if a button is pressed and held down
		public void run() {
			if (controls.getLeftButton().isPressed()){
				gameBoard.movePlayerLeft();
			}
			if (controls.getRightButton().isPressed()){
				gameBoard.movePlayerRight();
			}
				
			frame.postDelayed(this, FPS);
		}
		
	}
	
	//nested even listeners
	
	private class MoveButtonListener implements View.OnTouchListener{
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					//gameBoard.movePlayerRight();
					v.setPressed(true);
					break;
				case MotionEvent.ACTION_UP:
					v.setPressed(false);
					break;
				case MotionEvent.ACTION_CANCEL:
					v.setPressed(false);
					break;
			}
			return false;
		}
	}
	
	private class FireButtonListener implements View.OnTouchListener{
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					gameBoard.getBullets().addAll(gameBoard.getPlayer().fireWeapon());
					return false;
				}
			return false;
		}
	}
	
	//End of Nested Class
	
	//End of Class
}
