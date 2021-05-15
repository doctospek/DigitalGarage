package com.app.digitalgarage;/* Created By Ashwini Saraf on 4/2/2021*/

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ServiceAdapter extends BaseAdapter {

    Context context;
    ArrayList<Service> serviceArrayList;
    User user;

    public ServiceAdapter(Context context, ArrayList<Service> serviceArrayList) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
        SharedPreferences sharedPreferences = context.getSharedPreferences("GARAGE", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Users", "");
        user = gson.fromJson(json, User.class);
    }

    @Override
    public int getCount() {
        return serviceArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return serviceArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.service_listview, viewGroup, false);

        Service service = serviceArrayList.get(i);


        TextView text_service_id = view.findViewById(R.id.text_service_id);
        TextView text_created_at = view.findViewById(R.id.text_created_at);
        Button btn_add_feedback = view.findViewById(R.id.btn_add_feedback);
        Button btn_remind_user = view.findViewById(R.id.btn_remind_user);

        text_created_at.setText(service.getCreated_at());
        text_service_id.setText(service.getId());

        if (user.getId().equals("1")) {

            btn_add_feedback.setVisibility(View.GONE);

        } else {

            btn_remind_user.setVisibility(View.GONE);

        }

        btn_add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, FeedbackActivity.class).putExtra("service", service));

            }
        });

        btn_remind_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return view;
    }


}
