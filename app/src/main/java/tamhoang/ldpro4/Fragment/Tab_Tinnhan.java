package tamhoang.ldpro4.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import java.util.Vector;
import tamhoang.ldpro4.MyFragmentPagerAdapter;

public class Tab_Tinnhan extends Fragment implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
    int i = 0;

    private MyFragmentPagerAdapter myViewpagerAdapter;

    private TabHost tabHost;

    View v;

    private ViewPager viewPager;

    private void initializeTabHost(Bundle paramBundle) {
        TabHost tabHost = (TabHost)this.v.findViewById(16908306);
        this.tabHost = tabHost;
        tabHost.setup();
        TabHost.TabSpec tabSpec = this.tabHost.newTabSpec("Stin");
        tabSpec.setIndicator("Stin");
        tabSpec.setContent(new FakeContent((Context)getActivity()));
        this.tabHost.addTab(tabSpec);
        tabSpec = this.tabHost.newTabSpec("Tin nh);
                tabSpec.setIndicator("Tin chi ti);
                        tabSpec.setContent(new FakeContent((Context)getActivity()));
        this.tabHost.addTab(tabSpec);
        this.tabHost.setOnTabChangedListener(this);
    }

    private void initializeViewPager() {
        Vector<Frag_Suatin> vector = new Vector();
        vector.add(new Frag_Suatin());
        vector.add(new Frag_NoRP3());
        this.myViewpagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), vector);
        ViewPager viewPager = (ViewPager)this.v.findViewById(2131231559);
        this.viewPager = viewPager;
        viewPager.setAdapter((PagerAdapter)this.myViewpagerAdapter);
        this.viewPager.setOnPageChangeListener(this);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.v = paramLayoutInflater.inflate(2131427408, paramViewGroup, false);
        initializeTabHost(paramBundle);
        initializeViewPager();
        this.tabHost.setCurrentTab(0);
        return this.v;
    }

    public void onPageScrollStateChanged(int paramInt) {}

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}

    public void onPageSelected(int paramInt) {
        this.tabHost.setCurrentTab(paramInt);
    }

    public void onTabChanged(String paramString) {
        int i = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(i);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView)this.v.findViewById(2131230993);
        View view = this.tabHost.getCurrentTabView();
        horizontalScrollView.smoothScrollTo(view.getLeft() - (horizontalScrollView.getWidth() - view.getWidth()) / 2, 0);
    }

    class FakeContent implements TabHost.TabContentFactory {
        private final Context mContext;

        public FakeContent(Context param1Context) {
            this.mContext = param1Context;
        }

        public View createTabContent(String param1String) {
            View view = new View(this.mContext);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }
    }
}
