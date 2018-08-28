package com.example.asus.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String visit_user_id = getIntent().getExtras().get("visit_user_id").toString();

        Toast.makeText(ProfileActivity.this, visit_user_id, Toast.LENGTH_SHORT).show();
    }
}
