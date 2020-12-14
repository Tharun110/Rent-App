package com.dynamicdudes.rentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Otpactivity extends AppCompatActivity {

    TextView edtPhoneNo;
    TextView lblinfo;
    Button no1,no2,no3,no4,no5,no6,no7,no8,no9,no0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        no1 = (Button) findViewById(R.id.btnOne);
        no2 = (Button) findViewById(R.id.btnTwo);
        no3 = (Button) findViewById(R.id.btnThree);
        no4 = (Button) findViewById(R.id.btnFour);
        no5 = (Button) findViewById(R.id.btnFive);
        no6 = (Button) findViewById(R.id.btnSix);
        no7 = (Button) findViewById(R.id.btnSeven);
        no8 = (Button) findViewById(R.id.btnEight);
        no9 = (Button) findViewById(R.id.btnNine);
        no0 = (Button) findViewById(R.id.btnZero);

        edtPhoneNo = (TextView) findViewById(R.id.edtPhoneNumber);
        lblinfo = (TextView) findViewById(R.id.lblinfo);
    }

    public void  isValidNumber(String phoneNumber){
        if(phoneNumber.length() >= 6){
            //Disable the button...
            Log.d("Ac","" + phoneNumber.length());
            no0.setClickable(false);
            no1.setClickable(false);
            no2.setClickable(false);
            no3.setClickable(false);
            no4.setClickable(false);
            no5.setClickable(false);
            no6.setClickable(false);
            no7.setClickable(false);
            no8.setClickable(false);
            no9.setClickable(false);

        }
        else{
            no0.setClickable(true);
            no1.setClickable(true);
            no2.setClickable(true);
            no3.setClickable(true);
            no4.setClickable(true);
            no5.setClickable(true);
            no6.setClickable(true);
            no7.setClickable(true);
            no8.setClickable(true);
            no9.setClickable(true);
        }
        Log.d("Ac","" + phoneNumber.length());
        Log.d("Acc",""+phoneNumber);

    }

    public void buttonClickEvent(View v) {
        String phoneNo = edtPhoneNo.getText().toString();
        try {
            switch (v.getId()) {
                case R.id.btnZero:
                    lblinfo.setText("");
                    phoneNo += "0";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnOne:
                    lblinfo.setText("");
                    phoneNo += "1";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnTwo:
                    lblinfo.setText("");
                    phoneNo += "2";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnThree:
                    lblinfo.setText("");
                    phoneNo += "3";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnFour:
                    lblinfo.setText("");
                    phoneNo += "4";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnFive:
                    lblinfo.setText("");
                    phoneNo += "5";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnSix:
                    lblinfo.setText("");
                    phoneNo += "6";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnSeven:
                    lblinfo.setText("");
                    phoneNo += "7";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnEight:
                    lblinfo.setText("");
                    phoneNo += "8";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btnNine:
                    lblinfo.setText("");
                    phoneNo += "9";
                    edtPhoneNo.setText(phoneNo);
                    isValidNumber(phoneNo);
                    break;
                case R.id.btndel:
                    lblinfo.setText("");
                    if (phoneNo != null && phoneNo.length() > 0) {
                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                        isValidNumber(phoneNo);
                    }

                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btnCall:
                    if (phoneNo.trim().equals("")) {
                        lblinfo.setText("Enter your Phone Number");
                    } else {
                        if(phoneNo.length() == 6){

                            Boolean isHash = false;
                            String callInfo = "tel:" + phoneNo;
                            Log.d("MainActivity"," Phone No" + callInfo + "Lenght" + phoneNo.length());
                            Intent intent = new Intent(Otpactivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            lblinfo.setText("Enter a Vaild Number");

                        }

                    }
                    break;
            }

        } catch (Exception ex) {

        }
    }

}