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
					"来自" + sms[i].getDisplayOriginatingAddress() + " 的消息是："
							+ sms[i].getDisplayMessageBody());
			Toast.makeText(
					context,
					"来自" + sms[i].getDisplayOriginatingAddress() + " 的消息是："
							+ sms[i].getDisplayMessageBody(),
					Toast.LENGTH_SHORT).show();
		}
		// 终止广播，在这里我们可以稍微处理，根据用户输入的号码可以实现短信防火墙。
		abortBroadcast();
	}

}
