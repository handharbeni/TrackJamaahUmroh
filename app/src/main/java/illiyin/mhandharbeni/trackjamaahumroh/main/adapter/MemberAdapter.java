package illiyin.mhandharbeni.trackjamaahumroh.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import illiyin.mhandharbeni.databasemodule.ContactModel;
import illiyin.mhandharbeni.databasemodule.MemberModel;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by root on 10/4/17.
 */

public class MemberAdapter extends RealmBasedRecyclerViewAdapter<MemberModel, MemberAdapter.MyHolder> {

    public MemberAdapter(Context context, RealmResults<MemberModel> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate, false);
    }

    @Override
    public MemberAdapter.MyHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_contact, viewGroup, false);
        return new MemberAdapter.MyHolder(v);
    }

    @Override
    public void onBindRealmViewHolder(MemberAdapter.MyHolder myHolder, int i) {
        final MemberModel cm = realmResults.get(i);
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
