package com.example.entreclub.Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.entreclub.R;
import com.example.entreclub.utils.RecyclerViewAdapterViewposts;

public class ViewPosts extends AppCompatActivity {
    private Context mContext;
    private static final int ACTIVITY_NUM = 4;
    private static final String TAG = "Posts";
    // public int flag = 0;
    // final Button testButton = (Button) findViewById(R.id.follow);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewposts);
        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        String services[] = {"Sakshi", "Nikhil", "Shruti", "Vedang", "Sagar", "Anuj", "Mallika"};
        // String des[] = {"Glide supports fetching, decoding, and displaying video stills, images, and animated GIFs. Glide includes a flexible API that allows developers to plug in to almost any network stack. By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug in to Google's Volley project or Square's OkHttp library instead.", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug ", "By default Glide uses a custom HttpUrlConnection based stack, but also includes utility libraries plug "};
        recycler_view.setAdapter(new RecyclerViewAdapterViewposts(services));
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


}
