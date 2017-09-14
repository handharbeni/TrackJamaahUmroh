package illiyin.mhandharbeni.trackjamaahumroh.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import illiyin.mhandharbeni.trackjamaahumroh.R;

/**
 * Created by root on 9/13/17.
 */

public class Main extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout._layout_main, container, false);
        return v;
    }
}
