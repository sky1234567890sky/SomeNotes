package adapter;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import bean.ProjectTab;

/**
 * Created by long on 2019/2/19.
 */

public class VpListAdapter extends FragmentPagerAdapter{

    private ArrayList<android.support.v4.app.Fragment> fragments;
    private List<ProjectTab.DataBean> data;

    public VpListAdapter(FragmentManager fm, ArrayList<android.support.v4.app.Fragment> fragments, List<ProjectTab.DataBean> data) {
        super(fm);
        this.fragments = fragments;
        this.data = data;
    }

    public VpListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }
}
