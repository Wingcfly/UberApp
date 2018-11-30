package com.wingcfly.uber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnDriver, btnCustomer;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCustomer = (Button)findViewById(R.id.customers);
        btnDriver = (Button)findViewById(R.id.drivers);

        mAuth = FirebaseAuth.getInstance();

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aCustomer = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(aCustomer);
                finish();
                return;
            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aDriver = new Intent(MainActivity.this, DriverLoginActivity.class);
                startActivity(aDriver);
                finish();
                return;
            }
        });
    }
}
