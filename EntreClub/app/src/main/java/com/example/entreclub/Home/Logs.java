package com.example.entreclub.Home;



        import android.util.Log;

public class Logs {
    String from_user,to_user;
    String amount;

    public Logs(String from_user,String to_user,String amount) {
        this.from_user=from_user;
        this.to_user = to_user;
        this.amount = amount;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}



