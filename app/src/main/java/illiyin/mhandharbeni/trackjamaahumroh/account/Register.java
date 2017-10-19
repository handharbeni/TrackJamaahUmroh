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
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import illiyin.mhandharbeni.httpcalllibrary.AndroidCall;
import illiyin.mhandharbeni.trackjamaahumroh.R;
import illiyin.mhandharbeni.utilslibrary.SnackBar;
import illiyin.mhandharbeni.utilslibrary.SnackBarListener;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by root on 9/13/17.
 */

public class Register extends Fragment implements SnackBarListener {
    View v;
    private TextView dosignin;

    private TextInputEditText nama, alamat, notelp, username, password, repeatpassword;
    private Button singup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout._layout_register, container, false);
        fetch_element();
        return v;
    }

    private void fetch_element(){
        dosignin = v.findViewById(R.id.dosignin);
        dosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToLogin();
            }
        });

        nama = v.findViewById(R.id.nama);
        alamat = v.findViewById(R.id.alamat);
        notelp = v.findViewById(R.id.notelp);
        username = v.findViewById(R.id.username);
        password = v.findViewById(R.id.password);
        repeatpassword = v.findViewById(R.id.repeatpassword);
        singup = v.findViewById(R.id.singup);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignup();
            }
        });
    }
    private void doSignup(){
        singup.setEnabled(false);
        Boolean validate = validate();
        if (validate){
            /*proses login*/
            try {
                Boolean reg = do_register();
                if(reg){
                    showSnackBar(v, "Register Berhasil, Silakan Login");
                }else{
                    showSnackBar(v, "Register Gagal, Akun telah terdaftar");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private Boolean validate(){
        if (nama.getText().toString().isEmpty()){
            setError(nama, "Nama Tidak Boleh Kosong");
            return false;
        }else if (alamat.getText().toString().isEmpty()){
            setError(alamat, "Alamat Tidak Boleh Kosong");
            return false;
        }else if (notelp.getText().toString().isEmpty()){
            setError(notelp, "No Telp Tidak Boleh Kosong");
            return false;
        }else if (username.getText().toString().isEmpty()){
            setError(username, "Username Tidak Boleh Kosong");
            return false;
        }else if (password.getText().toString().isEmpty()){
            setError(password, "Password Tidak Boleh Kosong");
            return false;
        }if (repeatpassword.getText().toString().isEmpty()){
            setError(repeatpassword, "Ulangi Password!");
            return false;
        }else if(!repeatpassword.getText().toString().equalsIgnoreCase(password.getText().toString())){
            setError(repeatpassword, "Password Tidak Sama");
            setError(password, "Password Tidak Sama");
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
    private void changeToLogin(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainframe, new Login());
        ft.commit();
    }

    @Override
    public void onDismiss() {
        singup.setEnabled(true);
    }
    private Boolean do_register() throws IOException, JSONException {
        AndroidCall androidCall = new AndroidCall(getActivity().getApplicationContext());
        String sNama = nama.getText().toString();
        String sAlamat = alamat.getText().toString();
        String sNotelp = notelp.getText().toString();
        String sUsername = username.getText().toString();
        String sPassword = password.getText().toString();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("nama", sNama)
                .addFormDataPart("alamat", sAlamat)
                .addFormDataPart("no_telp", sNotelp)
                .addFormDataPart("email", sUsername)
                .addFormDataPart("password", sPassword)
                .build();
        String url = getString(R.string.module_server)+""+getString(R.string.endpoint_register);
        String response = androidCall.post(url, requestBody);
        JSONObject jsonObject = new JSONObject(response);
        int returns = jsonObject.getInt("code");
        if (returns == 300){
            return true;
        }else{
            return false;
        }
    }
}
