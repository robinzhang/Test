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
			// 生成广播处理
			smsBroadCastReceiver = new SmsBroadCastReceiver();
			// 实例化过滤器并设置要过滤的广播
			IntentFilter intentFilter = new IntentFilter(
					"android.provider.Telephony.SMS_RECEIVED");

			// 注册广播
			registerReceiver(smsBroadCastReceiver, intentFilter);
		}
	}

	public void onStop() {
		super.onStop();
		Log.v(TAG, "onStop");
		// 注册广播
		BroadcastActivity.this.unregisterReceiver(smsBroadCastReceiver);
		stop = true;
	}

	class EndButtonListener implements OnClickListener {
		public void onClick(View v) {
			if (!stop) {
				// 注册广播
				BroadcastActivity.this.unregisterReceiver(smsBroadCastReceiver);
				stop = true;
			}
		}
	}
}
