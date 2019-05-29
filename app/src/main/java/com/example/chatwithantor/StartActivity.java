package com.example.chatwithantor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button login_main, register_main;
    FirebaseUser firebaseUser;
/*

    <?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#17AFF3</color>
    <color name="colorPrimaryDark">#1060A0</color>
    <color name="colorAccent">#D81B60</color>
    <color name="White">#FFFFFF</color>
</resources>

*/



    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser != null){

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            Intent intent = new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        login_main = findViewById( R.id.login_main);
        register_main = findViewById( R.id.register_main);

        login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,LoginActivity.class));
            }
        });

        register_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            }
        });
    }
}
