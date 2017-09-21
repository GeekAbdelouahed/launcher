package launcher.abdelouahed.com.launcher.events;

import android.content.Context;
import android.view.View;

import launcher.abdelouahed.com.launcher.tools.LauncherApp;

/**
 * Created by geek on 20/09/17.
 */

public class ClickListener implements View.OnClickListener {

    private Context context;
    private String nameApp;

    public ClickListener(Context context, String nameApp) {
        this.context = context;
        this.nameApp = nameApp;
    }

    @Override
    public void onClick(View v) {
        LauncherApp.launch(context, nameApp);
    }
}
