package com.zahwaoctavioliena.ppb.uas.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.zahwaoctavioliena.ppb.uas.R;
import com.zahwaoctavioliena.ppb.uas.Apiclient.APIClient;
import com.zahwaoctavioliena.ppb.uas.Apiclient.Kasir;
import com.zahwaoctavioliena.ppb.uas.Apiclient.KasirInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    SharedPreferences sharedPref;
    KasirInterface kasirInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        kasirInterface = APIClient.getClient().create(KasirInterface.class);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString(MainActivity.KEY_USER, null);
        if(username != null){
            //Membuka MainActivity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void postLogin(View v){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        Call<Kasir> postLogin = kasirInterface.postLogin(username, password);

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(LoginActivity.this);

        postLogin.enqueue(new Callback<Kasir>() {
            @Override
            public void onResponse(Call<Kasir> call, Response<Kasir> response) {
                Log.d("post_login: ", response.toString());
                if(response.body().getUsername() != null){
                    //Menyimpan sharedpreference
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(MainActivity.KEY_USER, username);
                    editor.commit();

                    //Membuka MainActivity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    builder.setMessage("Username atau password salah!");
                    builder.setTitle("Peringatan !");
                    builder.setCancelable(false);
                    builder
                            .setNegativeButton(
                                    "Ok",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which)
                                        {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<Kasir> call, Throwable t) {
                Log.e("error_login: ", t.getMessage());
            }
        });
    }
}
