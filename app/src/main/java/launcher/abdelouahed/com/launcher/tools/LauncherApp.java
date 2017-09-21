package launcher.abdelouahed.com.launcher.tools;

import android.content.Context;
import android.content.Intent;

/**
 * Created by geek on 20/09/17.
 */

public class LauncherApp {

    public static void launch(Context context, String nameApp) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(nameApp);
        context.startActivity(intent);
    }

}
