package com.example.toshiba.moneyapps2.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RProses extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://ribrick.com/moneyapps/proses.php";
    private Map<String, String> params;

    public RProses(String iduser, String status, String judul, String nominal, String desk, String uang, Response.Listener<String> listener)
    {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("iduser", iduser);
        params.put("status", status);
        params.put("judul", judul);
        params.put("nominal", nominal);
        params.put("desk", desk);
        params.put("uang", uang);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
