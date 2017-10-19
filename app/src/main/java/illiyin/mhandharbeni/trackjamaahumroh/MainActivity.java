package illiyin.mhandharbeni.trackjamaahumroh;


import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import illiyin.mhandharbeni.sessionlibrary.Session;
import illiyin.mhandharbeni.sessionlibrary.SessionListener;
import illiyin.mhandharbeni.trackjamaahumroh.account.Login;
import illiyin.mhandharbeni.trackjamaahumroh.main.Main;

public class MainActivity extends AppCompatActivity implements SessionListener {
    private Session session;
    Integer loginState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = new String[11];
        permissions[0] = Manifest.permission.CAMERA;
        permissions[1] = Manifest.permission.INTERNET;
        permissions[2] = Manifest.permission.WAKE_LOCK;
        permissions[3] = Manifest.permission.LOCATION_HARDWARE;
        permissions[4] = Manifest.permission.ACCESS_COARSE_LOCATION;
        permissions[5] = Manifest.permission.ACCESS_FINE_LOCATION;
        permissions[6] = Manifest.permission.READ_PHONE_STATE;
        permissions[7] = Manifest.permission.ACCESS_NETWORK_STATE;
        permissions[8] = Manifest.permission.ACCESS_WIFI_STATE;
        permissions[9] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        permissions[10] = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    5
            );
        }
        session = new Session(this, this);

        setContentView(R.layout.activity_main);

        checkSession();
    }


    private void checkSession(){
//        0 = belum login
//        1 = sudah login / sudah ada token
//        loginState = session.getCustomParams("LoginState", 0);
        loginState = session.getCustomParams("LoginState", 0);
        setPage(loginState);

    }
    private void setPage(int page){
        Fragment fragment = null;
        switch (page){
            case 0 :
//                login
                fragment = new Login();
                break;
            case 1 :
                fragment = new Main();
//                sudah login
                break;
        }
        if (fragment != null){
            changeFragment(fragment);
        }
    }
    private void changeFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe, fragment);
        ft.commit();
    }
    @Override
    public void sessionChange() {
        checkSession();
    }
}
