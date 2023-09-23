package com.example.learningproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Switch;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String toastMessage = context.getString(R.string.no_action_performed);
        if (intentAction != null) {
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = context.getString(R.string.power_connected);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = context.getString(R.string.power_disconnected);
                    break;
            }
        }
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}