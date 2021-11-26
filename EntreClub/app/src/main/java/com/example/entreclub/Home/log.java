package com.example.entreclub.Home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.entreclub.Logl;
import com.example.entreclub.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class log extends AppCompatActivity {

    static String[] SPACESHIPS = { "No.","From", "To", "Service", "Count" };
//    String[][] data;
//
//static final String[][] data = {{ "1","Casini", "Chemical", "NASA", "Jupiter" }, {"2", "Casini", "Chemical", "NASA", "Jupiter" }, { "3","Casini", "Chemical", "NASA", "Jupiter" }};
    Intent intent;
    static String data[][] = new String[500][500];
    int i=0;
    ArrayList<String> d1,d2,d3,d4,d5;
    FirebaseFirestore db;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_log);
        intent = getIntent();
        s = intent.getStringExtra("ID");
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();        db = FirebaseFirestore.getInstance();

        db.collection("Events").document(s).collection("Logs").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        if(!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                         int i=0;

                         int lol=1;
                            for(DocumentSnapshot d : list){
                                int j=0;
                                String amt = d.getString("amount");
                                String service = d.getString("service_consumed");
                                String from = d.getString("from_user");
                                String to = d.getString("to_user");
                                Logl  li  = new Logl(service,from,to,amt);
                                data[i][j++] = Integer.toString(i+1);
                                //Log.d("print",d1);
                                data[i][j++] = from;
                                data[i][j++] = to;
                                data[i][j++] = service;
                                data[i][j++] = amt;
                                //Log.d("hello ,oto", data[i][j--]);
                                lol++;
                          //      l.add(i,li);
                                i++;

                            }

                        }

                    }
                });


        TableView<String[]>  tableView = (TableView<String[]>) findViewById(R.id.tableView);
        tableView.setHeaderBackgroundColor(Color.parseColor("#76287c"));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this,SPACESHIPS));
        tableView.setColumnCount(5);
        tableView.setDataAdapter(new SimpleTableDataAdapter(this,data));


    }
}