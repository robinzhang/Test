package com.robin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SubActivity extends Activity implements OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub);
		Bundle bundle = getIntent().getExtras(); 
		if(bundle.containsKey("Msg")){
			TextView tv = (TextView)findViewById(R.id.textView1);
			tv.setText(bundle.getString("Msg"));
		}
		findViewById(R.id.button1).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		intent.putExtra("ReturnVal", "Return from Sub Activity");
		setResult(RESULT_OK,intent);
		finish();
	}
	

}
