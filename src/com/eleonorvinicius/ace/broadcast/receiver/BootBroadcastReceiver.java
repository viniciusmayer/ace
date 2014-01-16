package com.eleonorvinicius.ace.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
		Log.i("BootBroadcastReceiver.onReceive", "Android is running!");

	}

}
