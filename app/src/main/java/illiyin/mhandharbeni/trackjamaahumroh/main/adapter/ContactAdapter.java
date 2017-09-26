package illiyin.mhandharbeni.trackjamaahumroh.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import illiyin.mhandharbeni.databasemodule.ContactModel;
import illiyin.mhandharbeni.databasemodule.GrupModel;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by root on 9/22/17.
 */

public class ContactAdapter extends RealmBasedRecyclerViewAdapter<ContactModel, ContactAdapter.MyHolder> {

    public ContactAdapter(Context context, RealmResults<ContactModel> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate, false);
    }

    @Override
    public MyHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_contact, viewGroup, false);
        return new ContactAdapter.MyHolder(v);
    }

    @Override
    public void onBindRealmViewHolder(MyHolder myHolder, int i) {
        final ContactModel cm = realmResults.get(i);
        myHolder.nama.setText(cm.getNama());
        Glide.with(getContext()).load(cm.getImage()).into(myHolder.image);
    }

    public class MyHolder extends RealmViewHolder {
        public CircleImageView image;
        public TextView nama;

        public MyHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            nama = itemView.findViewById(R.id.nama);
        }
    }
}
