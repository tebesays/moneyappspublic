package com.example.toshiba.moneyapps2.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RQRincian extends StringRequest {
    private static final String LOAD_REQUEST_URL = "http://ribrick.com/moneyapps/tampil.php";
    private Map<String, String> params;

    public RQRincian(String iduser, Response.Listener<String> listener)
    {
        super(Request.Method.POST, LOAD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("iduser", iduser);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
