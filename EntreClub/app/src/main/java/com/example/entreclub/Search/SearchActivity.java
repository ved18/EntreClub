package com.example.entreclub.Search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.entreclub.R;
import com.example.entreclub.utils.BottomNavigationViewHelper;
import com.example.entreclub.utils.RecyclerViewAdapterSearch;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "searchactivity";
    private Context mContext = SearchActivity.this;
    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);
        Log.d(TAG, "onCreate: Started!");
        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        String services[] = {"Sakshi", "Nikhil", "Shruti", "Vedang", "Sagar", "Anuj", "Mallika"};
        // String des[] = {"Glide supports fetching, decoding, and displaying video stills, images, and animated GIFs. Glide includes a flexible API that allows developers to plug in to almost any network stack. By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug in to Google's Volley project or Square's OkHttp library instead.", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug "};
        recycler_view.setAdapter(new RecyclerViewAdapterSearch(services));
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
        setupbottomnavigationview();
    }


    //bottom navigation setup
    private void setupbottomnavigationview(){
        Log.d(TAG, "setupbottomnavigationview: Setting up Bottom Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enablenavigation(mContext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = ((Menu) menu).getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}