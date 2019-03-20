package com.example.toshiba.moneyapps2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.toshiba.moneyapps2.Adapter.RincianAdapter;
import com.example.toshiba.moneyapps2.Imp.SharePreference;
import com.example.toshiba.moneyapps2.Model.RRincian;
import com.example.toshiba.moneyapps2.Request.RQRincian;
import com.example.toshiba.moneyapps2.Request.RUang;
import com.example.toshiba.moneyapps2.UI.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView RVrincian;
    FloatingActionButton FABtambah;
    TextView nama, saldo;
    ArrayList<RRincian> RincianList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FABtambah = (FloatingActionButton) findViewById(R.id.BTNtambah);
        nama = (TextView) findViewById(R.id.NamaUser);
        RVrincian = (RecyclerView) findViewById(R.id.RVRincian);
        saldo = (TextView) findViewById(R.id.SaldoUser);

        String nama2 = SharePreference.getmInstance(MainActivity.this).getNama();
        nama.setText(nama2);

        //UNTUK TAMPIL
        RVrincian.setHasFixedSize(true);
        RVrincian.setLayoutManager(new LinearLayoutManager(this));

        RincianList = new ArrayList<>();

        loaddata();
        loadrincian();

        FABtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Proccess.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            SharePreference.getmInstance(getApplicationContext()).logout();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void loaddata()
    {
        int iduser = SharePreference.getmInstance(MainActivity.this).getId();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("succ");
                    if (success){
                        Locale localeID = new Locale("in", "ID");
                        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                        String uang = jsonResponse.getString("uang");
                        int isi = Integer.parseInt(uang);
                        saldo.setText(formatRupiah.format(isi));

                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        RequestQueue queuee = Volley.newRequestQueue(MainActivity.this);
        queuee.add(pgrq2);

    }

    public void loadrincian()
    {
        int idku = SharePreference.getmInstance(MainActivity.this).getId();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                try {
                    //converting the string to json array object`
                    JSONArray array = new JSONArray(response);

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject notif = array.getJSONObject(i);
                        //adding the product to product list
                        RincianList.add(new RRincian(
                                notif.getInt("idrincian"),
                                notif.getString("judul"),
                                notif.getInt("nominal"),
                                notif.getString("status"),
                                notif.getString("deskripsi")
                        ));
                    }
                    //creating adapter object and setting it to recyclerview
                    RincianAdapter adapter = new RincianAdapter(MainActivity.this, RincianList);
                    RVrincian.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RQRincian req = new RQRincian(String.valueOf(idku), responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(req);
    }

}
