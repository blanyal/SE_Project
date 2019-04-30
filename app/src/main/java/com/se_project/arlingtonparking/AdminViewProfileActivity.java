package com.se_project.arlingtonparking;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminViewProfileActivity extends AppCompatActivity {

    private TextView usernameText, passwordText, lastnText, firstnText, roleText, uta_idText,
            phoneText, emailText, addressText, cityText, stateText, zipText, licenseText, dobText,
            permitText, carText, reservationText, noshowText;

    Button update, return_button, revoke_button;

    private String new_username = "";
    private String last_name = "";
    private int role = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_admin_view_profile);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> extra = extras.getStringArrayList("key");
        final String user_name = extra.get(1);
        final String lastn = extra.get(0);
        final String user_username = extra.get(2);

        new_username = user_name;
        last_name = lastn;

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
        reservationText = findViewById(R.id.reservation);
        noshowText = findViewById(R.id.noshow);
        update = (Button) findViewById(R.id.update_button);
        return_button = (Button) findViewById(R.id.return_button);
        revoke_button = (Button) findViewById(R.id.revoke_button);

        UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        UserDao userDAO = rb.userDao();

        user = userDAO.getUser(user_username);
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

        role = user.getRole();

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
        reservationText.setText("Is Revoked: " + user.isReservation());
        noshowText.setText("Is No Show: " + user.isNo_show());

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminUpdateProfileActivity.class);
                ArrayList<String> extra = new ArrayList<>();
                extra.add(last_name);
                extra.add(new_username);
                extra.add(user.getUsername());
                intent.putExtra("key", extra);
                startActivity(intent);
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        revoke_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                        UserDatabase.class, "database-name").allowMainThreadQueries().build();

                UserDao userDAO = rb.userDao();

                User user = userDAO.getUser(user_username);
                user.setReservation(true);

                userDAO.update(user);

                reservationText.setText("Is Revoked: " + user.isReservation());

                // Create toast to confirm new user
                Toast.makeText(getApplicationContext(), "Revoked User",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SearchUsersResultsActivity.class);
        ArrayList<String> extra = new ArrayList<>();
        extra.add(last_name);
        extra.add(new_username);
        intent.putExtra("key", extra);
        startActivity(intent);

    }
}
