package sam.runandgun.activites;

import sam.runandgun.gen.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Button playButton = (Button) this.findViewById(R.id.startButton);
		Button exitButton = (Button) this.findViewById(R.id.quitButton);
		
		playButton.setOnClickListener(new StartButton());
		exitButton.setOnClickListener(new ExitButton());
		
	}
	
	//logic
	
	public void quitMenu(){
		finish();
	}
	
	public void playGame(){
		Intent gameIntent = new Intent(getApplicationContext(), GameActivity.class);
		startActivity(gameIntent);
	}
	
	//nested button listners
	
	private class StartButton implements View.OnClickListener{
		public void onClick(View v) {
			playGame();
		}
	}
	
	private class ExitButton implements View.OnClickListener{
		public void onClick(View v) {
			quitMenu();
		}
	}
	

	
	
}
