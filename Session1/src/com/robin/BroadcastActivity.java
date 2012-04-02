package com.robin;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastActivity extends Activity {
	private static String TAG = "BroadcastActivity";
	private Button startButton = null;
	private Button endButton = null;
	SmsBroadCastReceiver smsBroadCastReceiver;
	private boolean stop = false;

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
			// ���ɹ㲥����
			smsBroadCastReceiver = new SmsBroadCastReceiver();
			// ʵ����������������Ҫ���˵Ĺ㲥
			IntentFilter intentFilter = new IntentFilter(
					"android.provider.Telephony.SMS_RECEIVED");

			// ע��㲥
			registerReceiver(smsBroadCastReceiver, intentFilter);
		}
	}

	public void onStop() {
		super.onStop();
		Log.v(TAG, "onStop");
		// ע��㲥
		BroadcastActivity.this.unregisterReceiver(smsBroadCastReceiver);
		stop = true;
	}

	class EndButtonListener implements OnClickListener {
		public void onClick(View v) {
			if (!stop) {
				// ע��㲥
				BroadcastActivity.this.unregisterReceiver(smsBroadCastReceiver);
				stop = true;
			}
		}
	}
}
