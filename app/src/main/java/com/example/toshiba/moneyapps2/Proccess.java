package com.example.toshiba.moneyapps2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.toshiba.moneyapps2.Imp.SharePreference;
import com.example.toshiba.moneyapps2.Request.RProses;
import com.example.toshiba.moneyapps2.Request.RUang;

import org.json.JSONException;
import org.json.JSONObject;

public class Proccess extends AppCompatActivity {

    EditText ETjudul,ETnominal,ETdesk;
    Spinner status;
    Button BTNkirim;
    int uang2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proccess);

        //Add back button
        getSupportActionBar().setTitle("Tambah Transaksi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ETjudul = (EditText) findViewById(R.id.ETJudul);
        ETnominal = (EditText) findViewById(R.id.ETNominal);
        ETdesk = (EditText) findViewById(R.id.ETDeskripsi);
        status = (Spinner) findViewById(R.id.SStatus);
        BTNkirim = (Button) findViewById(R.id.BTNKirim);


        BTNkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaddata();
            }
        });
    }

    public void loaddata()
    {
        int iduser = SharePreference.getmInstance(Proccess.this).getId();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("succ");
                    if (success){
                        String uang = jsonResponse.getString("uang");
                        uang2 = Integer.parseInt(uang);

                        proses();
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Proccess.this);
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

        RUang pgrq2 = new RUang(String.valueOf(iduser), responseListener);
        RequestQueue queuee = Volley.newRequestQueue(Proccess.this);
        queuee.add(pgrq2);

    }

    private void proses()
    {
        String status2,judul,nominal,desk;

        status2 = status.getSelectedItem().toString();
        judul = ETjudul.getText().toString();
        nominal = ETnominal.getText().toString();
        desk = ETdesk.getText().toString();

        int idus = SharePreference.getmInstance(Proccess.this).getId();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("succ");
                    if (success){
                        Toast.makeText(Proccess.this, "Berhasil", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(Proccess.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Proccess.this);
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

        RProses req = new RProses(String.valueOf(idus),status2,judul,nominal,desk,String.valueOf(uang2), responseListener);
        RequestQueue queue = Volley.newRequestQueue(Proccess.this);
        queue.add(req);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            startActivity(new Intent(Proccess.this, MainActivity.class));
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
