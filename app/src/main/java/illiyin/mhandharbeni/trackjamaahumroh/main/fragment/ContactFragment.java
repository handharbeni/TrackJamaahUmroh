package illiyin.mhandharbeni.trackjamaahumroh.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import illiyin.mhandharbeni.databasemodule.ContactModel;
import illiyin.mhandharbeni.databasemodule.GrupModel;
import illiyin.mhandharbeni.realmlibrary.Crud;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import illiyin.mhandharbeni.trackjamaahumroh.main.adapter.ContactAdapter;
import illiyin.mhandharbeni.trackjamaahumroh.main.adapter.GrupAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by root on 9/14/17.
 */

public class ContactFragment extends Fragment {
    View v;
    ContactModel contactModel;
    Crud crud;

    List<ContactModel> contactList;
    ContactAdapter contactAdapter;
    RealmRecyclerView rvcontact;
    View destination;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contactModel = new ContactModel();
        crud = new Crud(getActivity().getApplicationContext(), contactModel);


        v = inflater.inflate(R.layout.__layout_fragment_contact, container, false);

        fetch_element();
        fetch_adapter();
        return v;
    }

    public void fetch_element(){
        rvcontact = v.findViewById(R.id.rvcontact);
    }

    private void fetch_adapter(){
        Realm realm = Realm.getInstance(Realm.getDefaultConfiguration());
        RealmResults<ContactModel> contactModels = realm.where(ContactModel.class).findAllSorted("id", Sort.DESCENDING);
        contactAdapter = new ContactAdapter(getActivity().getApplicationContext(), contactModels, true);
        rvcontact.setAdapter(contactAdapter);
    }

    @Override
    public void onPause() {
        crud.closeRealm();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        contactModel = new ContactModel();
        crud = new Crud(getActivity().getApplicationContext(), contactModel);
    }

    @Override
    public void onDestroy() {
        crud.closeRealm();
        super.onDestroy();
    }
}
