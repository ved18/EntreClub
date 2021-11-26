package com.example.entreclub.Home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.AsksListAdapter;
import com.example.entreclub.AsksListClass;
import com.example.entreclub.Event;
import com.example.entreclub.FillData;
import com.example.entreclub.HavesListAdapter;
import com.example.entreclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.DoubleConsumer;

public class EventDetails extends AppCompatActivity {

    FirebaseFirestore db,db1,dbget,getasks;
    TextView t;
    RecyclerView recyclerView1,recyclerView2;
    AsksListAdapter asksListAdapter;
    HavesListAdapter havesListAdapter;
    List<AsksListClass> asklist,havelist;
    ConstraintLayout c;
    StringBuffer b;

    //  Button btn;
    FloatingActionButton btn,btn2;
    Intent i;
    String eventId;
    DocumentReference dref;
    String title,agenda,platform,urls;
    Timestamp timestamp;
    StringBuffer stringBuffer;
    Date d;
    private  String TAG="Nikhil";
//    FirebaseUser user;
    String name;

    DocumentReference ref;
//    Calendar cal = Calendar.getInstance();
//    Intent intent = new Intent(Intent.ACTION_INSERT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details2);
        t = (TextView) findViewById(R.id.info);

        c=(ConstraintLayout)findViewById(R.id.c_layout);
       //c.setBackgroundColor(getResources().getColor(R.color.blue));
        db = FirebaseFirestore.getInstance();
        db1 = FirebaseFirestore.getInstance();
        getasks = FirebaseFirestore.getInstance();
        dbget = FirebaseFirestore.getInstance();
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        Button b3 = findViewById(R.id.button2);

////        b3.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {




////        intent.setType("vnd.android.cursor.item/event");
////     //   intent.putExtra("Start Time", cal.getTimeInMillis());
////        intent.putExtra("All day", true);
////        intent.putExtra("Rule", "FREQ=YEARLY");
////        intent.putExtra("End Time", cal.getTimeInMillis() + 60 * 60 * 1000);
////     //   intent.putExtra("Title", "Weekend brunch with all the Entrepreneurs from EntreClub");
////       // startActivity(intent);
//
//            }
//        });

        Button b4 = findViewById(R.id.button3);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        Intent sky = new Intent("android.intent.action.VIEW", Uri.parse("https://meet.google.com/krk-mpcb-mnq"));
        startActivity(sky);

            }
        });

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });

        stringBuffer = new StringBuffer();
       // final TextView t1 = (TextView) findViewById(R.id.t_link);
        asklist = new ArrayList<>();
        havelist = new ArrayList<>();
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        i = getIntent();
        btn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btn2 = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        asksListAdapter = new AsksListAdapter(this, asklist);
        recyclerView1.setAdapter(asksListAdapter);

        havesListAdapter = new HavesListAdapter(this, havelist);
        recyclerView2.setAdapter(havesListAdapter);

        eventId = new String();
        b = new StringBuffer();
        eventId = i.getStringExtra("ID");

      //  Toast.makeText(EventDetails.this, eventId, Toast.LENGTH_LONG).show();
        dref = db.collection("Events").document(eventId);

        dref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            title = documentSnapshot.getString("Title");
                            agenda = documentSnapshot.getString("Agenda");
                           timestamp = documentSnapshot.getTimestamp("Date");
                           d=timestamp.toDate();


//                            intent.putExtra("Title", "dcbjdsbsdcb");
//                            intent.putExtra("Start Time", d.toString());


                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                          String str_date = simpleDateFormat.format(d);

                            platform = documentSnapshot.getString("Platform");
                            urls = documentSnapshot.getString("Link");
                       //     t1.setText(urls);
                            stringBuffer.append("Title         : " + title + "\nDate        : "+str_date+"\nPlatform : "+platform);
                            t.setText(stringBuffer);
                        }
                    }
                });



        dref.collection("asks").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> list = task.getResult().getDocuments();
                            int i = 0;
////
////
                            for (DocumentSnapshot document : list) {
                               Log.d(TAG, document.getId() + " => " + document.getData());
                                AsksListClass a = new AsksListClass(document.getId(),document.getString("Description"));
//
//                                FirebaseUser user = FirebaseAuth.getInstance().g
//                                name = user.getDisplayName();


                                asklist.add(a);
                             //   Toast.makeText(getApplicationContext(),document.getString("Description"),Toast.LENGTH_SHORT);
                            }
                            asksListAdapter.notifyDataSetChanged();
