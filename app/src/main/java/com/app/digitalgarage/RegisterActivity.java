package com.app.digitalgarage;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.digitalgarage.webservices.ApiClient;
import com.app.digitalgarage.webservices.ApiInterface;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edit_car_chesis_number, edit_firstname, edit_lastname, edit_car_name, edit_year, edit_birthday, edit_mobile_no,
            edit_gender, edit_permanent_address, edit_present_address;
    Button btn_register;
    ApiInterface apiInterface;
    String password;
    SmsManager smsManager;

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        checkPermission(Manifest.permission.SEND_SMS, 1);

        edit_car_chesis_number = findViewById(R.id.edit_car_chesis_number);
        edit_firstname = findViewById(R.id.edit_firstname);
        edit_lastname = findViewById(R.id.edit_lastname);
        edit_car_name = findViewById(R.id.edit_car_name);
        edit_year = findViewById(R.id.edit_year);
        edit_birthday = findViewById(R.id.edit_birthday);
        edit_mobile_no = findViewById(R.id.edit_mobile_no);
        edit_gender = findViewById(R.id.edit_gender);
        edit_permanent_address = findViewById(R.id.edit_permanent_address);
        edit_present_address = findViewById(R.id.edit_present_address);
        btn_register = findViewById(R.id.btn_register);

        smsManager = SmsManager.getDefault();

        apiInterface = ApiClient.getClient(RegisterActivity.this).create(ApiInterface.class);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                password = getRandomNumberString();
                register();

            }
        });


    }

    private void register() {


        apiInterface.register(edit_firstname.getText().toString(), edit_lastname.getText().toString(), edit_mobile_no.getText().toString(), edit_car_name.getText().toString(),
                edit_year.getText().toString(), edit_birthday.getText().toString(), edit_gender.getText().toString(),
                edit_permanent_address.getText().toString(), edit_present_address.getText().toString(),
                edit_car_chesis_number.getText().toString(), password).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());

                    if (jsonObject.getString("code").equals("200")) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                        String msg = "Your password for login Digital Garage App is :" + password;
                        smsManager.sendTextMessage(edit_mobile_no.getText().toString(), null, msg, pi, null);

                        Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(), MainActivity.class));


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

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {


            ActivityCompat.requestPermissions(RegisterActivity.this,
                    new String[]{permission},
                    requestCode);
        } else {
//            Toast.makeText(ForgetPasswordActivity.this,
//                    "Permission already granted",
//                    Toast.LENGTH_SHORT)
//                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(RegisterActivity.this,
                        "Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(RegisterActivity.this,
                        "Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }


    }
}