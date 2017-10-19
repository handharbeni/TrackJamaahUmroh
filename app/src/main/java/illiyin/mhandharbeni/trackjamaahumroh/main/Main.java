package illiyin.mhandharbeni.trackjamaahumroh.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.tablayouthelper.TabLayoutHelper;

import illiyin.mhandharbeni.trackjamaahumroh.R;
import illiyin.mhandharbeni.trackjamaahumroh.main.adapter.TabsPagerAdapter;
import illiyin.mhandharbeni.trackjamaahumroh.main.fragment.sub.contact.AddContact;
import illiyin.mhandharbeni.trackjamaahumroh.main.fragment.sub.grup.CreateGroup;

/**
 * Created by root on 9/13/17.
 */

public class Main extends Fragment{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayoutHelper mTabLayoutHelper;
    private FloatingActionButton fabgrup, fabcontact;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout._layout_main, container, false);
        init_view();
        fetchFab();
        return v;
    }
    private void fetchFab(){
        fabgrup = v.findViewById(R.id.fabgrup);
        fabcontact = v.findViewById(R.id.fabcontact);
        fabgrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), CreateGroup.class);
                getActivity().startActivity(i);
            }
        });
        fabcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), AddContact.class);
                getActivity().startActivity(i);
            }
        });
    }
    private void changeFab(int position){
        switch (position){
            case 0:
                fabgrup.show();
                fabcontact.hide();
                break;
            case 1:
                fabgrup.hide();
                fabcontact.show();
                break;
            case 3:
                fabgrup.hide();
                fabcontact.hide();
                break;
            default:
                fabgrup.hide();
                fabcontact.hide();
                break;
        }
    }
    public void init_view(){
        viewPager = v.findViewById(R.id.pager);
        tabLayout = v.findViewById(R.id.tablayout);
        viewPager.setAdapter(buildAdapter());
        viewPager.setOnPageChangeListener(onPageChangeListener);
        mTabLayoutHelper = new TabLayoutHelper(tabLayout, viewPager);
        mTabLayoutHelper.setAutoAdjustTabModeEnabled(true);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_group_work);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_chat_black);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings_black);

        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.greyCustom));
        tabLayout.setTabTextColors(getResources().getColor(R.color.textWhite), getResources().getColor(R.color.greyCustom));
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }
    private PagerAdapter buildAdapter(){
        return new TabsPagerAdapter(getChildFragmentManager());
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            changeFab(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeFab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
