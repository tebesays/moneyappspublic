package com.example.toshiba.moneyapps2.Imp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.toshiba.moneyapps2.Model.RUser;

public class SharePreference {

    private static final String SHARED_PREF_NAME = "MoneyUser";
    private static final String KEY_NAMA = "KeyNama";
    private static final String KEY_USERNAME = "KeyUser";
    private static final String KEY_ID = "KeyId";
    private static final String KEY_UANG = "KeyUang";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static SharePreference mInstance;
    private static Context ctx;

    public SharePreference(Context context){
        ctx = context;
        sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharePreference getmInstance(Context context){
        if (mInstance == null){
            mInstance = new SharePreference(context);
        }
        return mInstance;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public void userLogin(RUser model){
        editor.putString(KEY_NAMA, model.getNama());
        editor.putString(KEY_USERNAME, model.getUsername());
        editor.putInt(KEY_ID, model.getIduser());
        editor.putInt(KEY_UANG, model.getUang());
        editor.apply();
    }

    public void userUang(int uangUser){
        editor.putInt(KEY_UANG, uangUser);
        editor.apply();
    }


    public RUser getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new RUser(
                sharedPreferences.getInt(KEY_ID, 0),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getInt(KEY_UANG, 0)
        );
    }

    public String getNama() {
        return sharedPreferences.getString(KEY_NAMA,"");
    }

    public String getUsername(){
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    public int getId() {
        return sharedPreferences.getInt(KEY_ID,0);
    }

    public int getUang() {
        return sharedPreferences.getInt(KEY_UANG,0);
    }

    public void logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        //ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }

}
