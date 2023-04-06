package com.example.pmd_l2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {

    TextView tvView;
    ImageView imgView;
    TextView descrip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String[] des = intent.getStringArrayExtra("DESCRIPTION");

        recyclerView.setHasFixedSize(true);

        MyAdapter2 adapter2 = new MyAdapter2(MainActivity2.this, new String[0]);
        recyclerView.setAdapter(adapter2);


        tvView = findViewById(R.id.tvView);
        imgView = findViewById(R.id.imgView);


        String Img = intent.getStringExtra("IMAGE");
        ImageRequester requester = new ImageRequester();
        requester.execute(Img, imgView);

        String Name = intent.getStringExtra("NAME");
        tvView.setText(Name);



        new MainActivity2.MyAsyncTask(des, recyclerView).execute();


    }

    private class MyAsyncTask extends AsyncTask<String, Void, String[]> {

        String[] des;
        RecyclerView recyclerView;

        public MyAsyncTask(String[] des, RecyclerView recyclerView) {
            this.des = des;
            this.recyclerView = recyclerView;
        }


        protected String[] doInBackground(String... urls) {

            String[] res = new String[des.length];

            OkHttpClient client = new OkHttpClient();

            JSONArray array2 = null;
            try {
                array2 = new JSONArray(des);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            for (int j = 0; j < array2.length(); j++) {

                Request request1 = null;
                try {
                    request1 = new Request.Builder()
                            .url(array2.getString(j))
                            .build();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try (Response response2 = client.newCall(request1).execute()) {
                    JSONObject jsonResponse2 = new JSONObject(response2.body().string());
                    res[j] = jsonResponse2.getString("name");
                    res[j] += " ";
                    res[j] += jsonResponse2.getString("air_date");
                    res[j] += " ";
                    res[j] += jsonResponse2.getString("episode");
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            return res;
        }

        protected void onPostExecute(String[] res) {
            MyAdapter2 adapter = new MyAdapter2(MainActivity2.this, res);
            recyclerView.setAdapter(adapter);
        }

    }

}