package illiyin.mhandharbeni.trackjamaahumroh.account;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import illiyin.mhandharbeni.httpcalllibrary.AndroidCall;
import illiyin.mhandharbeni.sessionlibrary.Session;
import illiyin.mhandharbeni.sessionlibrary.SessionListener;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import illiyin.mhandharbeni.utilslibrary.SnackBar;
import illiyin.mhandharbeni.utilslibrary.SnackBarListener;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by root on 9/13/17.
 */

public class Login extends Fragment implements SessionListener, SnackBarListener {
    View v;
    private Session session;
    private TextView dosignup;
    private TextInputEditText username, password;
    private Button signin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        session = new Session(getActivity().getApplicationContext(), this);
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
                doLogin();
            }
        });
    }

    private void changeToRegister(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe, new Register());
        ft.commit();
    }
    private Boolean validate(){
        if (username.getText().toString().isEmpty()){
            setError(username, "Username Tidak Boleh Kosong");
            return false;
        }else if (password.getText().toString().isEmpty()){
            setError(password, "Password Tidak Boleh Kosong");
            return false;
        }
        return true;
    }
    private void setError(TextInputEditText txt, String message){
        showSnackBar(v, message);
        txt.requestFocus();
    }
    private void showSnackBar(View v, String message){
        new SnackBar(getActivity().getApplicationContext()).view(v).message(message).build(Gravity.TOP).listener(this).show();
    }
    private void doLogin(){
        signin.setEnabled(false);
        Boolean valid = validate();
        if (valid){
            /*doligin*/
            try {
                Boolean returns = do_login();
                if (returns){
                    showSnackBar(v, "Login Sukses");
                }else{
                    showSnackBar(v, "Login gagal, cek username atau password");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private Boolean do_login() throws IOException, JSONException {
        String sUsername = username.getText().toString();
        String sPassword = password.getText().toString();
        AndroidCall androidCall = new AndroidCall(getActivity().getApplicationContext());
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", sUsername)
                .addFormDataPart("password", sPassword)
                .build();
        String url = getString(R.string.module_server)+""+getString(R.string.endpoint_login);
        String response = androidCall.post(url, requestBody);
        JSONObject jsonObject = new JSONObject(response);
        int returns = jsonObject.getInt("code");
        if (returns == 300){
            JSONArray arrayData = jsonObject.getJSONArray("data");
            if (arrayData.length() > 0){
                JSONObject objectData = arrayData.getJSONObject(0);
                String sId = objectData.getString("id");
                String sNama = objectData.getString("nama");
                String sAlamat = objectData.getString("alamat");
                String sNotelp = objectData.getString("no_telp");
                String sUser = objectData.getString("email");
                String sPass = objectData.getString("password");
                String sImage = objectData.getString("image");
                String sDateAdd = objectData.getString("date_add");
                String sDateModified = objectData.getString("date_modified");
                String sKey = objectData.getString("key");
                String sReferral = objectData.getString("referral");
                String sKonfirm = objectData.getString("confirm");
                String sSha = objectData.getString("sha");

                session.setCustomParams("id", sId);
                session.setCustomParams("nama", sNama);
                session.setCustomParams("alamat", sAlamat);
                session.setCustomParams("no_telp", sNotelp);
                session.setCustomParams("email", sUser);
                session.setCustomParams("password", session.encryptString(sPass));
                session.setCustomParams("image", sImage);
                session.setCustomParams("date_add", sDateAdd);
                session.setCustomParams("date_modified", sDateModified);
                session.setCustomParams("key", session.encryptString(sKey));
                session.setCustomParams("referral", sReferral);
                session.setCustomParams("confirm", sKonfirm);
                session.setCustomParams("sha", sSha);
                session.setCustomParams("loggedin", true);
                session.setCustomParams("LoginState", 1);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void sessionChange() {

    }

    @Override
    public void onDismiss() {
        signin.setEnabled(true);
    }
}
