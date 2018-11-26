package com.wingcfly.uber;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLoginActivity extends AppCompatActivity {
    private Button btnLogin, btnRegis;
    private EditText edtEmail, edtPass;

    private FirebaseAuth fbAuth;
    private FirebaseAuth.AuthStateListener fbAuthListenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        fbAuth = FirebaseAuth.getInstance();

        fbAuthListenter = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent aMap = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                    startActivity(aMap);
                    finish();
                    return;
                }
            }
        };

        btnLogin = (Button) findViewById(R.id.dlogin);
        btnRegis = (Button) findViewById(R.id.dregistration);

        edtEmail = (EditText) findViewById(R.id.demail);
        edtPass = (EditText) findViewById(R.id.dpassword);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString().trim();
                final String password = edtPass.getText().toString().trim();
                fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            String userID = fbAuth.getCurrentUser().getUid();
                            DatabaseReference newUser = FirebaseDatabase.getInstance().getReference().child("Drivers").child(userID);
                            newUser.child("Email").setValue(email);
                            //multiple fields
//                            newUser.child("Name").setValue("ABC");
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString().trim();
                final String password = edtPass.getText().toString().trim();
                fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(fbAuthListenter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fbAuth.removeAuthStateListener(fbAuthListenter);
    }
}