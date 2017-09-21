package launcher.abdelouahed.com.launcher.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

public class ReceiverApp extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new NotifyPackage());
    }
}
