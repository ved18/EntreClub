package com.example.entreclub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.Home.AddLog;
import com.example.entreclub.Home.Mom;
import com.example.entreclub.Home.ViewMom;
import com.example.entreclub.Home.log;

import java.util.List;

public class SecAdapterClass extends RecyclerView.Adapter<SecAdapterClass.EventViewHolder> {

    public final String ID="ID";
    private Context mCtx;
    private List<FeedSecretary> li;

    public SecAdapterClass(Context mctx, List <FeedSecretary>li)
    {
        this.mCtx=mctx;
        this.li=li;
    }

    @Override
    public EventViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.sec_past_events,null);
        EventViewHolder holder = new EventViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder eventViewHolder,final int i ) {
        final FeedSecretary e=li.get(i);

       eventViewHolder.t1.setText(e.getTitle());
        eventViewHolder.t2.setText(e.getDate());
        eventViewHolder.t3.setText(e.getAgenda());



        eventViewHolder.b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FeedSecretary e=li.get(i);

                Intent i = new Intent( mCtx, log.class);
                i.putExtra("ID",e.getId());
                Log.d("hello ved",e.getId());
                mCtx.startActivity(i);

            }
        })     ;





        eventViewHolder.b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, AddLog.class);
                intent.putExtra("event_id",e.getId());
                mCtx.startActivity(intent);
            }
        });








        eventViewHolder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, Mom.class);
                intent.putExtra("event_id",e.getId());
                //Log.d("hello ved",e.getId());
                mCtx.startActivity(intent);
            }
        });

        eventViewHolder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ViewMom.class);
                intent.putExtra("event_id",e.getId());
                mCtx.startActivity(intent);
            }
        });

//        eventViewHolder.b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(mCtx,EventDetailsActivity.class);
//                i.putExtra("ID",e.getId());
//                mCtx.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3;
        Button b1,b2,b3,b4;
        public EventViewHolder(View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.textTitle);
            t2=itemView.findViewById(R.id.fetcheddate);
            t3=itemView.findViewById(R.id.desc);
            b1=itemView.findViewById(R.id.btn_explore);
            b2=itemView.findViewById(R.id.btn_view_mom);
            b3=itemView.findViewById(R.id.btn_add_log);
            b4=itemView.findViewById(R.id.btn_log);



        }



    }
}
