package com.example.pmd_l2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    private Details[] listData;
    private final RecycleViewInreface recycleViewInreface;


    public MyAdapter(Context context, Details[] listData, RecycleViewInreface recycleViewInreface) {
        this.context = context;
        this.listData = listData;
        this.recycleViewInreface = recycleViewInreface;
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
        ImageRequester requester = new ImageRequester();
        requester.execute(listData[position].getImage(), holder.photo);
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
