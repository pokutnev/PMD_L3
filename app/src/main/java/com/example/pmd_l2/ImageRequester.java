package com.example.pmd_l2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageRequester extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView imageView;
    Context context;
    String id;



    public void execute(String urlString, ImageView imageView, Context context, String id) {
        this.imageView = imageView;
        this.execute(urlString);
        this.context = context;
        this.id = id;
    }


    protected Bitmap doInBackground(String... urls) {
        return loadImageFromSite(urls[0]);
    }

    protected void onPostExecute(Bitmap result) {

        imageView.setImageBitmap(result);
        imageView.setVisibility(View.VISIBLE);

    }

    private Bitmap loadImageFromSite(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream inputstream = conn.getInputStream();
            Bitmap photo = BitmapFactory.decodeStream(inputstream);

            String filename = "img-" + id + ".jpg";
            FileOutputStream outputStream;

            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                photo.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return photo;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}