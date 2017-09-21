package launcher.abdelouahed.com.launcher.events;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by geek on 21/09/17.
 */

public class HomeLongClick implements View.OnLongClickListener {

    private Activity context;
    private AppWidgetHost mAppWidgetHost;

    public HomeLongClick(Activity context, AppWidgetHost mAppWidgetHost) {
        this.context = context;
        this.mAppWidgetHost = mAppWidgetHost;
    }

    @Override
    public boolean onLongClick(View v) {
        selectWidget();
        return false;
    }

    private void selectWidget() {
        int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
        Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
        pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        addEmptyData(pickIntent);
        context.startActivityForResult(pickIntent, 100);
    }

    private void addEmptyData(Intent pickIntent) {
        ArrayList customInfo = new ArrayList();
        pickIntent.putParcelableArrayListExtra(AppWidgetManager.EXTRA_CUSTOM_INFO, customInfo);
        ArrayList customExtras = new ArrayList();
        pickIntent.putParcelableArrayListExtra(AppWidgetManager.EXTRA_CUSTOM_EXTRAS, customExtras);
    }

}
