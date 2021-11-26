package com.example.entreclub.Notification;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.entreclub.AdapterClassSec;
import com.example.entreclub.Home.HomeActivity1;
import com.example.entreclub.R;
import com.example.entreclub.utils.BottomNavigationViewHelper;
import com.example.entreclub.utils.BottomNavigationViewSecHelper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Alert extends AppCompatActivity {
    RecyclerView recyclerView;
    private Context mContext = Alert.this;
    private static final int ACTIVITY_NUM = 2;
    AdapterClassSec a;
    private FirebaseFirestore db;
    String emailid = new String();
    String items[]={"ABC posted for a first time in a wile","PQR liked your post","XYZ started followin you","PQR liked your post","PQR liked your post"};
    Vector<String> vname;
    //List<String> name = new ArrayList<String>(Arrays.asList(items));
    ArrayList<String> l  = new ArrayList<String>();
    AdapterClassSec adapter;
    //String[] arr = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_decline);
       // setupbottomnavigationview();
        final String[] s = new String[20];
        vname = new Vector<String>();
        db = FirebaseFirestore.getInstance();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        adapter = new AdapterClassSec(this,l.size(),l,emailid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterClassSec(this,l.size(),l,emailid));
        db.collection("Users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            int i = 0;
                            for (DocumentSnapshot d : list) {
                                Log.d("vedang1", "hello vedang");
                                //Requests_class r = d.toObject(Requests_class.class);
                                String flag = d.getString("flag");

                                if(flag.equals("0")) {
                                    String lname = d.getString("lastname");
                                    emailid = d.getString("emailid");
                                    String name = d.getString("firstname");
                                  //  String name = fname +" " + lname;
                                    l.add(i,name);
                                    i++;
                                }

                                //vname.add(sname);
                                //Log.d("hello", "onSuccess: ");
                                //arr[i] = sname;

                                //Toast.makeText(getApplicationContext(),"hello world"+l.get(i),Toast.LENGTH_LONG).show();
                                //Log.d("vedang1", l.get(i));


                            }
                            //arr = vname.toArray(new String[vname.size()]);
                            adapter.notifyDataSetChanged();
                        }
                        recyclerView.setAdapter(new AdapterClassSec(getApplicationContext(),l.size(),l,emailid));
                    }

                });//Toast.makeText(this,"stringarr:"+arr[0],Toast.LENGTH_LONG).show();
        setupbottomnavigationview();

    }





//    protected void onResume(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final String[] s = new String[20];
//        vname = new Vector<String>();
//        db = FirebaseFirestore.getInstance();
//        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//        adapter = new AdapterClassSec(this,l.size(),l);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new AdapterClassSec(this,l.size(),l));
//        db.collection("Users").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if(!queryDocumentSnapshots.isEmpty()) {
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            int i = 0;
//                            for (DocumentSnapshot d : list) {
//                                Log.d("vedang1", "hello vedang");
//                                //Requests_class r = d.toObject(Requests_class.class);
//                                String flag = d.getString("flag");
//
//                                if(flag.equals("0")) {
//                                    String lname = d.getString("lastname");
//                                    emailid = d.getString("emailid");
//                                    String fname = d.getString("firstname");
//                                    String name = fname +" " + lname;
//                                    l.add(i,name);
//                                    i++;
//                                }
//
//                                //vname.add(sname);
//                                //Log.d("hello", "onSuccess: ");
//                                //arr[i] = sname;
//
//                                //Toast.makeText(getApplicationContext(),"hello world"+l.get(i),Toast.LENGTH_LONG).show();
//                                //Log.d("vedang1", l.get(i));
//
//
//                            }
//                            //arr = vname.toArray(new String[vname.size()]);
//                            adapter.notifyDataSetChanged();
//                        }
//                        recyclerView.setAdapter(new AdapterClassSec(getApplicationContext(),l.size(),l));
//                    }
//
//                });//Toast.makeText(this,"stringarr:"+arr[0],Toast.LENGTH_LONG).show();
//
//    }



    //bottom navigation setup
    private void setupbottomnavigationview () {
      //  Log.d(TAG, "setupbottomnavigationview: Setting up Bottom Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
        BottomNavigationViewSecHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewSecHelper.enablenavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = ((Menu) menu).getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
    //bottom navigation setup

}



