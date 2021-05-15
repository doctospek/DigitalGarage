package com.app.digitalgarage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.app.digitalgarage.webservices.ApiClient;
import com.app.digitalgarage.webservices.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edit_number, edit_password;
    Button btn_submit;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_number = findViewById(R.id.edit_number);
        edit_password = findViewById(R.id.edit_password);
        btn_submit = findViewById(R.id.btn_submit);

        apiInterface = ApiClient.getClient(LoginActivity.this).create(ApiInterface.class);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });

    }

    private void login() {

        apiInterface.login(edit_number.getText().toString(), edit_password.getText().toString()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());

                    if (jsonObject.getString("code").equals("200")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("user");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            User user = new User();
                            user.setId(object.getString("id"));
                            user.setFname(object.getString("fname"));
                            user.setLnme(object.getString("lname"));
                            user.setPassword(object.getString("password"));
                            user.setChesis_no(object.getString("chesis_no"));
                            user.setCar_no(object.getString("car_no"));
                            user.setYear(object.getString("year"));
                            user.setBirthday(object.getString("birthday"));
                            user.setMobile_no(object.getString("mobile_no"));
                            user.setGender(object.getString("gender"));
                            user.setPermanent_address(object.getString("permanent_address"));
                            user.setPresent_address(object.getString("present_address"));


                            SharedPreferences sharedPreferences = getSharedPreferences("GARAGE", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(user);
                            prefsEditor.putString("Users", json);
                            prefsEditor.apply();

                            if (user.getId().equals("1")){

                                startActivity(new Intent(getBaseContext(),MainActivity.class));

                            }else {
                                startActivity(new Intent(getBaseContext(),HomeActivity.class));
                            }

                        }

                    } else {

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