package com.example.entreclub.Home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.sax.StartElementListener;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Mom extends AppCompatActivity {
    private FirebaseFirestore db,db1;
    String event_id;
    Set<String> emails=new HashSet<String>();
    final Set<String> str1 = new HashSet<String>();
    String[] arr = new String[50];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mom);
        db = FirebaseFirestore.getInstance();
        db1 = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
     //   Bundle extras = intent.getExtras();
String extras;
        //Date t;
        //pass event_id in Intent from the parent activity
        event_id= intent.getStringExtra("event_id");

       /* db.collection("Events")
       /* db.collection("Events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                String doc_id_String = document.getId().toString();
                                if(doc_id_String.equals(event_id))
                                {
                                    TextView temp =  (TextView)findViewById(R.id.event_name);
                                    temp.setText(document.getString("Title"));
                                    Timestamp t = document.getTimestamp("Date");
                                    temp =  (TextView)findViewById(R.id.date);
                                    temp.setText(t.toDate().toString());
                                    break;
                                }
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        }
                        else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });*/
        //SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //Toast.makeText(MainActivity.this," rveryday" ,Toast.LENGTH_LONG).show();

        DocumentReference rf = db.collection("Events").document(event_id);
        rf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    TextView temp =  (TextView)findViewById(R.id.event_name);
                    temp.setText(documentSnapshot.get("Title").toString());
                    //temp =  (TextView)findViewById(R.id.date);
                  //  temp.setText(documentSnapshot.get("Date").toString());
                }
            }
        });


    }


    public void saveMom(View v)
    {
        EditText desc_decision= (EditText) findViewById(R.id.desc_decision);
        EditText desc_remarks= (EditText) findViewById(R.id.desc_remarks);
        EditText desc_amend= (EditText) findViewById(R.id.desc_amend);
        EditText desc_future_scope= (EditText) findViewById(R.id.desc_future_scope);
        //Toast.makeText(MainActivity.this,"Remarks " + desc_remarks.getText().toString().trim(),Toast.LENGTH_LONG).show();
        db  = FirebaseFirestore.getInstance();
        Map<String,Object> datatoSave = new HashMap<String, Object>();
        datatoSave.put("Decisions",desc_decision.getText().toString().trim());
        datatoSave.put("Futurescope",desc_future_scope.getText().toString().trim());
        datatoSave.put("Amendments",desc_amend.getText().toString().trim());
        datatoSave.put("Remarks",desc_remarks.getText().toString().trim());

        db.collection("Events").document(event_id)
                .set(datatoSave, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {
                        Toast.makeText(Mom.this,"Mom added successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Mom.this,"lol fail dyna",Toast.LENGTH_LONG).show();
                    }
                });
        TextView temp =  (TextView)findViewById(R.id.desc_decision);
        temp.setText("");
        temp = (TextView)findViewById(R.id.desc_future_scope);
        temp.setText("");
        temp = (TextView)findViewById(R.id.desc_amend);
        temp.setText("");
        temp = (TextView)findViewById(R.id.desc_remarks);
        temp.setText("");

         db.collection("Events").document(event_id).collection("Logs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                            String from = documentSnapshot.getString("from_users");
                            String to =  documentSnapshot.getString("to_user");

                            str1.add(from);
                            str1.add(to);
                        }

                        Log.d("nikhil ssss",Integer.toString(str1.size()));
                        Iterator value = str1.iterator();
                        Log.d("nikhil ss",Integer.toString(str1.size()));
                        int i=0;
                        for(String s:str1){

                            arr[i] = s;
                          //  Log.d("nikhil",arr[i]);
                            i++;
                        }
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, arr);
                        intent.putExtra(Intent.EXTRA_SUBJECT, "dugcsdj");
                        intent.putExtra(Intent.EXTRA_TEXT, "Added mom mail");


                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent, "Choose a Client"));

                    }
                });
        Log.d("nikhil ss11",Integer.toString(str1.size()));
                        //sendMails();

    }



    public void sendMails(){
        Iterator value = str1.iterator();
        Log.d("nikhil ss",Integer.toString(str1.size()));
        int i=0;
        while(value.hasNext())
        {

            arr[i] = value.next().toString();
            Log.d("nikhil",arr[i]);
            i++;
        }



        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, arr);
        intent.putExtra(Intent.EXTRA_SUBJECT, "dugcsdj");
        intent.putExtra(Intent.EXTRA_TEXT, "Added mom mail");


        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose a Client"));

    }








}








