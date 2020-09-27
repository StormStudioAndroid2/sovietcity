package com.sovietcity.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthorisationWorker {
    private String name;
    private String mail;
    private static final String KEY_PREFERENCES = "com.sovietcity";
    private static final String NAME_KEY = "name";
    private static final String MAIL_KEY = "mail";
    private ArrayList<Record> recordArrayList;
    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public void createRecord() {


        SovietApiService sovietApiService = SovietApiService.instance;
        Call<Response> callAdd = sovietApiService.addUser("lololoshka", "enstein225@gmail.com");
        callAdd.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
        Call<Response> call = sovietApiService.setRecord("enstein225@gmail.com", 100.0, 100);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    public void addUser(final String name, final String mail, final Context context) {
        SovietApiService sovietApiService = SovietApiService.instance;
        Call<Response> callAdd = sovietApiService.addUser(name, mail);
        callAdd.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response response1 = response.body();
                if (response1.code != 0) {

                    writeNameUser(context, name, mail);
                }
                Log.i("STATUS3", response1.message);
                Log.i("STATUS4", response1.code + "");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addRecord(double money, int emotion) {
        SovietApiService sovietApiService = SovietApiService.instance;
        if (this.mail != "") {
            Call<Response> callAdd = sovietApiService.setRecord(this.mail, money, emotion);
            callAdd.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Response res = response.body();
                    Log.i("STATUS1", res.message);
                    Log.i("STATUS2", res.code + "");
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public boolean getNameUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
        this.name = sharedPreferences.getString(NAME_KEY, "");
        this.mail = sharedPreferences.getString(MAIL_KEY, "");
        if ((name != "") && (mail != "")) {
            return true;
        }

        return false;
    }

    public void writeNameUser(Context context, String name, String mail) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(MAIL_KEY, mail);
        editor.apply();
    }

    public boolean isAuthorisation() {
        if ((name != null) && (!name.equals("")) && (mail != null) && (!mail.equals(""))) {
            return true;
        }

        return false;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Record> getRecords() {
        SovietApiService sovietApiService = SovietApiService.instance;
        Call<Record[]> callget = sovietApiService.getRecords();
        callget.enqueue(new Callback<Record[]>() {
            @Override
            public void onResponse(Call<Record[]> call, retrofit2.Response<Record[]> response) {
                Record[] records = response.body();
                Arrays.sort(records);
                ArrayList<Record> arrayList = new ArrayList<Record>(Arrays.asList(records));
                recordArrayList = arrayList;
            }

            @Override
            public void onFailure(Call<Record[]> call, Throwable t) {

            }
        });
        return recordArrayList;
    }
}
