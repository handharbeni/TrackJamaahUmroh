package illiyin.mhandharbeni.trackjamaahumroh.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import illiyin.mhandharbeni.trackjamaahumroh.R;

/**
 * Created by root on 9/14/17.
 */

public class SettingFragment extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.__layout_fragment_setting, container, false);
        return v;
    }
}
