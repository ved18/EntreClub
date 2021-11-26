package com.example.entreclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsActivity extends AppCompatActivity {
//
//    FirebaseFirestore db,db1;
//    TextView t;
//    RecyclerView recyclerView1,recyclerView2;
//    AsksListAdapter asksListAdapter;
//    HavesListAdapter havesListAdapter;
//    List<AsksListClass> li,li2;
//    StringBuffer b;
//  //  Button btn;
//    FloatingActionButton btn;
//    Intent i;
//    String eventId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_event_details);
//       t=(TextView)findViewById(R.id.info);
//        db= FirebaseFirestore.getInstance();
//        db1= FirebaseFirestore.getInstance();
//        recyclerView2=(RecyclerView)findViewById(R.id.recycler2);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
//
//        li=new ArrayList<>();
//        li2=new ArrayList<>();
//        recyclerView1=(RecyclerView)findViewById(R.id.recyclerview1);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
//        i=getIntent();
//        btn=(FloatingActionButton) findViewById(R.id.floatingActionButton);
//        asksListAdapter= new AsksListAdapter(this,li);
//        recyclerView1.setAdapter(asksListAdapter);
//
//        havesListAdapter=new HavesListAdapter(this,li2);
//        recyclerView2.setAdapter(havesListAdapter);
//
//        eventId=new String();
//        b=new StringBuffer();
//        eventId=i.getStringExtra("ID");
//
//Toast.makeText(EventDetailsActivity.this,eventId,Toast.LENGTH_LONG).show();
//        db.collection("CreateEvent").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                          @Override
//                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                              List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                              for(DocumentSnapshot d:list)
//                                              {
//                                                    String id=d.getString("id");
//                                                    if(id.equals(eventId))
//                                                    {
//                                                        Event e=d.toObject(Event.class);
//                                                        b.append("Title : "+e.getTitle()+"\n\t\t\t "+e.getAgenda()+"\nDate : "+e.getDate()+"\nTime : "+e.getTime());
//                                                        t.setText(b.toString());
//
//                                                    }
//
//
//                                              }
//                                          }
//                                      }
//                );
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(EventDetailsActivity.this,FillData.class);
//                i.putExtra("ID",eventId);
//                startActivity(i);
//            }
//        });
//
//
//        db1.collection("UpdateEventInfo").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                          @Override
//                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                              List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                              for(DocumentSnapshot d:list)
//                                              {
//                                                  AsksListClass a=d.toObject(AsksListClass.class);
//                                                  if(a.getType().equals("Ask"))
//                                                        li.add(a);
//                                                  else
//                                                      li2.add(a);
//                                              }
//                                              asksListAdapter.notifyDataSetChanged();
//                                              havesListAdapter.notifyDataSetChanged();
//                                          }
//                                      }
//                );
//
//    }
}
