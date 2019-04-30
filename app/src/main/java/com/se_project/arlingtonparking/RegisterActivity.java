package com.se_project.arlingtonparking;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText, passwordText, lastnText, firstnText, roleText, uta_idText,
            phoneText, emailText, addressText, cityText, stateText, zipText, licenseText, dobText,
            permitText, carText;

    Button register;

    private String username;
    private String password;
    private String lastn;
    private String firstn;
    private int role;
    private int uta_id;
    private int phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String license;
    private String dob;
    private String permit;
    private String car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        lastnText = (EditText) findViewById(R.id.lastn);
        firstnText = (EditText) findViewById(R.id.firstn);
        role = 1;
        uta_idText = (EditText) findViewById(R.id.uta_id);
        phoneText = (EditText) findViewById(R.id.phone);
        emailText = (EditText) findViewById(R.id.email);
        addressText = (EditText) findViewById(R.id.address);
        cityText = (EditText) findViewById(R.id.city);
        stateText = (EditText) findViewById(R.id.state);
        zipText = (EditText) findViewById(R.id.zip);
        licenseText = (EditText) findViewById(R.id.license);
        dobText = (EditText) findViewById(R.id.dob);
        permitText = (EditText) findViewById(R.id.permit);
        carText = (EditText) findViewById(R.id.car);
        register = (Button) findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                        UserDatabase.class, "database-name").allowMainThreadQueries().build();

                UserDao userDAO = rb.userDao();

                username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                lastn = lastnText.getText().toString();
                firstn = firstnText.getText().toString();
                uta_id = Integer.parseInt(uta_idText.getText().toString());
                phone = Integer.parseInt(phoneText.getText().toString());
                email = emailText.getText().toString();
                address = addressText.getText().toString();
                city = cityText.getText().toString();
                state = stateText.getText().toString();
                zip = Integer.parseInt(zipText.getText().toString());
                license = licenseText.getText().toString();
                dob = dobText.getText().toString();
                permit = permitText.getText().toString();
                car = carText.getText().toString();

                // Creating user
                User user = new User(username, password, lastn, firstn, role,
                        uta_id, phone, email, address, city,
                        state, zip, license, dob, permit, car, false, false);

                userDAO.insert(user);

                // Create toast to confirm new user
                Toast.makeText(getApplicationContext(), "Registration successful",
                        Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_user:
                if (checked) {
                    role = 1;
                    break;
                }

            case R.id.radio_manager:
                if (checked) {
                    role = 2;
                    break;
                }

            case R.id.radio_admin:
                if (checked) {
                    role = 3;
                    break;
                }
        }
    }


    // On pressing the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
