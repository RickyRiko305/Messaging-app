package com.example.asus.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startPageActivity extends AppCompatActivity {

    private Button NeedNewAccount;
    private Button AlreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        NeedNewAccount = (Button) findViewById(R.id.need_account_button);
        AlreadyHaveAccount = (Button) findViewById(R.id.already_have_account_button);

        NeedNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent RegisterIntent = new Intent(startPageActivity.this, RegisterActivity.class);
                startActivity(RegisterIntent);
            }
        });

        AlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(startPageActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
