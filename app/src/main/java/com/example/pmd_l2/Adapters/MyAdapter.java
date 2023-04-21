package com.example.pmd_l2.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmd_l2.ImageRequester;
import com.example.pmd_l2.R;
import com.example.pmd_l2.RecycleViewInreface;
import com.example.pmd_l2.entity.Details;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    private Details[] listData;
    private final RecycleViewInreface recycleViewInreface;


    public MyAdapter(Context context, Details[] listData, RecycleViewInreface recycleViewInreface) {
        this.context = context;
        this.listData = listData;
        this.recycleViewInreface = recycleViewInreface;
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


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, recycleViewInreface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.fullname.setText(listData[position].getName());

        if (hasConnection(context)){
            ImageRequester requester = new ImageRequester();
            requester.execute(listData[position].getImage(), holder.photo, context, listData[position].id);
        }
        else {
            new MyAdapter.MyAsyncTask(listData[position].id, holder.photo).execute();
        }

    }



    private class MyAsyncTask extends AsyncTask<String, Void, ArrayList<Details>> {


        String id;
        Bitmap photo;
        ImageView imageView;

        public MyAsyncTask(String id, ImageView imageView) {
            this.id = id;
            this.imageView = imageView;
        }

        @Override
        protected ArrayList<Details> doInBackground(String... strings) {

            String filename = "img-" + id + ".jpg";
            FileInputStream inputStream;

            try {
                inputStream = context.openFileInput(filename);

                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                photo = BitmapFactory.decodeStream(bufferedInputStream);

                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Details> details) {
            imageView.setImageBitmap(photo);
        }
    }

    public Details[] getElemets(){
        return listData;
    }


    @Override
    public int getItemCount() {
        return listData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullname;
        ImageView photo;
        public ViewHolder(@NonNull View itemView, RecycleViewInreface recycleViewInreface) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewInreface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recycleViewInreface.onItemClick(pos);
                        }
                    }
                }
            });
            fullname = itemView.findViewById(R.id.fullname);
            photo = itemView.findViewById(R.id.photo);
        }
    }

}
