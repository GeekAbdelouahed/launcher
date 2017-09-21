package launcher.abdelouahed.com.launcher.ui;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import launcher.abdelouahed.com.launcher.R;
import launcher.abdelouahed.com.launcher.adapters.MainAdapter;
import launcher.abdelouahed.com.launcher.events.HomeLongClick;
import launcher.abdelouahed.com.launcher.models.Pack;
import launcher.abdelouahed.com.launcher.tools.NotifyPackage;
import launcher.abdelouahed.com.launcher.tools.SortApps;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    RelativeLayout homeLayout;

    private Pack[] packs;
    private PackageManager packageManager;
    private MainAdapter mainAdapter;
    private AppWidgetManager mAppWidgetManager;
    private AppWidgetHost mAppWidgetHost;
    private final int REQUEST_PICK_APPWIDGET = 100;
    private final int REQUEST_CREATE_APPWIDGET = 100;
    private int numWidget = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppWidgetManager = AppWidgetManager.getInstance(this);
        mAppWidgetHost = new AppWidgetHost(this, R.id.APPWIDGET_HOST_ID);

        packageManager = getPackageManager();

        gridView = (GridView) findViewById(R.id.gridView);
        homeLayout = (RelativeLayout) findViewById(R.id.homeLayout);

        homeLayout.setOnLongClickListener(new HomeLongClick(this, mAppWidgetHost));

        setPacks();

        mainAdapter = new MainAdapter(this, packs, homeLayout);

        gridView.setAdapter(mainAdapter);

    }


    private void setPacks() {

        Intent intent = new Intent(Intent.ACTION_MAIN);

        // any app launcher
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> packList = packageManager.queryIntentActivities(intent, 0);

        // how many application found
        packs = new Pack[packList.size()];

        for (int i = 0; i < packs.length; i++) {
            packs[i] = new Pack();
            packs[i].setIcon(packList.get(i).loadIcon(packageManager));
            packs[i].setLabel(packList.get(i).activityInfo.loadLabel(packageManager).toString());
            packs[i].setName(packList.get(i).activityInfo.packageName);
        }

        SortApps.sort(packs);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_APPWIDGET) {
                configureWidget(data);
            } else if (requestCode == REQUEST_CREATE_APPWIDGET) {
                createWidget(data);
            }
        } else if (resultCode == RESULT_CANCELED && data != null) {
            int appWidgetId = data.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
            if (appWidgetId != -1) {
                mAppWidgetHost.deleteAppWidgetId(appWidgetId);
            }
        }
    }


    private void configureWidget(Intent data) {
        Bundle extras = data.getExtras();
        int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
        AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId);
        if (appWidgetInfo.configure != null) {
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
            intent.setComponent(appWidgetInfo.configure);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            startActivityForResult(intent, REQUEST_CREATE_APPWIDGET);
        } else {
            createWidget(data);
        }
    }

    public void createWidget(Intent data) {
        Bundle extras = data.getExtras();
        int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
        AppWidgetProviderInfo appWidgetInfo = mAppWidgetManager.getAppWidgetInfo(appWidgetId);
        AppWidgetHostView hostView = mAppWidgetHost.createView(this, appWidgetId, appWidgetInfo);
        hostView.setAppWidget(appWidgetId, appWidgetInfo);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(homeLayout.getWidth() / 3, homeLayout.getHeight() / 3);
        lp.leftMargin = numWidget * (homeLayout.getWidth() / 3);
        homeLayout.addView(hostView, lp);
        numWidget++;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void notifyPackge(NotifyPackage notifyPackage) {
        setPacks();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
