package illiyin.mhandharbeni.databasemodule;

import android.content.Context;

import illiyin.mhandharbeni.networklibrary.CallHttp;
import illiyin.mhandharbeni.realmlibrary.Crud;
import illiyin.mhandharbeni.sessionlibrary.Session;
import illiyin.mhandharbeni.sessionlibrary.SessionListener;

/**
 * Created by root on 17/07/17.
 */

public class AdapterModel implements SessionListener{
    String server;

    private String endpoint_listgrup;
    private String endpoint_creategrup;
    private String endpoint_updategrup;
    private String endpoint_deletegrup;
    private String endpoint_sendmessagegrup;
    private String endpoint_senglocationgrup;
    private String endpoint_invitemembergrup;
    private String endpoint_konfimasimasukgrup;
    private String endpoint_listcontact;
    private String endpoint_getchatgrup;

    private DatabaseListener databaseListener;


    Context context;
    Crud crud;
    CallHttp callHttp;
    Session session;

    public AdapterModel(Context context) {
        this.context = context;
        callHttp = new CallHttp(context);
        session = new Session(context, this);
        initEndPoint();
    }

    private void initEndPoint(){
        server = context.getString(R.string.module_server);
        endpoint_listgrup = server+""+context.getString(R.string.endpoint_listgrup);
        endpoint_creategrup = server+""+context.getString(R.string.endpoint_creategrup);
        endpoint_updategrup = server+""+context.getString(R.string.endpoint_updategrup);
        endpoint_deletegrup = server+""+context.getString(R.string.endpoint_deletegrup);
        endpoint_sendmessagegrup = server+""+context.getString(R.string.endpoint_sendmessagegrup);
        endpoint_senglocationgrup = server+""+context.getString(R.string.endpoint_senglocationgrup);
        endpoint_invitemembergrup = server+""+context.getString(R.string.endpoint_invitemembergrup);
        endpoint_konfimasimasukgrup = server+""+context.getString(R.string.endpoint_konfimasimasukgrup);
        endpoint_listcontact = server+""+context.getString(R.string.endpoint_listcontact);
        endpoint_getchatgrup = server+""+context.getString(R.string.endpoint_getchatgrup);
    }
    @Override
    public void sessionChange() {

    }

    public void syncGrup(){

    }
    public void syncChat(){

    }
    public void syncContact(){

    }
}
