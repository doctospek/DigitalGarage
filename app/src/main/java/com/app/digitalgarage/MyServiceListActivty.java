package com.app.digitalgarage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.digitalgarage.webservices.ApiClient;
import com.app.digitalgarage.webservices.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyServiceListActivty extends AppCompatActivity {

    ListView listView;
    ApiInterface apiInterface;
    User user;
    ArrayList<Service> serviceArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service_list_activty);

        SharedPreferences sharedPreferences = getSharedPreferences("GARAGE", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Users", "");
        user = gson.fromJson(json, User.class);

        apiInterface = ApiClient.getClient(MyServiceListActivty.this).create(ApiInterface.class);

        listView = findViewById(R.id.listView);


        getList();
    }

    private void getList() {

        serviceArrayList = new ArrayList<>();
        ServiceAdapter serviceAdapter = new ServiceAdapter(MyServiceListActivty.this, serviceArrayList);

        apiInterface.getUserService(user.getId()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());

                    if (jsonObject.getString("code").equals("200")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("service");

                        for (int i = 0; i < jsonArray.length(); i++) {


                            JSONObject object = jsonArray.getJSONObject(i);

                            Service service = new Service();

                            service.setId(object.getString("id"));
                            service.setCreated_at(object.getString("created_at"));
                            service.setUser_id(object.getString("user_id"));

                            serviceArrayList.add(service);

                            serviceAdapter.notifyDataSetChanged();
                        }

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listView.setAdapter(serviceAdapter);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }
}