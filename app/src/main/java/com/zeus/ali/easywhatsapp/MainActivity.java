package com.zeus.ali.easywhatsapp;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ImageButton infobutton;
    Button button;
    EditText phone;
    EditText messageText;
    private String urlLink;
    String phvalue, msvalue;
    CountryCodePicker ccp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        infobutton = findViewById(R.id.imageButton);
        phone = findViewById(R.id.phone);
        messageText = findViewById(R.id.messageText);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phone);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels ,height = dm.heightPixels;
        //getWindow().setLayout((int)(width*.7),(int)(height*.5));
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout((int)(width*.5),ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            getWindow().setElevation(5.0f);
            getWindow().setDimAmount(.5f);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phvalue = ccp.getFullNumber();
                msvalue = messageText.getText().toString();
                urlLink = "https://api.whatsapp.com/send?phone="+phvalue+"&text="+msvalue;

                //Log.e(TAG, "whatsappurl " + urlLink);
                if (TextUtils.isEmpty(phone.getText())) {
                    Toasty.error(getApplicationContext(),
                            "Where's the phone number?",
                            Toast.LENGTH_SHORT, true).show();
                }
                else if (TextUtils.isEmpty(messageText.getText())) {
                    Toasty.error(getApplicationContext(),
                            "Type some message!",
                            Toast.LENGTH_SHORT, true).show();
                }
                else {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlLink)));}
            }
        });

        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toasty.info(getApplicationContext(),
                        "Choose country code, type phone number & message and click whatsapp. Simple!",
                        Toast.LENGTH_LONG, true).show();
            }
        });

    }

}
