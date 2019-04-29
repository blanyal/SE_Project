package com.se_project.arlingtonparking;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView usernameText, passwordText, lastnText, firstnText, roleText, uta_idText,
            phoneText, emailText, addressText, cityText, stateText, zipText, licenseText, dobText,
            permitText, carText;

    Button update;

    private String new_username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_profile);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        final String user_name = extras.getString("key");

        new_username = user_name;

        usernameText = findViewById(R.id.username);
        lastnText = findViewById(R.id.lastn);
        firstnText = findViewById(R.id.firstn);
        roleText = findViewById(R.id.role);
        uta_idText = findViewById(R.id.uta_id);
        phoneText = findViewById(R.id.phone);
        emailText = findViewById(R.id.email);
        addressText = findViewById(R.id.address);
        cityText = findViewById(R.id.city);
        stateText = findViewById(R.id.state);
        zipText = findViewById(R.id.zip);
        licenseText = findViewById(R.id.license);
        dobText = findViewById(R.id.dob);
        permitText = findViewById(R.id.permit);
        carText = findViewById(R.id.car);
        update = (Button) findViewById(R.id.update_button);

        UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        UserDao userDAO = rb.userDao();

        User user = userDAO.getUser(user_name);
        usernameText.setText("Username: " + user.getUsername());
        lastnText.setText("Last Name: " + user.getLastn());
        firstnText.setText("First Name: " + user.getFirstn());

        if (user.getRole() == 1) {
            roleText.setText("Role: User");
        } else if (user.getRole() == 2) {
            roleText.setText("Role: Manager");
        } else {
            roleText.setText("Role: Admin");
        }

        uta_idText.setText("UTA ID: " + String.valueOf(user.getUta_id()));
        phoneText.setText("Phone: " + String.valueOf(user.getPhone()));
        emailText.setText("Email: " + user.getEmail());
        addressText.setText("Street: " + user.getAddress());
        cityText.setText("City: " + user.getCity());
        stateText.setText("State: " + user.getState());
        zipText.setText("Zip: " + String.valueOf(user.getZip()));
        licenseText.setText("License: " + user.getLicense());
        dobText.setText("Date of Birth: " + user.getDob());
        permitText.setText("Permit: " + user.getPermit());
        carText.setText("Car: " + user.getCar());

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateProfileActivity.class);
                intent.putExtra("key", user_name);
                startActivity(intent);
            }
        });

    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, UserHomeActivity.class);
        intent.putExtra("key", new_username);
        startActivity(intent);
    }
}
