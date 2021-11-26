package com.example.entreclub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AsksListAdapter extends RecyclerView.Adapter<AsksListAdapter.AsksListHolder>
{
    Context mCtx;

    private List<AsksListClass> li;


    public AsksListAdapter(Context mCtx, List<AsksListClass> li) {
        this.mCtx = mCtx;
        this.li = li;
    }

    @NonNull
    @Override
    public AsksListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.asks_list,null);
        AsksListHolder holder=new AsksListHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull AsksListHolder asksListHolder, int i) {

        AsksListClass a=li.get(i);
        asksListHolder.t1.setText(a.getEmailid());
        asksListHolder.t2.setText(a.getDescription());

    }

    @Override
    public int getItemCount() {
    return li.size();
    }

    class AsksListHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2;
        public AsksListHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView4);
            t2=itemView.findViewById(R.id.t_platform);
        }
    }
}