//                                              havesListAdapter.notifyDataSetChanged();
                        } else {
                           // Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



        dref.collection("haves").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> list = task.getResult().getDocuments();
                            int i = 0;
////
////
                            for (DocumentSnapshot document : list) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                AsksListClass a = new AsksListClass(document.getId(),document.getString("Description"));
//
//                                FirebaseUser user = FirebaseAuth.getInstance().g
//                                name = user.getDisplayName();


                                havelist.add(a);
                                //   Toast.makeText(getApplicationContext(),document.getString("Description"),Toast.LENGTH_SHORT);
                            }
                            asksListAdapter.notifyDataSetChanged();
//                                              havesListAdapter.notifyDataSetChanged();
                        } else {
                            // Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
//
//        dref.collection("asks").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
////
////
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            int i = 0;
////
////
//                            for (DocumentSnapshot d : list) {
//                                AsksListClass a = d.toObject(AsksListClass.class);
////                                asklist.add(a);
//
//                                Toast.makeText(getApplicationContext(),a.getDescription(),Toast.LENGTH_SHORT);
//
//                            }
//
//
//
//
//                    }
//                });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventDetails.this, AskHave.class);
                i.putExtra("ID", eventId);
                startActivity(i);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sendInvite.class);
                 startActivity(intent);
            }
        });





        }


    public void setCalendar(View v) {

//        intent.setType("vnd.android.cursor.item/event");
//
//   intent.putExtra("All day", true);
// intent.putExtra("Rule", "FREQ=YEARLY");
//   intent.putExtra("End Time", cal.getTimeInMillis() + 60 * 60 * 1000);
//
// startActivity(intent);
//    }

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
              //  .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, d.getTime());
               // .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


        }
















































































//        getasks.collection("Events").document(eventId).collection("asks").get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(msg, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d(msg, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });


//
//        getasks.collection("Events").document(eventId).collection("asks").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                        if (!queryDocumentSnapshots.isEmpty()) {
////
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            int i = 0;
////
////
//                            for (DocumentSnapshot d : list) {
//                                AsksListClass a = d.toObject(AsksListClass.class);
////                                asklist.add(a);
//
//                                Toast.makeText(getApplicationContext(),a.getDescription(),Toast.LENGTH_SHORT);
//
//                            }
//
//                            asksListAdapter.notifyDataSetChanged();
//                            havesListAdapter.notifyDataSetChanged();
//


//       dbget.collection("Events");
//       db1 = dbget.document(eventId);
//              ref =   db.collection("Events").document(eventId).collection("asks").document();
//              ref.get()
//                      .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                          @Override
//                          public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                              if(documentSnapshot.exists()){
//
//                                  List<DocumentSnapshot> list=DocumentSnapshot.get
//                                  for(DocumentSnapshot d : list){
//
//                                  }
//                              }
//
//
//
//                          }
//                      });
//                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                          @Override
//                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                              List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                              for(DocumentSnapshot d:list)
//                                              {
//                                                  AsksListClass a=d.toObject(AsksListClass.class);
//                                                //  if(a.getType().equals("Ask"))
//                                                      asklist.add(a);
////                                                  else
////                                                      havelist.add(a);
//                                              }
//                                              asksListAdapter.notifyDataSetChanged();
//                                              havesListAdapter.notifyDataSetChanged();
//                                          }
//                                      }
//                );
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                          @Override
//                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                              List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                              for(DocumentSnapshot d:list)
//                                              {
//                                                  String id=d.getString("id");
//                                                  if(id.equals(eventId))
//                                                  {
//                                                      Event e=d.toObject(Event.class);
//                                                      b.append("Title : "+e.getTitle()+"\n\t\t\t "+e.getAgenda()+"\nDate : "+e.getDate()+"\nTime : "+e.getTime());
//                                                      t.setText(b.toString());
//
//                                                  }
//
//
//                                              }
//                                          }
//                                      }
//                );


//        db1.collection("UpdateEventInfo").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                          @Override
//                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                              List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                              for(DocumentSnapshot d:list)
//                                              {
//                                                  AsksListClass a=d.toObject(AsksListClass.class);
//                                                  if(a.getType().equals("Ask"))
//                                                      asklist.add(a);
//                                                  else
//                                                      havelist.add(a);
//                                              }
//                                              asksListAdapter.notifyDataSetChanged();
//                                              havesListAdapter.notifyDataSetChanged();
//                                          }
//                                      }
//                );
//
//    }

