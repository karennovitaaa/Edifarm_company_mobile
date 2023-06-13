package com.aditiyagilang.edifarm_company;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.aditiyagilang.edifarm_company.model.login.LoginData;

import java.util.HashMap;

public class SesionManager {

    public static final String ISLOGIN = "islogin";
    public static final String TOKEN = "token";
    public static final String USERNAME = "username";
    public static final String ID = "id";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String NAME = "name";
    public static final String PHOTO = "photo";
    public static final String BORN_DATE = "born_date";
    public static final String EMAIL = "email";
    public static final String LEVEL = "level";
    private final Context _context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SesionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user) {
        editor.putBoolean(ISLOGIN, true);
        editor.putString(TOKEN, user.getToken());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(LEVEL, user.getLevel());
        editor.putString(ADDRESS, user.getAddress());
        editor.putString(PHONE, user.getPhone());
        editor.putString(NAME, user.getName());
        editor.putString(PHOTO, user.getPhoto());
        editor.putString(BORN_DATE, user.getBornDate());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(ID, user.getId());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(TOKEN, sharedPreferences.getString(TOKEN, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(LEVEL, sharedPreferences.getString(LEVEL, null));
        user.put(ADDRESS, sharedPreferences.getString(ADDRESS, null));
        user.put(PHONE, sharedPreferences.getString(PHONE, null));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(PHOTO, sharedPreferences.getString(PHOTO, null));
        user.put(BORN_DATE, sharedPreferences.getString(BORN_DATE, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID, sharedPreferences.getString(ID, null));
        return user;

    }

    public void logoutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }

}
