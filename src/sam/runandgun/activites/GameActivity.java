package sam.runandgun.activites;

import java.util.Collection;

import sam.runandgun.gen.R;
import sam.runandgun.player.Player;
import sam.runandgun.views.Controls;
import sam.runandgun.views.GameBoard;
import sam.runandgun.weapons.Bullet;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	//Start of Class
	
	//Globals
	
	private Handler frame = new Handler();
	private static final int FPS = 20;
	//private static SoundPool soundEffects = new SoundPool(5,AudioManager.STREAM_MUSIC,0); //Unused right now
	

	
	private FrameUpdate frameRunnable;
	
	private ControlThread controlThread = new ControlThread();
	
	//End of globals
	
	//Views
	
	private GameBoard gameBoard;
	private Controls controls;
	private ProgressBar healthBar;
	private TextView score;
	
	//End of Views
	
	//Event Listeners for view
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		gameBoard = (GameBoard)this.findViewById(R.id.board);
		controls = (Controls)this.findViewById(R.id.controls);
		
		healthBar = controls.getHealth();
		score = controls.getScore();
		
		controls.getLeftButton().setOnTouchListener(new MoveButtonListener());
		controls.getRightButton().setOnTouchListener(new MoveButtonListener());
		controls.getFireButton().setOnTouchListener(new FireButtonListener()); //multitouch impossible because I can't split the motion events in the buttons.. Would be possible in 3.0 api using android:splitMotionEvents='true'
		
		frameRunnable = new FrameUpdate(gameBoard);
		frame.postDelayed(frameRunnable.initiateGraphics(), 1000); //start graphics thread
		frame.postDelayed(controlThread, 1000); //fire up the control thread
		
	}
	
	//End of view Listeners
	
	//Nested Classes
	
	//threads
	
	private class FrameUpdate implements Runnable{ //gameLoop
		
		private GameBoard board;
		
		public FrameUpdate(GameBoard b){
			board = b;
			healthBar.setMax(board.getPlayer().getHealth());
			healthBar.setProgress(gameBoard.getPlayer().getHealth());
			score.setText(""+board.getPlayer().getScore());
		}
		synchronized public void run(){
			//frame.removeCallbacks(frameRunnable);
			
			//update UI
			healthBar.setProgress(gameBoard.getPlayer().getHealth());
			score.setText(""+gameBoard.getPlayer().getScore());
			
			//update game
			board.getPlayer().isDead();
			board.updateBoard();
			board.invalidate();
			
			//continue thread
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
					Collection<Bullet> bullets = gameBoard.getPlayer().fireWeapon();
					if (bullets != null){
						gameBoard.getBullets().addAll(bullets);
					}
					return false;
				}
			return false;
		}
	}
	
	//End of Nested Class
	
	//End of Class
}
