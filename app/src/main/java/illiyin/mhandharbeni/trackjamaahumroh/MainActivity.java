package illiyin.mhandharbeni.trackjamaahumroh;


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
        session = new Session(this, this);

        setContentView(R.layout.activity_main);

        checkSession();
    }


    private void checkSession(){
//        0 = belum login
//        1 = sudah login / sudah ada token
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

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
