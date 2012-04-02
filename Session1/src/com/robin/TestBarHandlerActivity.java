package com.robin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class TestBarHandlerActivity extends Activity {

	ProgressBar progressBar = null;
	Button startButton = null;

	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.layout_bar);
		progressBar = (ProgressBar) findViewById(R.id.bar);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new buttonOnClickListener());
	}

	class buttonOnClickListener implements OnClickListener {
		public void onClick(View v) {
			// 点击一下进度条就可见
			progressBar.setVisibility(View.VISIBLE);
			updateBarHandler.post(updateThread);
		}
	}

	Handler updateBarHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressBar.setProgress(msg.arg1);
			if (msg.arg1 < 100) {
				// 將要執行的線程放入隊列中
				updateBarHandler.post(updateThread);
			}
		}
	};

	Runnable updateThread = new Runnable() {
		int counter = 0;

		public void run() {
			// TODO Auto-generated method stub
			Log.d("in updateThread run()", "在updateThread run方法中");
			counter += 10;
			// 从系统处得到一个消息
			// Returns a new Message from the global message pool. More
			// efficient than creating and allocating new instances. The
			// retrieved message has its handler
			// set to this instance (Message.target == this). If you don't want
			// that facility, just call Message.obtain() instead.
			Message msg = updateBarHandler.obtainMessage();
			// 将Message对象的arg1参数的值设置为i
			// arg1 and arg2 are lower-cost alternatives to using setData() if
			// you only need to store a few integer values.
			msg.arg1 = counter; // 用arg1、arg2这两个成员变量传递消息，优点是系统性能消耗较少
			try {
				Thread.sleep(1000); // 让当前线程休眠1000毫秒
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			// 将Message对象加入到消息队列当中
			updateBarHandler.sendMessage(msg);
			if (100 <= counter) {
				updateBarHandler.removeCallbacks(updateThread);
			}
		}
	};
}