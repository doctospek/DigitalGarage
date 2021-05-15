package com.app.digitalgarage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.digitalgarage.webservices.ApiClient;
import com.app.digitalgarage.webservices.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    User user;
    Button btn_my_service, btn_add_service;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_my_service = findViewById(R.id.btn_my_service);
        btn_add_service = findViewById(R.id.btn_add_service);

        apiInterface = ApiClient.getClient(HomeActivity.this).create(ApiInterface.class);

        SharedPreferences sharedPreferences = getSharedPreferences("GARAGE", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Users", "");
        user = gson.fromJson(json, User.class);


        btn_my_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), MyServiceListActivty.class));
            }
        });

        btn_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addService();

            }
        });

    }

    private void addService() {


        apiInterface.addService(user.getId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());

                    if (jsonObject.getString("code").equals("200")) {

                        Toast.makeText(HomeActivity.this, "Service Added Successfully", Toast.LENGTH_SHORT);
                    } else {
                        Toast.makeText(HomeActivity.this, "Service Not Added Successfully", Toast.LENGTH_SHORT);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}