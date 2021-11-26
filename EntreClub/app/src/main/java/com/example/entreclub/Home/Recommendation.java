package com.example.entreclub.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.entreclub.AdapterClass;
import com.example.entreclub.FeedSecretary;
import com.example.entreclub.R;
import com.example.entreclub.utils.BottomNavigationViewHelper;
import com.example.entreclub.utils.RecyclerViewAdapterRecom;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class Recommendation extends AppCompatActivity {
    private Context mContext;
    private static final int ACTIVITY_NUM = 0;
    private static final String TAG = "Recommendation";
    RecyclerView recyclerView;
    AdapterClass adapterClass;
    List<FeedSecretary> li;
    private FirebaseFirestore db;
    DocumentReference documentReference;
   // public int flag = 0;
    // final Button testButton = (Button) findViewById(R.id.follow);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);




        li = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterClass = new AdapterClass(this, li);
        recyclerView.setAdapter(adapterClass);


//        RecyclerView recycler_view = findViewById(R.id.recycler_view);
//        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//        String services[] = {"Sakshi", "Nikhil", "Shruti", "Vedang", "Sagar", "Anuj", "Mallika"};
//        // String des[] = {"Glide supports fetching, decoding, and displaying video stills, images, and animated GIFs. Glide includes a flexible API that allows developers to plug in to almost any network stack. By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug in to Google's Volley project or Square's OkHttp library instead.", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug "};
//        recycler_view.setAdapter(new RecyclerViewAdapterRecom(services));
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
    }

      /*  testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag != 0) {

                    testButton.setText("Follow");
                    flag = 0;
                } else {


                    testButton.setText("Following");
                    flag = 1;
                }
            }

            ;
        });

*/
        private void setupbottomnavigationview(){
            Log.d(TAG, "setupbottomnavigationview: Setting up Bottom Navigation View");
            BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
            BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
            BottomNavigationViewHelper.enablenavigation(mContext, bottomNavigationViewEx);
            Menu menu = bottomNavigationViewEx.getMenu();
            MenuItem menuItem = ((Menu) menu).getItem(ACTIVITY_NUM);
            menuItem.setChecked(true);
        }

    }
