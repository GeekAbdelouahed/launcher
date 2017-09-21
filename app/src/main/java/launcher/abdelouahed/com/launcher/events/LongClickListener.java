package launcher.abdelouahed.com.launcher.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import launcher.abdelouahed.com.launcher.R;

/**
 * Created by geek on 20/09/17.
 */

public class LongClickListener implements View.OnLongClickListener {

    private Context context;
    private RelativeLayout homeLayout;
    private String nameApp;

    public LongClickListener(Context context, RelativeLayout homeLayout, String nameApp) {
        this.context = context;
        this.homeLayout = homeLayout;
        this.nameApp = nameApp;
    }

    @Override
    public boolean onLongClick(View v) {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(v.getWidth(), v.getHeight());
        layoutParams.leftMargin = (int) v.getX();
        layoutParams.topMargin = (int) v.getY();

        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.item_pack, null);

        ((ImageView) layout.findViewById(R.id.ic_pack)).setImageDrawable(
                ((ImageView) v.findViewById(R.id.ic_pack)).getDrawable()
        );

        ((TextView) layout.findViewById(R.id.title_pack)).setText(
                ((TextView) v.findViewById(R.id.title_pack)).getText().toString()
        );

        layout.setOnClickListener(new ClickListener(context, nameApp));

        layout.setOnTouchListener(new TouchListener(homeLayout));

        homeLayout.addView(layout, layoutParams);

        return false;
    }
}
