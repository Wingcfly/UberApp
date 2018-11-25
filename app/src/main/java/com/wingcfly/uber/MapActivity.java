package com.wingcfly.uber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MapActivity extends AppCompatActivity {
    private Button btnLogout;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fbAuth = FirebaseAuth.getInstance();

        btnLogout = (Button) findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbAuth.signOut();
                Intent aLogin = new Intent(MapActivity.this, MainActivity.class);
                startActivity(aLogin);
                finish();
                return;
            }
        });
    }
}
