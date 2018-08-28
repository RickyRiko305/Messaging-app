package com.example.asus.chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class poopActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button SaveChangesButton;
    private EditText StatusInput;
    private ProgressDialog loadingBar;

    private DatabaseReference ChangeStatusRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poop);

        mToolbar = (Toolbar) findViewById(R.id.all_users_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Users");

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        ChangeStatusRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

//        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.status_app_bar);
//        getSupportActionBar().setTitle("Change Status");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SaveChangesButton = (Button) findViewById(R.id.save_change_status_button);
        StatusInput = (EditText) findViewById(R.id.status_input);
        loadingBar = new ProgressDialog(this);

//        String old_status = getIntent().getExtras().get("user_status1").toString();
//        StatusInput.setText(old_status);

        SaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                String new_status = StatusInput.getText().toString();

                ChangeProfileStatus(new_status);
            }
        });
    }

    private void ChangeProfileStatus(String new_status) {
        if(TextUtils.isEmpty(new_status)){
            Toast.makeText(poopActivity.this, "Please write your status..", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Change Profile Status");
            loadingBar.setMessage("Please wait, while we are updating your profile status...");
            loadingBar.show();

            ChangeStatusRef.child("user_status").setValue(new_status)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                loadingBar.dismiss();

                                Intent settingIntent = new Intent(poopActivity.this, SettingsActivity.class);
                                startActivity(settingIntent);
                                Toast.makeText(poopActivity.this, "Status is updated", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(poopActivity.this, "error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
