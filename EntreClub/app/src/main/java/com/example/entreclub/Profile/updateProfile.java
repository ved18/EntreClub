package com.example.entreclub.Profile;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entreclub.R;
import com.example.entreclub.utils.BottomNavigationViewHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

//import com.bumptech.glide.Glide;

public class updateProfile extends AppCompatActivity {
//
   private static  final String key_firstname = "firstname";
    private static final String key_lastname = "lastname";
    private static final String key_emailid = "emailid";
    private static final String key_contact = "contact";
    private static final String key_city = "city";
    private static final String key_password = "password";
    private static final String key_dob = "dob";
    private static final String key_gender = "gender";
    private static final String key_companyname = "companyname";
    private static final String key_position = "position";
    private static final String key_description = "description";
    private static final int ACTIVITY_NUM = 2;
    private static final int image_Request =1;
    private Button choose;
    private ImageView imageView;
    private ProgressBar progressBar;
    Uri uri;
    Context mcontext;

    private EditText editTextfirstname,editTextlastname,editTextemailid;
    private EditText editTextcontact,editTextcity;
    private EditText editTextcompanyname,editTextposition,editTextdescription;
    private String firstname,lastname,emailid,contact,city,dob,password,cpassword,companyname,position,description;
   // editText.setText("Google is your friend.", TextView.BufferType.EDITABLE);
    private FirebaseFirestore db;
    private DocumentReference dbref;
    private StorageReference storageReference;
    private StorageReference getUrl;
    private DocumentReference documentReference;
    Uri downloadUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        emailid = extras.getString("emailid");
      //  Toast.makeText(getApplicationContext(),"email id " +emailid,Toast.LENGTH_SHORT).show();



        FirebaseApp.initializeApp(this);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        emailid = mauth.getCurrentUser().getEmail();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        getUrl = FirebaseStorage.getInstance().getReference().child(emailid + ".jpg");
        documentReference = db.collection("Users").document(emailid);
        dbref = db.collection("Users").document(emailid);

        setupbottomnavigationview();



        editTextfirstname = findViewById(R.id.firstname);
        editTextlastname = findViewById(R.id.lastname);
     //   editTextemailid = findViewById(R.id.username);
        editTextcontact = findViewById(R.id.contact);
        editTextcity = findViewById(R.id.city);
        editTextcompanyname = findViewById(R.id.company);
        editTextposition = findViewById(R.id.position);
        editTextdescription = findViewById(R.id.description);

