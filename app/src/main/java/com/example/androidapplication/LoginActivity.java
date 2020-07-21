package com.example.androidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button btn_login;
    FirebaseAuth auth;

    TextView create_account;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        btn_login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        create_account = findViewById(R.id.create_account);

        layout = findViewById(R.id.login_layout);

        btn_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt_email = email.getText().toString();
                        String txt_pass = pass.getText().toString();
                        if(TextUtils.isEmpty(txt_email)){
//                            Toast.makeText(LoginActivity.this,"Enter Your Email ID",Toast.LENGTH_LONG).show();
                            Snackbar.make(layout, "Enter Your Email ID",Snackbar.LENGTH_LONG)
                                    .setAction("Close", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

                        } else if(!Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
//                            Toast.makeText(LoginActivity.this,"Enter a valid Email ID",Toast.LENGTH_LONG).show();
                            Snackbar.make(layout, "Enter a valid Email ID",Snackbar.LENGTH_LONG)
                                    .setAction("Close", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

                        } else if(TextUtils.isEmpty(txt_pass)){
//                            Toast.makeText(LoginActivity.this,"Enter Your Password",Toast.LENGTH_LONG).show();
                            Snackbar.make(layout, "Enter Your Password",Snackbar.LENGTH_LONG)
                                    .setAction("Close", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();

                        } else{
                            auth.signInWithEmailAndPassword(txt_email,txt_pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                       @Override
                                       public void onComplete(@NonNull Task<AuthResult> task) {
                                           if(task.isSuccessful())
                                           {
                                               Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                               startActivity(intent);
                                               finish();
                                           }else{
//                                               Toast.makeText(LoginActivity.this,"Authentication Failed!",Toast.LENGTH_LONG).show();
                                               Snackbar.make(layout, "Authentication Failed!",Snackbar.LENGTH_LONG)
                                                       .setAction("Close", new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {

                                                           }
                                                       })
                                                       .setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
                                           }
                                       }
                                   }
                            );
                        }
                    }
                }
        );

        create_account.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    }
                }
        );
    }
}
