package com.example.da1firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button logout;
    private EditText name;
    private EditText contact;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        add = findViewById(R.id.add);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name = name.getText().toString();
                String txt_contact = contact.getText().toString();
                if (TextUtils.isEmpty(txt_name) || TextUtils.isEmpty(txt_contact)) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", txt_name);
                    map.put("contact", txt_contact);
                    FirebaseDatabase.getInstance("https://da1-firebase-82111-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("19BIT0382").child("DA1").updateChildren(map);
                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}