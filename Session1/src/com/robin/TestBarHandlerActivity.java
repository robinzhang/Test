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
			// ���һ�½������Ϳɼ�
			progressBar.setVisibility(View.VISIBLE);
			updateBarHandler.post(updateThread);
		}
	}

	Handler updateBarHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressBar.setProgress(msg.arg1);
			if (msg.arg1 < 100) {
				// ��Ҫ���еľ��̷��������
				updateBarHandler.post(updateThread);
			}
		}
	};

	Runnable updateThread = new Runnable() {
		int counter = 0;

		public void run() {
			// TODO Auto-generated method stub
			Log.d("in updateThread run()", "��updateThread run������");
			counter += 10;
			// ��ϵͳ���õ�һ����Ϣ
			// Returns a new Message from the global message pool. More
			// efficient than creating and allocating new instances. The
			// retrieved message has its handler
			// set to this instance (Message.target == this). If you don't want
			// that facility, just call Message.obtain() instead.
			Message msg = updateBarHandler.obtainMessage();
			// ��Message�����arg1������ֵ����Ϊi
			// arg1 and arg2 are lower-cost alternatives to using setData() if
			// you only need to store a few integer values.
			msg.arg1 = counter; // ��arg1��arg2��������Ա����������Ϣ���ŵ���ϵͳ�������Ľ���
			try {
				Thread.sleep(1000); // �õ�ǰ�߳�����1000����
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			// ��Message������뵽��Ϣ���е���
			updateBarHandler.sendMessage(msg);
			if (100 <= counter) {
				updateBarHandler.removeCallbacks(updateThread);
			}
		}
	};
}