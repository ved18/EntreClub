package com.example.entreclub.Home;




import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entreclub.R;
import com.google.api.Context;

import java.util.Calendar;

public class sendInvite extends AppCompatActivity {

    private EditText editTextTo, editTextSubject, editTextMessage;
    String str;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_invite);

        intent = getIntent();
        //Bundle bundle = new Bundle();
        str= intent.getStringExtra("URL");

        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT);

        editTextTo = findViewById(R.id.editText4);
        editTextSubject = findViewById(R.id.editText7);
        editTextMessage = findViewById(R.id.editText14);


        editTextSubject.setText("Guest Invite");
        editTextMessage.setText("Hey! I'm inviting you to this event.\nPlease have a look and do join on the platform specified in the URL below -\n" +str);



        Button sendButton = findViewById(R.id.button2);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

//        Button add = findViewById(R.id.addEvent);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addEvent();
//            }
//        });
//
//        Button hangouts = findViewById(R.id.hangouts);
//
//        hangouts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getHangouts();
//            }
//        });
//
//        Button whatsapp =  findViewById(R.id.whatsapp);
//
//        whatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shareOnWhatsapp();
//            }
//        });


    }


    public void sendMail() {
        String recipientList = editTextTo.getText().toString();
        String[] recipients = recipientList.split(",");


        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);


        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose a Client"));
    }

//
//    public void addEvent() {
//
//        Calendar cal = Calendar.getInstance();
//        Intent intent = new Intent(Intent.ACTION_EDIT);
//        intent.setType("vnd.android.cursor.item/event");
//        intent.putExtra("Start Time", cal.getTimeInMillis());
//        intent.putExtra("All day", true);
//        intent.putExtra("Rule", "FREQ=YEARLY");
//        intent.putExtra("End Time", cal.getTimeInMillis() + 60 * 60 * 1000);
//        intent.putExtra("Title", "Weekend brunch with all the Entrepreneurs from EntreClub");
//        startActivity(intent);
//    }
//
//
//    /////////////////////////////////////////Share on hangout
//
//    public void shareOnWhatsapp() {
////
////        Intent intent = new Intent();
////        intent.setAction(Intent.ACTION_VIEW);
//
//// the _ids you save goes here at the end of /data/12562
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
//        startActivity(sendIntent);
//
//
//
//    }
//
//
//    public void getHangouts(){
//
//
////        Intent sky = new Intent("android.intent.action.VIEW", Uri.parse("https://meet.google.com/krk-mpcb-mnq"));
////        startActivity(sky);
//
//    }
//


//
//    public void getHangouts(Context myContext, String textToShare) {
//
////        // Make sure Android client is installed.
////        if (!isHangOutClientInstalled(myContext)) {
////            goToMarket(myContext);
////            return;
////        }
//
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SCREEN_ON);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.google.android.talk");
//        startActivity(sendIntent);
//
//        return;
//    }

//    public void getHangouts(){
//
//        Intent sky = new Intent("android.intent.action.VIEW");
//        sky.setData(Uri.parse("skype:" + "nik   "));
//        startActivity(sky);
//    }
//}
}