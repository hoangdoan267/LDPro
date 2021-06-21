package tamhoang.ldpro4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class NavListAdapter extends ArrayAdapter<NavItem> {
    Context context;

    List<NavItem> listNavItems;

    int resLayout;

    public NavListAdapter(Context paramContext, int paramInt, List<NavItem> paramList) {
        super(paramContext, paramInt, paramList);
        this.context = paramContext;
        this.resLayout = paramInt;
        this.listNavItems = paramList;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        View view = View.inflate(this.context, this.resLayout, null);
        TextView textView1 = (TextView)view.findViewById(2131231323);
        TextView textView2 = (TextView)view.findViewById(2131231287);
        ImageView imageView = (ImageView)view.findViewById(2131230997);
        NavItem navItem = this.listNavItems.get(paramInt);
        textView1.setText(navItem.getTitle());
        textView2.setText(navItem.getSubtitle());
        imageView.setImageResource(navItem.getResIcons());
        return view;
    }
}
