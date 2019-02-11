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

public class editProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference userInfo;
    private EditText editTextName, editTextAddress;
    private Button buttonSave;
    private Button buttonBackMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //firebase
        userInfo = FirebaseDatabase.getInstance().getReference("User");

        //find by id
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        buttonSave = (Button)findViewById(R.id.buttonSaveInfo);
        buttonBackMain = (Button)findViewById(R.id.buttonBackToMainPage2);

        //listener
        buttonSave.setOnClickListener(this);
        buttonBackMain.setOnClickListener(this);
    }

    private void saveUserInformation() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String id = userInfo.push().getKey();

        UserInformation userInformation = new UserInformation(id, name, address);

//        FirebaseUser user = firebaseAuth.getCurrentUser();

        userInfo.child(id).setValue(userInformation);

        Toast.makeText(this, "Information Saved...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view){
        if (view == buttonSave) {
            saveUserInformation();
        }

        if (view == buttonBackMain) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
