package illiyin.mhandharbeni.trackjamaahumroh.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import illiyin.mhandharbeni.sessionlibrary.Session;
import illiyin.mhandharbeni.trackjamaahumroh.R;

/**
 * Created by root on 9/13/17.
 */

public class Login extends Fragment {
    View v;
    private Session session;
    private TextView dosignup;
    private EditText username, password;
    private Button signin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout._layout_login, container, false);
        fetch_element();
        fetch_click();
        return v;
    }

    private void fetch_element(){
        username = v.findViewById(R.id.username);
        password = v.findViewById(R.id.password);
        signin = v.findViewById(R.id.signin);
        dosignup = v.findViewById(R.id.dosignup);
    }

    private void fetch_click(){
        dosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToRegister();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_login();
            }
        });
    }

    private void changeToRegister(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe, new Register());
        ft.commit();
    }

    private void do_login(){

    }
}
