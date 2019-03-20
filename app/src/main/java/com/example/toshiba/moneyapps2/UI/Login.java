package com.example.toshiba.moneyapps2.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.toshiba.moneyapps2.Imp.SharePreference;
import com.example.toshiba.moneyapps2.MainActivity;
import com.example.toshiba.moneyapps2.Model.RUser;
import com.example.toshiba.moneyapps2.R;
import com.example.toshiba.moneyapps2.Request.RLogin;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText ETemail,ETpassword;
    Button BTNlogin, BTNregist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharePreference.getmInstance(Login.this).isLoggedIn()){
            Intent intent = new Intent(Login.this, MainActivity.class);
            Login.this.startActivity(intent);
            finish();
        }

        ETemail = (EditText) findViewById(R.id.email);
        ETpassword = (EditText) findViewById(R.id.password);
        BTNlogin = (Button) findViewById(R.id.email_sign_in_button);
        BTNregist = (Button) findViewById(R.id.BTNRegistAwal);

        BTNregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        BTNlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login()
    {
        String password2 = ETpassword.getText().toString();
        String username2 = ETemail.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        int IdUser = jsonResponse.getInt("IdUser");
                        String name = jsonResponse.getString("Nama");
                        String username = jsonResponse.getString("Username");
                        int uang = jsonResponse.getInt("Uang");

                        RUser model = new RUser(IdUser,name,username,uang);
                        SharePreference.getmInstance(getApplicationContext()).userLogin(model);

                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Gagal Memanggil")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RLogin req = new RLogin(username2,password2,responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(req);
    }
}
