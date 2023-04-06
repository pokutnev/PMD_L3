package com.example.pmd_l2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder2> {

    Context context;
    private String[] listData;


    public MyAdapter2(Context context, String[] listData) {
        this.context = context;
        this.listData = listData;
    }


    @NonNull
    @Override
    public MyAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item2, parent, false);
        return new ViewHolder2(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.ViewHolder2 holder, int position) {
        holder.description.setText(listData[position]);
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView description;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description1);
        }
    }

}
