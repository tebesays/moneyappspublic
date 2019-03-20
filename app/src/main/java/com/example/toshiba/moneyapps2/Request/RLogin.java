package com.example.toshiba.moneyapps2.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RLogin extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://ribrick.com/moneyapps/login.php";
    private Map<String, String> params;

    public RLogin(String username, String password, Response.Listener<String> listener)
    {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
