package illiyin.mhandharbeni.trackjamaahumroh.main.fragment.sub.grup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import illiyin.mhandharbeni.databasemodule.ContactModel;
import illiyin.mhandharbeni.realmlibrary.Crud;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import illiyin.mhandharbeni.trackjamaahumroh.main.adapter.ContactAdapter;
import io.realm.RealmResults;
import io.realm.Sort;

public class MapsGrup extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SlidingUpPanelLayout mLayout;
    private RealmRecyclerView list;

    ContactModel contactModel;
    Crud crud;

    ContactAdapter contactAdapter;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactModel = new ContactModel();
        crud = new Crud(getApplicationContext(), contactModel);

        setContentView(R.layout.layout_detail_grup);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fetch_element();
        fetch_adapter();
        fetch_slide();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng malang = new LatLng(-7.9743746, 112.607836);
        mMap.addMarker(new MarkerOptions().position(malang).title("Marker in Malang"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(malang));
    }
    private void fetch_element(){
        list = findViewById(R.id.list);
        mLayout = findViewById(R.id.sliding_layout);
    }
    private void fetch_slide(){
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }
    private void fetch_adapter(){
        RealmResults contactModels = crud.readSorted("id", Sort.DESCENDING);
        contactAdapter = new ContactAdapter(getApplicationContext(), contactModels, true);
        list.setAdapter(contactAdapter);
    }
    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
