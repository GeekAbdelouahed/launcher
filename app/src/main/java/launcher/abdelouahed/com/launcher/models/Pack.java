package launcher.abdelouahed.com.launcher.models;

import android.graphics.drawable.Drawable;

/**
 * Created by geek on 20/09/17.
 */

public class Pack {
    private Drawable icon;
    private String name;
    private String label;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
