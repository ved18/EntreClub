package com.example.entreclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HavesListAdapter extends RecyclerView.Adapter<HavesListAdapter.HavesViewHolder>{

    private Context nCtx;
    private List<AsksListClass> li;


    public HavesListAdapter(Context nCtx, List<AsksListClass> li) {
        this.nCtx = nCtx;
        this.li = li;
    }

    @NonNull
    @Override
    public HavesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(nCtx);
        View view=inflater.inflate(R.layout.haves_list,null);
        HavesViewHolder holder= new HavesViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull HavesViewHolder havesViewHolder, int i) {

            AsksListClass asksListClass=li.get(i);
            havesViewHolder.t1.setText(asksListClass.getEmailid());
            havesViewHolder.t2.setText(asksListClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class HavesViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1;
        TextView t2;
        public HavesViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView6);
            t2=itemView.findViewById(R.id.textView7);
        }
    }
}
