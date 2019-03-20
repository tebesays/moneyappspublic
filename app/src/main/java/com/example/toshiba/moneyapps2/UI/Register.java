package com.example.toshiba.moneyapps2.UI;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.toshiba.moneyapps2.R;
import com.example.toshiba.moneyapps2.Request.Rregist;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText ETnama,ETemail,ETusername,ETpassword;
    Button BTNregist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ETnama = (EditText) findViewById(R.id.ETNama);
        ETemail = (EditText) findViewById(R.id.ETemail);
        ETusername = (EditText) findViewById(R.id.ETusername);
        ETpassword = (EditText) findViewById(R.id.ETpassword);
        BTNregist = (Button) findViewById(R.id.BTNregist);

        BTNregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });
    }

    public void regist()
    {
        String namaET = ETnama.getText().toString();
        String emailET = ETemail.getText().toString();
        String usernameET = ETusername.getText().toString();
        String passwordET = ETpassword.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("succ");
                    if (success){
                        Toast.makeText(Register.this, "Registrasi Selesai", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Gagal Registrasi, kemungkinan username yang anda daftarkan telah dipakai")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Rregist pgrqq = new Rregist(namaET, emailET, usernameET, passwordET, responseListener);
        RequestQueue queueee = Volley.newRequestQueue(Register.this);
        queueee.add(pgrqq);
    }
}
