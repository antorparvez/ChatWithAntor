package com.example.chatwithantor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private MaterialEditText uesername, email, pass;
    private Button reg_btn;


    FirebaseAuth auth ;
    DatabaseReference reference;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        uesername = findViewById(R.id.reg_username);
        email = findViewById(R.id.reg_email);
        pass = findViewById(R.id.reg_pass);
        reg_btn = findViewById(R.id.reg_btn);

        auth = FirebaseAuth.getInstance();

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reg_username = uesername.getText().toString();
                String reg_email = email.getText().toString();
                String reg_pass = pass.getText().toString();

                if(TextUtils.isEmpty(reg_username)||TextUtils.isEmpty(reg_email)||TextUtils.isEmpty(reg_pass)){

                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

                else if(reg_pass.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password must contain at least 6 character", Toast.LENGTH_SHORT).show();

                }

                else {
                    register(reg_username,reg_email,reg_pass);
                }
            }
        });

    }

    public void  register(final String username, String email, String pass){

        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("user").child(userid);
                    HashMap <String , String> hashMap = new HashMap<>();
                    hashMap.put("id" , userid);
                    hashMap.put("username" , username);
                    hashMap.put("imageURL" , "default");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class) ;
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
