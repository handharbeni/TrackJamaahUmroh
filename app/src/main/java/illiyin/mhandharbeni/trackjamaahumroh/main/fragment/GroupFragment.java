package illiyin.mhandharbeni.trackjamaahumroh.main.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;

import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import illiyin.mhandharbeni.databasemodule.GrupModel;
import illiyin.mhandharbeni.realmlibrary.Crud;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import illiyin.mhandharbeni.trackjamaahumroh.main.adapter.GrupAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by root on 9/14/17.
 */

public class GroupFragment extends Fragment implements RealmRecyclerView.OnRefreshListener {
    View v;
    GrupModel grupModel;
    Crud crud;

    List<GrupModel> grupList;
    GrupAdapter grupAdapter;
    RealmRecyclerView rvgrup;
    View destination;
    private SlideUp slideUp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        grupModel = new GrupModel();
        crud = new Crud(getActivity().getApplicationContext(), grupModel);

        v = inflater.inflate(R.layout.__layout_fragment_group, container, false);

        fetch_element();
        fetch_adapter();
        return v;
    }

    public void fetch_element(){
        rvgrup = v.findViewById(R.id.rvgrup);
        rvgrup.setOnRefreshListener(this);
    }
    private void fetch_adapter(){
        RealmResults results = crud.readSorted("id", Sort.DESCENDING);
        grupAdapter = new GrupAdapter(getActivity().getApplicationContext(), results, true);
        rvgrup.setAdapter(grupAdapter);
    }
    @Override
    public void onPause() {
        crud.closeRealm();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        grupModel = new GrupModel();
        crud = new Crud(getActivity().getApplicationContext(), grupModel);
    }

    @Override
    public void onDestroy() {
        crud.closeRealm();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {

    }
}
