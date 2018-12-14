package com.wingcfly.uber;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn, btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                            .setDefaultFontPath("fonts/Arkhip_font.ttf")
                                            .setFontAttrId(R.attr.fontPath)
                                            .build());


        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnRegister);

//        btnCustomer = (Button)findViewById(R.id.customers);
//        btnDriver = (Button)findViewById(R.id.drivers);

//        mAuth = FirebaseAuth.getInstance();

//        btnCustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent aCustomer = new Intent(MainActivity.this, CustomerLoginActivity.class);
//                startActivity(aCustomer);
//                finish();
//                return;
//            }
//        });
//
//        btnDriver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent aDriver = new Intent(MainActivity.this, DriverLoginActivity.class);
//                startActivity(aDriver);
//                finish();
//                return;
//            }
//        });
    }
}
