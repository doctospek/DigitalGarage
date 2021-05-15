package com.app.digitalgarage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.app.digitalgarage.webservices.ApiClient;
import com.app.digitalgarage.webservices.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    AppCompatRatingBar rating;
    EditText edit_comment;
    Button btn_add_feedback;
    ApiInterface apiInterface;
    User user;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        rating = findViewById(R.id.rating);
        edit_comment = findViewById(R.id.edit_comment);
        btn_add_feedback = findViewById(R.id.btn_add_feedback);

        service = (Service) getIntent().getSerializableExtra("service");

        apiInterface = ApiClient.getClient(FeedbackActivity.this).create(ApiInterface.class);

        SharedPreferences sharedPreferences = getSharedPreferences("GARAGE", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Users", "");
        user = gson.fromJson(json, User.class);

        btn_add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addFeedback();

            }
        });
    }

    private void addFeedback() {


        apiInterface.addFeedback(user.getId(), service.getId(), String.valueOf(rating.getRating()), edit_comment.getText().toString()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());

                    if (jsonObject.getString("code").equals("200")) {

                        Toast.makeText(FeedbackActivity.this, "Feedback added successfully", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(FeedbackActivity.this, "Feedback not added successfully", Toast.LENGTH_SHORT).show();
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