        choose = findViewById(R.id.button);
        imageView = findViewById(R.id.profile_photo);
        progressBar = findViewById(R.id.progressbar);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseFile();

            }
        });



        getProfileDetails();

    }


    public void chooseFile(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,image_Request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == image_Request && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();

            Picasso.with(this).load(uri).into(imageView);
        }
    }

    public void getProfileDetails(){
        dbref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                             firstname = documentSnapshot.getString(key_firstname);
                             lastname = documentSnapshot.getString(key_lastname);
                          //   emailid = documentSnapshot.getString(key_emailid);
                             contact = documentSnapshot.getString(key_contact);
                             city = documentSnapshot.getString(key_city);
                             companyname = documentSnapshot.getString(key_companyname);
                             position = documentSnapshot.getString(key_position);
                             description = documentSnapshot.getString(key_description);

                           //  downloadUri = (Uri) getUrl.getDownloadUrl();
                          //  Glide.with(getApplicationContext()).load("https://www.google.com/imgres?imgurl=https%3A%2F%2Fd1kkg0o175tdyf.cloudfront.net%2Flarge%2Fp_9cd2339d2f74-2018-10-30-17-35-18-000216.jpg&imgrefurl=https%3A%2F%2Fmagicpin.in%2Fusers%2F871367&docid=e4-_KrF4jjr65M&tbnid=Q977tU2t-IcMQM%3A&vet=10ahUKEwirwMTwgt7gAhUHRY8KHVeTASUQMwg8KAEwAQ..i&w=512&h=512&bih=785&biw=1482&q=nikhil%20narwade&ved=0ahUKEwirwMTwgt7gAhUHRY8KHVeTASUQMwg8KAEwAQ&iact=mrc&uact=8").into(imageView);

//                            Picasso.with(getApplicationContext()).load("https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi1mtfp6tzgAhVDiHAKHcdYC4QQjRx6BAgBEAU&url=https%3A%2F%2Fmagicpin.in%2Fusers%2F871367&psig=AOvVaw0b1SZHrX6igcUeq_Gd4qfQ&ust=1551388531558344").into(imageView);
                        //    editTextemailid.setText(emailid, TextView.BufferType.EDITABLE);
                            editTextfirstname.setText(firstname, TextView.BufferType.EDITABLE);
                            editTextlastname.setText(lastname, TextView.BufferType.EDITABLE);
                            editTextcontact.setText(contact, TextView.BufferType.EDITABLE);
                            editTextcity.setText(city, TextView.BufferType.EDITABLE);
                            editTextcompanyname.setText(companyname, TextView.BufferType.EDITABLE);
                            editTextposition.setText(position, TextView.BufferType.EDITABLE);
                            editTextdescription.setText(description, TextView.BufferType.EDITABLE);

//
//                            storageReference.child("uploads/" + emailid + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri urii) {

                                    // Got the download URL for 'users/me/profile.png'
//                Uri downloadUri = taskSnapshot.getMetadata().getDownloadUrl();
//                generatedFilePath = downloadUri.toString(); /// The string(file link) that you need


//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception exception) {
//                                    // Handle any errors
//                                }
//                            });

//                            Uri downloadUri = documentReference.getPath("url");
                           // Picasso.with(getApplicationContext()).load(uri).into(imageView);

                            //Toast.makeText(updateProfile.this, "firstname : "+putfirstname, Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(updateProfile.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });





    }




    public boolean validate(){
        if(firstname.isEmpty()){
            editTextfirstname.setError("Firstname required!");
            editTextfirstname.requestFocus();
            return false;
        }

        if(lastname.isEmpty()){
            editTextlastname.setError("Lastname required!");
            editTextlastname.requestFocus();
            return false;
        }

        if(emailid.isEmpty()){
            editTextemailid.setError("Email id is required!");
            editTextemailid.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
            editTextemailid.setError("Please enter a valid mail id!");
            editTextemailid.requestFocus();
            return false;
        }

        if(contact.isEmpty()){
            editTextcontact.setError("Contact required!");
            editTextcontact.requestFocus();
            return false;
        }

        if(city.isEmpty()){
            editTextcity.setError("City required!");
            editTextcity.requestFocus();
            return false;
        }

        if(companyname.isEmpty()){
            editTextcompanyname.setError("Company required!");
            editTextcompanyname.requestFocus();
            return false;
        }

        if(position.isEmpty()){
            editTextposition.setError("Position in the company required!");
            editTextposition.requestFocus();
            return false;
        }

        if(description.isEmpty()){
            editTextdescription.setError("Please provide profile description!");
            editTextdescription.requestFocus();
            return false;
        }

        return true;

    }

    public String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void updateProfilePicture(){

        if(uri!=null) {
            StorageReference fileRef = storageReference.child(emailid + "." + getFileExtension(uri));
            fileRef.putFile(uri);



//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBar.setProgress(0);
//
//                                }
//                            },500);
//
////                            Upload upload =new Upload(taskSnapshot.getUploadSessionUri().toString());
//////                            documentReference.collection("Users").document(emailid).set(upload);
////                              documentReference.update("url",upload);
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(updateProfile.this,e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                   // double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                   // progressBar.setProgress((int) progress);
//
//                }
//            });
//
//
//        }
////            final StorageReference fileReference = storageReference.child("User_uploads/" + emailid + "." + getFileExtension(uri));
////            fileReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressBar.setProgress(0);
//                        }
//                    },500);
//
//                    UploadTask uploadTask = fileReference.putFile(uri);
//                    documentReference.set(uploadTask);
//
//
//                }
//            });
//        }
        }
    }

    public void updateProfile(View v){
        firstname = editTextfirstname.getText().toString().trim();
        lastname = editTextlastname.getText().toString().trim();
        emailid = editTextemailid.getText().toString().trim();
        contact = editTextcontact.getText().toString().trim();
        city = editTextcity.getText().toString().trim();
        companyname = editTextcompanyname.getText().toString().trim();
        position = editTextposition.getText().toString().trim();
        description = editTextdescription.getText().toString().trim();





        if(validate()){


            Map<String, Object> entrepreneur = new HashMap<>();
            entrepreneur.put(key_firstname,firstname);
            entrepreneur.put(key_lastname,lastname);
            entrepreneur.put(key_emailid,emailid);
            entrepreneur.put(key_contact,contact);
            entrepreneur.put(key_city,city);
            entrepreneur.put(key_companyname,companyname);
            entrepreneur.put(key_position,position);
            entrepreneur.put(key_description,description);



            updateProfilePicture();
           // dbref.set(entrepreneur, SetOptions.merge());
            dbref.update(entrepreneur).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Toast.makeText(updateProfile.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(updateProfile.this, "Could not update profile, please try again!", Toast.LENGTH_SHORT).show();

                        }
                    });




    }
}


    private void setupbottomnavigationview(){
        //Log.d(TAG, "setupbottomnavigationview: Setting up Bottom Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomnav);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enablenavigation(mcontext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = ((Menu) menu).getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}

