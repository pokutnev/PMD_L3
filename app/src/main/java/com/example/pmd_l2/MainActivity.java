package com.example.pmd_l2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements RecycleViewInreface {

    RecyclerView recyclerView;
    private static String json_url = "https://rickandmortyapi.com/api/character";

    ArrayList<Details> info = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Рик и Морти");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration
                (this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        MyAdapter adapter = new MyAdapter(MainActivity.this, info.toArray(new Details[info.size()]), this);
        recyclerView.setAdapter(adapter);

        new MyAsyncTask().execute(json_url);

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MainActivity2.class);

        intent.putExtra("NAME", info.get(position).getName());
        intent.putExtra("IMAGE", info.get(position).getImage());
        intent.putExtra("DESCRIPTION", info.get(position).getDescription());



        startActivity(intent);
    }


    private class MyAsyncTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... urls) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(json_url)
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            JSONObject jsonObject;

            String responsebody = null;
            try {
                responsebody = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            String name, photo;


            info.clear();
            try {


                JSONObject jsonResponse = new JSONObject(responsebody);
                JSONArray array = jsonResponse.getJSONArray("results");
                JSONArray array2 ;


                for (int i = 0; i < array.length(); i++) {

                    jsonObject = array.getJSONObject(i);
                    name = jsonObject.getString("name");
                    photo = jsonObject.getString("image");
                    array2 = jsonObject.getJSONArray("episode");
                    String[] epis = new String[array2.length()];

                    for (int j = 0; j < array2.length(); j++) {

                        epis[j] = array2.getString(j);

                    }

                    info.add(new Details(name, photo, epis));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void res) {
            MyAdapter adapter = new MyAdapter(MainActivity.this, info.toArray(new Details[info.size()]), MainActivity.this);
            recyclerView.setAdapter(adapter);
        }
    }

}