package com.application.saia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //Firebase auth
    private FirebaseAuth firebaseAuth;

    //View objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    //Database components
    private DatabaseReference userInfo;
//    private EditText editTextName, editTextAddress;
    private Button buttonManageVisitor;
    private Button buttonEditProfile;
    private Button buttonViewRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        userInfo = FirebaseDatabase.getInstance().getReference("User");

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

//        editTextName = (EditText)findViewById(R.id.editTextName);
//        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
//        buttonSave = (Button)findViewById(R.id.buttonSaveInfo);
        buttonManageVisitor = (Button)findViewById(R.id.buttonManageVisitor);
        buttonEditProfile = (Button)findViewById(R.id.buttonEditProfile);
        buttonViewRequest = (Button)findViewById(R.id.buttonViewRequest);

        textViewUserEmail = (TextView)findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button)findViewById(R.id.buttonLogout);

        // Listener
//        buttonSave.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        buttonEditProfile.setOnClickListener(this);
        buttonManageVisitor.setOnClickListener(this);
        buttonViewRequest.setOnClickListener(this);
        textViewUserEmail.setText("Welcome " + user.getEmail());
    }

//    private void saveUserInformation() {
//        String name = editTextName.getText().toString().trim();
//        String address = editTextAddress.getText().toString().trim();
//        String id = userInfo.push().getKey();
//
//        UserInformation userInformation = new UserInformation(id, name, address);
//
//        userInfo.child(id).setValue(userInformation);
//
//        Toast.makeText(this, "Information Saved...", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onClick(View view){
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if (view == buttonEditProfile) {
            finish();
            startActivity(new Intent(this, editProfileActivity.class));
        }

        if (view == buttonViewRequest) {
//            saveUserInformation();
            startActivity(new Intent(this, PushRequest.class));
        }

        if (view == buttonManageVisitor) {
            finish();
            startActivity(new Intent(this, ManageVisitors.class));
        }

    }
}
