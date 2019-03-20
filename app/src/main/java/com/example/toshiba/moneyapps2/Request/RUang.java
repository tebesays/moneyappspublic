package com.example.toshiba.moneyapps2.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RUang extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://ribrick.com/moneyapps/uang.php";
    private Map<String, String> params;

    public RUang(String iduser, Response.Listener<String> listener)
    {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("iduser", iduser);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}