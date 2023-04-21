package com.example.pmd_l2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;


import com.example.pmd_l2.AppDB;
import com.example.pmd_l2.entity.Details;
import com.example.pmd_l2.Adapters.MyAdapter;
import com.example.pmd_l2.R;
import com.example.pmd_l2.RecycleViewInreface;
import com.example.pmd_l2.SpacesItemDecoration;
import com.example.pmd_l2.entity.IDpersonIDurl;
import com.example.pmd_l2.entity.Urlepisode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements RecycleViewInreface {

    RecyclerView recyclerView;
    private static String json_url = "https://rickandmortyapi.com/api/character";
    MyAdapter adapter;


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




        new MyAsyncTask().execute(json_url);

    }




    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MainActivity2.class);


        Details[] t = adapter.getElemets();

        intent.putExtra("id", t[position].id);


        startActivity(intent);
    }


    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }


    private class MyAsyncTask extends AsyncTask<String, Void, ArrayList<Details>> {


        protected ArrayList<Details> doInBackground(String... urls) {

            AppDB db = Room. databaseBuilder(getApplicationContext(),
                            AppDB. class, "RickAndMortyDB")
                    .fallbackToDestructiveMigration()
                    .build();


            if(hasConnection(MainActivity.this)){

                db.clearAllTables();

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


                try {

                    JSONObject jsonResponse = new JSONObject(responsebody);
                    JSONArray array = jsonResponse.getJSONArray("results");
                    JSONArray array2;



                    for (int i = 0; i < array.length(); i++) {

                        jsonObject = array.getJSONObject(i);
                        name = jsonObject.getString("name");
                        photo = jsonObject.getString("image");
                        array2 = jsonObject.getJSONArray("episode");
                        String id = UUID.randomUUID().toString();
                        db.userDao().insert(new Details(id, name, photo));

                        for (int j = 0; j < array2.length(); j++) {

                            String idurl = UUID.randomUUID().toString();
                            db.urlDao().insert(new Urlepisode(idurl, array2.getString(j)));
                            db.iDpersonIDurl().insert(new IDpersonIDurl(UUID.randomUUID().toString(), id, idurl));

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            return (ArrayList<Details>) db.userDao().getAll();
        }

        protected void onPostExecute(ArrayList<Details> res) {

            adapter = new MyAdapter(MainActivity.this, res.toArray(new Details[0]), MainActivity.this);
            recyclerView.setAdapter(adapter);
        }
    }

}