package com.example.entreclub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.entreclub.Home.EventDetails;
import com.example.entreclub.Home.test;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.EventViewHolder> {

    public final String ID="ID";
    private Context mCtx;
    private List<FeedSecretary> li;

    public AdapterClass(Context mctx, List <FeedSecretary>li)
    {
        this.mCtx=mctx;
        this.li=li;
    }

    @Override
    public EventViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_layout,null);
        EventViewHolder holder = new EventViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder, int i ) {
        final FeedSecretary e=li.get(i);

       eventViewHolder.t1.setText(e.getTitle());
        eventViewHolder.t2.setText(e.getDate());
        eventViewHolder.t3.setText(e.getAgenda());
//
        eventViewHolder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mCtx, EventDetails.class);
                i.putExtra("ID",e.getId());
                mCtx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3;
        Button b1;
        public EventViewHolder(View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.textTitle);
            t2=itemView.findViewById(R.id.fetcheddate);
            t3=itemView.findViewById(R.id.desc);
            b1=itemView.findViewById(R.id.btn_explore);
        }



    }
}
