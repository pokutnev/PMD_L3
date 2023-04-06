package com.example.pmd_l2;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageRequester extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView imageView;
    @SuppressLint("StaticFieldLeak")
    private ProgressBar progressBar;

    public void execute(String urlString, ImageView imageView) {
        this.imageView = imageView;
        this.execute(urlString);
    }


    protected Bitmap doInBackground(String... urls) {
        return loadImageFromSite(urls[0]);
    }

    protected void onPostExecute(Bitmap result) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
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
            return BitmapFactory.decodeStream(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}