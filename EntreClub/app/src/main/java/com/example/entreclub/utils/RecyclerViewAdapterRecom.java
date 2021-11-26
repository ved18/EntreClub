package com.example.entreclub.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entreclub.R;

public class RecyclerViewAdapterRecom extends RecyclerView.Adapter<RecyclerViewAdapterRecom.ViewHolder> {

    private String[] data;
    public RecyclerViewAdapterRecom(String[] data){
        this.data=data;

    }



   // public void setOnItemClickListener(){}

    @NonNull
    @Override
    public RecyclerViewAdapterRecom.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_recommend_list,viewGroup,false);
        return new RecyclerViewAdapterRecom.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = data[i];
        viewHolder.textTitle.setText(title);
    }



    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textTitle = itemView.findViewById(R.id.textTitle);

        }
    }

}

