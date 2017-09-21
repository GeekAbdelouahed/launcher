package launcher.abdelouahed.com.launcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import launcher.abdelouahed.com.launcher.R;
import launcher.abdelouahed.com.launcher.events.ClickListener;
import launcher.abdelouahed.com.launcher.events.LongClickListener;
import launcher.abdelouahed.com.launcher.models.Pack;

/**
 * Created by geek on 20/09/17.
 */

public class MainAdapter extends BaseAdapter {

    private RelativeLayout homeLayout;
    private Pack[] packList;
    private Context context;
    private String namePack;

    public MainAdapter(Context context, Pack[] packList, RelativeLayout homeLayout) {
        this.context = context;
        this.packList = packList;
        this.homeLayout = homeLayout;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pack, parent, false);

        namePack = packList[position].getName();

        ImageView imageView = (ImageView) v.findViewById(R.id.ic_pack);
        TextView title = (TextView) v.findViewById(R.id.title_pack);

        imageView.setImageDrawable(packList[position].getIcon());
        title.setText(packList[position].getLabel());

        v.findViewById(R.id.layoutItem).setOnClickListener(new ClickListener(context, namePack));

        v.findViewById(R.id.layoutItem).setOnLongClickListener(new LongClickListener(context, homeLayout
                , namePack));

        return v;
    }

    @Override
    public int getCount() {
        return packList.length;
    }

    @Override
    public Pack getItem(int position) {
        return packList[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
