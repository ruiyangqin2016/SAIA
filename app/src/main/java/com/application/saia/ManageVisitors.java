package com.application.saia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class ManageVisitors extends AppCompatActivity implements View.OnClickListener {

    //Database components
    private EditText editTextVisitorName;
    private EditText editTextVisitorDescription;
    private EditText editTextVisitorPhone;
    private EditText editTextVisitorAddress;
    private Button buttonUploadVisitorPic;
    private Button buttonSaveVisitor;
    private Button buttonBackMain;
    private DatabaseReference visitorInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_visitors);

        //firebase
        visitorInfo = FirebaseDatabase.getInstance().getReference("Visitor");

        //find by id
        editTextVisitorName = (EditText)findViewById(R.id.editTextVisitorName);
        editTextVisitorDescription = (EditText)findViewById(R.id.editTextVisitorDescription);
        editTextVisitorPhone = (EditText)findViewById(R.id.editTextVisitorPhone);
        editTextVisitorAddress = (EditText)findViewById(R.id.editTextVisitorAddress);
        buttonSaveVisitor = (Button)findViewById(R.id.buttonSaveVisitor);
        buttonBackMain = (Button)findViewById(R.id.buttonBackToMainPage1);
        buttonUploadVisitorPic = (Button)findViewById(R.id.buttonUploadVisitorPic);

        //Listener
        buttonBackMain.setOnClickListener(this);
        buttonSaveVisitor.setOnClickListener(this);
        buttonUploadVisitorPic.setOnClickListener(this);
    }

    public void saveVisitorInfo() {
        String name = editTextVisitorName.getText().toString().trim();
        String description = editTextVisitorDescription.getText().toString().trim();
        String address = editTextVisitorAddress.getText().toString().trim();
        String phone = editTextVisitorPhone.getText().toString().trim();
        String id = visitorInfo.push().getKey();

        Visitor visitor = new Visitor(id, name, description, phone, address);
        visitorInfo.child(id).setValue(visitor);
        Toast.makeText(this, "Information Saved...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view){
        if (view == buttonSaveVisitor) {
            saveVisitorInfo();
        }

        if (view == buttonUploadVisitorPic) {
            finish();
            startActivity(new Intent(this, RequestActivity.class));
        }

        if (view == buttonBackMain) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}

