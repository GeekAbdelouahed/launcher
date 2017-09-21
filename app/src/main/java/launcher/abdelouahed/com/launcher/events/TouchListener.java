package launcher.abdelouahed.com.launcher.events;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by geek on 20/09/17.
 */

public class TouchListener implements View.OnTouchListener {

    private RelativeLayout homeLayout;
    private int _xDelta;
    private int _yDelta;

    public TouchListener(RelativeLayout homeLayout) {
        this.homeLayout = homeLayout;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                v.setLayoutParams(layoutParams);
                break;
        }

        homeLayout.invalidate();

        return false;
    }
}
