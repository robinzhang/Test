package com.robin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HandlerActivity extends Activity {
	private Button startButton = null;
	private Button endButton = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_handler);
		startButton = (Button) findViewById(R.id.startButton);
		endButton = (Button) findViewById(R.id.endButton);
		startButton.setOnClickListener(new StartButtonListener());
		endButton.setOnClickListener(new EndButtonListener());
	}

	class StartButtonListener implements OnClickListener {
		public void onClick(View v) {
			handler.post(updateThread);
		}
	}

	class EndButtonListener implements OnClickListener {
		public void onClick(View v) {
			handler.removeCallbacks(updateThread);
		}
	}

	Handler handler = new Handler();
	Runnable updateThread = new Runnable() {
		public void run() {
			System.out.println("UpdateThread");
			handler.postDelayed(updateThread, 3000);
		}
	};
}