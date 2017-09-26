package illiyin.mhandharbeni.trackjamaahumroh.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import illiyin.mhandharbeni.trackjamaahumroh.main.fragment.ContactFragment;
import illiyin.mhandharbeni.trackjamaahumroh.main.fragment.GroupFragment;
import illiyin.mhandharbeni.trackjamaahumroh.main.fragment.SettingFragment;


/**
 * Created by root on 9/5/17.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new GroupFragment();
            case 1:
                return new ContactFragment();
            case 2:
                return new SettingFragment();
        }

        return new GroupFragment();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Group";
        }
        else if (position == 1)
        {
            title = "Contact";
        }
        else if (position == 2)
        {
            title = "Setting";
        }
        return title;
    }
}