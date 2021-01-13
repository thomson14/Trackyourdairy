package com.example.trackyourdairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    MaterialTextField emailL,passwordL;
    Button btn_loginL;
    FirebaseAuth auth;
    ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginProgress = findViewById(R.id.loginProgress);

        emailL = (MaterialTextField) findViewById(R.id.emailL);
        passwordL = (MaterialTextField) findViewById(R.id.passwordL);

        btn_loginL = (Button) findViewById(R.id.btn_loginL);
        auth = FirebaseAuth.getInstance();
        Log.d("btn_Login", "L:"+btn_loginL);
        btn_loginL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btn_Login", "L:"+btn_loginL);
                loginProgress.setVisibility(View.VISIBLE);
                String txt_email = emailL.getEditText().getText().toString();
                String txt_password = passwordL.getEditText().getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Login.this,"All Fields Are Requierds",Toast.LENGTH_SHORT).show();

                }else {

                    auth.signInWithEmailAndPassword(txt_email,txt_password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        loginProgress.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(Login.this, Search.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(Login.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}
