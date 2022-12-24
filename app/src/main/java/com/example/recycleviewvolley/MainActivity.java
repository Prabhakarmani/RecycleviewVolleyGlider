package com.example.recycleviewvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity
{
    private static final String URL="https://api.github.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView userlist=(RecyclerView)findViewById(R.id.userList);
        userlist.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                User[] users = gson.fromJson(response,User[].class);
                userlist.setAdapter(new GithubAdapter(MainActivity.this,users));
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}