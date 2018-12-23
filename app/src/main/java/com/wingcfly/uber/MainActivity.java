package com.wingcfly.uber;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private Button btnSignIn, btnSignUp;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference users;
    private FirebaseAuth.AuthStateListener mAuthListener;

    RelativeLayout rootLayout;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        //Auto Login when having current account
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Intent welcome = new Intent(MainActivity.this, Welcome.class);
                    startActivity(welcome);
                    finish();
                    return;
                }
            }
        };

        //Init view
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnRegister);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        //Signup button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUpDialog();
            }
        });

        //Signin button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignInDialog();
            }
        });
    }

    private void showSignInDialog() {
        final AlertDialog.Builder signInDialog = new AlertDialog.Builder(this);
        signInDialog.setTitle("Đăng nhập");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signInLayout = inflater.inflate(R.layout.layout_signin, null);

        final MaterialEditText edtEmail = signInLayout.findViewById(R.id.edtEmail);
        final MaterialEditText edtPassword = signInLayout.findViewById(R.id.edtPassword);

        signInDialog.setView(signInLayout);

        signInDialog.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //disable button Sign In
                btnSignIn.setEnabled(false);

                final String email = edtEmail.getText().toString().trim();
                final String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Snackbar.make(rootLayout, "Hãy nhập địa chỉ Email", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Snackbar.make(rootLayout, "Hãy nhập mật khẩu", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (password.length() < 6) {
                    Snackbar.make(rootLayout, "Mật khẩu quá ngắn", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                final SpotsDialog alertDialog = new SpotsDialog(MainActivity.this);
                alertDialog.show();

                //Firebase login
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                alertDialog.dismiss();
                                Intent welcome = new Intent(MainActivity.this, Welcome.class);
                                startActivity(welcome);
                                finish();
                                return;
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                alertDialog.dismiss();
                                Snackbar.make(rootLayout, "Đăng nhập không thành công", Snackbar.LENGTH_SHORT)
                                        .show();
                                btnSignUp.setEnabled(true);
                            }
                        });
            }
        });

        signInDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        signInDialog.show();
    }

    private void showSignUpDialog() {
        final AlertDialog.Builder signUpDialog = new AlertDialog.Builder(this);
        signUpDialog.setTitle("Đăng ký");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signUpLayout = inflater.inflate(R.layout.layout_signup, null);

        final MaterialEditText edtEmail = signUpLayout.findViewById(R.id.edtEmail);
        final MaterialEditText edtPassword = signUpLayout.findViewById(R.id.edtPassword);
        final MaterialEditText edtName = signUpLayout.findViewById(R.id.edtName);
        final MaterialEditText edtPhone = signUpLayout.findViewById(R.id.edtPhone);

        signUpDialog.setView(signUpLayout);

        signUpDialog.setPositiveButton("Đăng ký", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                final String email = edtEmail.getText().toString().trim();
                final String password = edtPassword.getText().toString().trim();
                final String phone = edtPhone.getText().toString().trim();
                final String name = edtName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Snackbar.make(rootLayout, "Hãy nhập địa chỉ Email", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Snackbar.make(rootLayout, "Hãy nhập số điện thoại", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Snackbar.make(rootLayout, "Hãy nhập mật khẩu", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (password.length() < 6) {
                    Snackbar.make(rootLayout, "Mật khẩu quá ngắn", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(rootLayout, "Hãy nhập họ và tên", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User us = new User(email, phone, name);

                                FirebaseUser user = mAuth.getCurrentUser();
                                //add user to firebase database
                                users.child("Drivers").child(user.getUid())
                                        .setValue(us)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(rootLayout, "Đăng ký tài khoản thành công", Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout, "Đăng ký không thành công", Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout, "Đăng ký thất bại", Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });

        signUpDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        signUpDialog.show();
    }


}
