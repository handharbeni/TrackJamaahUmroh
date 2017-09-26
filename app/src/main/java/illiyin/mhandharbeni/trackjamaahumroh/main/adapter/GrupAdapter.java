package illiyin.mhandharbeni.trackjamaahumroh.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import illiyin.mhandharbeni.databasemodule.GrupModel;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by root on 9/21/17.
 */

public class GrupAdapter extends RealmBasedRecyclerViewAdapter<GrupModel, GrupAdapter.MyHolder> {
    public GrupAdapter(Context context, RealmResults<GrupModel> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate, false);
    }

    @Override
    public MyHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_grup, viewGroup, false);
        return new MyHolder((LinearLayout) v);
    }

    @Override
    public void onBindRealmViewHolder(MyHolder myHolder, int i) {
        final GrupModel gm = realmResults.get(i);
        myHolder.nama.setText(gm.getNama_grup());
        Glide.with(getContext()).load(gm.getNama_grup()).into(myHolder.image);
    }

    public class MyHolder extends RealmViewHolder {
        public CircleImageView image;
        public TextView nama;
        public MyHolder(LinearLayout container) {
            super(container);
            this.image = container.findViewById(R.id.image);
            this.nama = container.findViewById(R.id.nama);
        }
    }
}
