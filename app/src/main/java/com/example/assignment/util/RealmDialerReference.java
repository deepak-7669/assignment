package com.example.assignment.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.assignment.util.AppController;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RealmDialerReference {
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor prefs_edit;
    private static RealmDialerReference instance;
    private static final String PREFS = "prefs";

    public RealmDialerReference(Context context) {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs_edit = preferences.edit();
    }

    public final static RealmDialerReference getInstance(Context context) {
        if (instance == null) {
            instance = new RealmDialerReference(context);
        }
        return instance;
    }

    public void setLangCode(String language_code) {
        prefs_edit.putString("language_code", language_code);
        prefs_edit.commit();
    }

    public String getLangCode() {
        return preferences.getString("language_code", "");
    }

    // TODO: 4/13/21 old lang code
    public void setoldLangCode(String language_code) {
        prefs_edit.putString("language_code_old", language_code);
        prefs_edit.commit();
    }

    public String getoldLangCode() {
        return preferences.getString("language_code_old", "");
    }

    public void setMobileNumber(String mobile_number) {
        prefs_edit.putString("mobile_number", mobile_number);
        prefs_edit.commit();
    }

    public String getMobileNumber() {
        return preferences.getString("mobile_number", "");
    }

    public void setAuthToken(String auth_token) {
        prefs_edit.putString("auth_token", auth_token);
        prefs_edit.commit();
    }

    public String getAuthToken() {
        return preferences.getString("auth_token", "");
    }

    public void setDeviceToken(String device_token) {
        prefs_edit.putString("device_token", device_token);
        prefs_edit.commit();
    }

    public String getDeviceToken() {
        return preferences.getString("device_token", "");
    }

    public void setLocalIP(String localIP) {
        prefs_edit.putString("local_ip", localIP);
        prefs_edit.commit();
    }

    public String getLocalIP() {
        return preferences.getString("local_ip", "");
    }

    public void setLoggedIn(boolean isLoggedIn) {
        prefs_edit.putBoolean("isLoggedIn", isLoggedIn);
        prefs_edit.commit();
    }

    public Boolean isLoggedIn() {
        return preferences.getBoolean("isLoggedIn", false);
    }
    public void setSimSlotPref(String simPrefresnce) {
        prefs_edit.putString(AppController.simPreference, simPrefresnce);
        prefs_edit.commit();

    }

    public String getSimSlotPref() {
        return preferences.getString(AppController.simPreference, "");
    }
/*
    public static void saveMap(Context context,String key, Map<Integer, SpeedDialContactModel> inputMap){
        SharedPreferences pSharedPref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if (pSharedPref != null){
            Gson gson = new Gson();
            String hashMapString = gson.toJson(inputMap);
            //save in shared prefs
            pSharedPref.edit().putString(key, hashMapString).apply();
        }
    }
*/
/*
    public static Map<Integer, SpeedDialContactModel> loadMap(String key,Context context){
        Map<Integer, SpeedDialContactModel> outputMap = new HashMap<Integer, SpeedDialContactModel>();
        SharedPreferences pSharedPref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        try{
            //get from shared prefs
            String storedHashMapString = pSharedPref.getString(key, (new JSONObject()).toString());
            java.lang.reflect.Type type = new TypeToken<HashMap<Integer, SpeedDialContactModel>>(){}.getType();
            Gson gson = new Gson();
            return  gson.fromJson(storedHashMapString, type);

        }catch(Exception e){
            e.printStackTrace();
        }
        return outputMap;
    }
*/

    public void clearPref(){
        prefs_edit.clear();
        prefs_edit.commit();
    }
}
