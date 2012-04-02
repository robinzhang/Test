package com.robin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Object[] object = (Object[]) bundle.get("pdus");
		SmsMessage sms[] = new SmsMessage[object.length];
		for (int i = 0; i < object.length; i++) {
			sms[0] = SmsMessage.createFromPdu((byte[]) object[i]);
			Log.i("SmsBroadCastReceiver",
					"����" + sms[i].getDisplayOriginatingAddress() + " ����Ϣ�ǣ�"
							+ sms[i].getDisplayMessageBody());
			Toast.makeText(
					context,
					"����" + sms[i].getDisplayOriginatingAddress() + " ����Ϣ�ǣ�"
							+ sms[i].getDisplayMessageBody(),
					Toast.LENGTH_SHORT).show();
		}
		// ��ֹ�㲥�����������ǿ�����΢���������û�����ĺ������ʵ�ֶ��ŷ���ǽ��
		abortBroadcast();
	}

}
