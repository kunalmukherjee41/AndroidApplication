package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password,rPassword;
    Button createUser;
    TextView login;
    LinearLayout layout;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        layout = findViewById(R.id.register_layout);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rPassword = findViewById(R.id.rPassword);
        createUser = findViewById(R.id.create_user);
        login = findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        createUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        registerUser();
                    }
                }
        );

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }
                }
        );

    }

    private void registerUser() {

        final String txt_name = name.getText().toString();
        final String txt_email = email.getText().toString();
        String txt_password = password.getText().toString();
        String txt_rPassword = rPassword.getText().toString();

        if(TextUtils.isEmpty(txt_name)){
//            Toast.makeText(RegisterActivity.this, "Enter the Name to Create Account", Toast.LENGTH_LONG).show();
            Snackbar.make(layout, "Enter the Name to Create Account",Snackbar.LENGTH_LONG)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

        } else if(TextUtils.isEmpty(txt_email)){
//            Toast.makeText(RegisterActivity.this, "Enter the Email to Create Account", Toast.LENGTH_LONG).show();
            Snackbar.make(layout, "Enter the Email to Create Account",Snackbar.LENGTH_LONG)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

        } else if(!Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()){
//            Toast.makeText(RegisterActivity.this, "Enter a valid Email Address", Toast.LENGTH_LONG).show();
            Snackbar.make(layout, "Enter a valid Email Address",Snackbar.LENGTH_LONG)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

        } else if(TextUtils.isEmpty(txt_password)){
//            Toast.makeText(RegisterActivity.this, "Set Your password", Toast.LENGTH_LONG).show();
            Snackbar.make(layout, "Set Your password",Snackbar.LENGTH_LONG)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

        } else if(TextUtils.isEmpty(txt_rPassword)){
//            Toast.makeText(RegisterActivity.this, "ReEnter Your Password", Toast.LENGTH_LONG).show();
            Snackbar.make(layout, "ReEnter Your Password",Snackbar.LENGTH_LONG)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

        } else if(!txt_password.equals(txt_rPassword)){
//            Toast.makeText(RegisterActivity.this, "Passwords are not match", Toast.LENGTH_LONG).show();
            Snackbar.make(layout, "Passwords are not match",Snackbar.LENGTH_LONG)
                    .setAction("Close", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

        } else {

            auth.createUserWithEmailAndPassword(txt_email,txt_password)
                    .addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){

                                        FirebaseUser firebaseUser = auth.getCurrentUser();

                                        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("name", txt_name);
                                        hashMap.put("email", txt_email);

                                        reference.setValue(hashMap).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                        }
                                                    }
                                                }
                                        );

                                    }
                                }
                            }
                    );

        }

    }
}