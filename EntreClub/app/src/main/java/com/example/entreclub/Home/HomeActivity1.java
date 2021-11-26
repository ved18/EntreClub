package com.example.entreclub.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.entreclub.AdapterClass;
import com.example.entreclub.AdapterClassSec;
import com.example.entreclub.FeedSecretary;
import com.example.entreclub.R;
import com.example.entreclub.utils.BottomNavigationViewHelper;
import com.example.entreclub.utils.BottomNavigationViewSecHelper;
import com.example.entreclub.utils.UniversalImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
//public class HomeActivity extends AppCompatActivity {
//
//    private String Agenda,amendments,date,decision,future_scope,title,link,remarks,amount,id,d;
//
//    RecyclerView recyclerView;
//    AdapterClass adapterClass;
//    List<FeedSecretary> li;
//    private FirebaseFirestore db;
//    DocumentReference documentReference;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upcomming_events);
//        db=FirebaseFirestore.getInstance();
//        documentReference = db.collection("Events").document("1");
//
//        li=new ArrayList<>();
//        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapterClass= new AdapterClass(this,li);
//        recyclerView.setAdapter(adapterClass);
//
//        documentReference.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.exists()){
//
//                          //  if(int i=0;i<2;)
//
//                            Agenda = documentSnapshot.getString("Agenda");
//                            title = documentSnapshot.getString("Title");
//                            date = documentSnapshot.getString("Date");
////                            d = date.toString();
//                            id = documentSnapshot.getString("id");
//
//                            FeedSecretary feedSecretary = new FeedSecretary(title,d,Agenda,id);
//                            li.add(feedSecretary);
//
//
//                            adapterClass.notifyDataSetChanged();
//
//
//
//
//                        }
//                    }
//                });
//        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                          @Override
//                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                              List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                              for(DocumentSnapshot d:list)
//                                              {
//                                                  Event e=d.toObject(Event.class);
//                                                  li.add(e);
//                                              }
//                                              adapterClass.notifyDataSetChanged();
//                                          }
//                                      }
//                );
//    }






//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
////import android.widget.Toolbar;
//import android.support.v7.widget.Toolbar;
//import com.example.entreclub.R;
//import com.example.entreclub.Registration.RegistrationActivity;
//import com.example.entreclub.utils.BottomNavigationViewHelper;
//import com.example.entreclub.utils.RecyclerViewAdapter;
//import com.example.entreclub.utils.UniversalImageLoader;
//import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
//import com.nostra13.universalimageloader.core.ImageLoader;
//

//Responsible for Recommendation,Messages,Icon

public class HomeActivity1 extends AppCompatActivity {
    private Context mContext = HomeActivity1.this;
    private static final int ACTIVITY_NUM = 0;
    private static final String TAG = "HomeActivity";
    private Button b;
    private String Agenda, amendments, decision, future_scope, str,title, link, remarks, amount, id, d;

    RecyclerView recyclerView;
    AdapterClass adapterClassSec;
    Date date;

    List<FeedSecretary> li;
    private FirebaseFirestore db;
    DocumentReference documentReference;
    Timestamp timestamp;
    private ImageButton b1;
    private ImageButton b2;
    DrawerLayout drawerlayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_activity_homepage);


//        b1 = (ImageButton)findViewById(R.id.imageButton);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(HomeActivity.this,Recommendation.class);
//                startActivity(i);
//            }
//        });


        Log.d(TAG, "onCreate: Starting");
        initImageLoader();
        setUpToolbar();
        setupbottomnavigationview();
//       // setupviewpager();


        db = FirebaseFirestore.getInstance();


        li = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterClassSec = new AdapterClass(this, li);
        recyclerView.setAdapter(adapterClassSec);

        db.collection("Events").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            int i=0;
                            for (DocumentSnapshot d : list){

                                Agenda = d.getString("Agenda");
                                title = d.getString("Title");
                                timestamp = d.getTimestamp("Date");
                                date = timestamp.toDate();
                               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


                                str = simpleDateFormat.format(date);
////                            d = date.toString();
                                id = d.getId().toString();
                                FeedSecretary feedSecretary = new FeedSecretary(title, str, Agenda,id );
                                li.add(feedSecretary);


                                adapterClassSec.notifyDataSetChanged();
                                i++;

                            }
                        }
                    }
                });

//        documentReference.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (documentSnapshot.exists()) {
//
//                            //  if(int i=0;i<2;)
//
//
//                            Agenda = documentSnapshot.getString("Agenda");
//                            title = documentSnapshot.getString("Title");
//                            date = documentSnapshot.getString("Date");
////                            d = date.toString();
//                            id = documentSnapshot.getString("id");
//
//                            FeedSecretary feedSecretary = new FeedSecretary(title, d, Agenda, id);
//                            li.add(feedSecretary);
//
//
//                            adapterClass.notifyDataSetChanged();
//
//
//                        }
//                    }
//                });
    }
//        RecyclerView recycler_view = findViewById(R.id.recycler_view);
//        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//        String services[] = {"Sakshi","Nikhil","Shruti","Vedang","Sagar","Anuj","Mallika"};
//        String des[] = {"Glide supports fetching, decoding, and displaying video stills, images, and animated GIFs. Glide includes a flexible API that allows developers to plug in to almost any network stack. By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug in to Google's Volley project or Square's OkHttp library instead.","By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ","By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ","By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ","By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ","By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ","By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug "};
//        recycler_view.setAdapter(new RecyclerViewAdapter(services));
//
//
//
//    }
//
        private void initImageLoader () {
            UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
            ImageLoader.getInstance().init(universalImageLoader.getConfig());
        }

        private void setUpToolbar () {

            drawerlayout = findViewById(R.id.drawer);
            toolbar = findViewById(R.id.toolbar);
            navigationView = findViewById(R.id.navigation_view);
            setSupportActionBar(toolbar);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.app_name, R.string.app_name);
            drawerlayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.past_events:
                            Intent i = new Intent(HomeActivity1.this, PastEventsActivity.class);
                            startActivity(i);
                            break;
                        case R.id.about:
                            Intent k = new Intent(HomeActivity1.this, Recommendation.class);
                            startActivity(k);
                            break;
                        case R.id.members:
                            Intent j = new Intent(HomeActivity1.this, Recommendation.class);
                            startActivity(j);
                            break;
                        case R.id.signout:
                            Intent l = new Intent(HomeActivity1.this, MainActivity.class);
                            startActivity(l);

                    }
                    return false;
                }
            });
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (drawerlayout.isDrawerOpen(Gravity.RIGHT)) {
//                    drawerlayout.closeDrawer(Gravity.RIGHT);
//                } else {
//                    drawerlayout.openDrawer(Gravity.RIGHT);
//                }
//            }
//        });

        }
        ;
        //bottom navigation setup
        private void setupbottomnavigationview () {
            Log.d(TAG, "setupbottomnavigationview: Setting up Bottom Navigation View");
            BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
            BottomNavigationViewSecHelper.setupBottomNavigationView(bottomNavigationViewEx);
            BottomNavigationViewSecHelper.enablenavigation(mContext, bottomNavigationViewEx);
            Menu menu = bottomNavigationViewEx.getMenu();
            MenuItem menuItem = ((Menu) menu).getItem(ACTIVITY_NUM);
            menuItem.setChecked(true);

        }

    }

