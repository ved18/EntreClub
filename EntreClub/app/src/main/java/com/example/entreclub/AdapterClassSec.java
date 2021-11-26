package com.example.entreclub;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.Notification.Alert;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AdapterClassSec extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Adapter adapter;
    Context context;
    List<String> items = new ArrayList<String>();
    String emailid;
    //String[] items;
    public AdapterClassSec(Context context,int sz, List<String> items1,String emailid)
    {
        this.context=context;
        this.items = items1;
        this.emailid = emailid;
        Log.d("size",Integer.toString(sz));
        for(int i=0;i<items.size();i++)
            Log.d("vedang",items.get(i));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row1=inflater.inflate(R.layout.list_layout_accept,viewGroup,false);
        Item item=new Item(row1);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        //((Item)viewHolder.textView )

        ((Item)viewHolder).t.setText(items.get(i));

        ((Item) viewHolder).b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(emailid).update("flag","1");
                Log.d("error",items.get(i));
                //   adapter.notifyDataSetChanged();
                ((Item) viewHolder).b1.setText("accepted");

                Intent intent =new Intent(context, Alert.class);
                context.startActivity(intent);


            }

        });


        ((Item) viewHolder).b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(emailid).update("flag","2");

                //   adapter.notifyDataSetChanged();
                ((Item) viewHolder).b2.setText("rejected");
                Intent intent =new Intent(context,Alert.class);
                context.startActivity(intent);
            }

        });



    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class Item extends RecyclerView.ViewHolder {
        TextView t;
        ImageView v;
        Button b1,b2;
        public Item(@NonNull View itemView) {

            super(itemView);

            t=itemView.findViewById(R.id.textTitle);
            b1=itemView.findViewById(R.id.button1);
            b2=itemView.findViewById(R.id.button2);
            v=itemView.findViewById(R.id.imageView);
        }
    }
}
