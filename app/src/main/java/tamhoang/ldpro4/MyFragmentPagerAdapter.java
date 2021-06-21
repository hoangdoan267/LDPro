package tamhoang.ldpro4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager paramFragmentManager, List<Fragment> paramList) {
        super(paramFragmentManager);
        this.fragments = paramList;
    }

    public int getCount() {
        return this.fragments.size();
    }

    public Fragment getItem(int paramInt) {
        return this.fragments.get(paramInt);
    }
}
