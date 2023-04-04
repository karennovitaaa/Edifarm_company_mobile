package com.aditiyagilang.edifarm_company;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.aditiyagilang.edifarm_company.model.login.LoginData;

import java.util.HashMap;

public class SesionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private static final String ISLOGIN = "islogin" ;
    private static final String TOKEN = "token";
    private static final String USERNAME = "username";
    private static  final String LEVEL = "level";

    public SesionManager(Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }
    public void createLoginSession(LoginData user){
        editor.putBoolean(ISLOGIN, true);
        editor.putString(TOKEN, user.getToken());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(LEVEL, user.getLevel());
        editor.commit();
    }

    public HashMap<String, String> getUSerDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(TOKEN, sharedPreferences.getString(TOKEN,null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(LEVEL, sharedPreferences.getString(LEVEL,null));
        return user;

    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }
    public boolean isLogin(){
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }

}
