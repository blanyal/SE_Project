package com.se_project.arlingtonparking;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText usernameText, passwordText;
    Button login, register;

    private String username;
    private String password;
    private UserDatabase ub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login_in_button);
        register = (Button) findViewById(R.id.register_button);

        UserDatabase rv = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        ReservationDao reservationDAO = rv.reservationDao();

        List<Reservation> reservations = reservationDAO.getAll();

        if (reservations.isEmpty()) {
            reservationDAO.insert(new Reservation(0, "30/April/2019 01:00:PM", 1,
                    2, "", false, false, false,"West Garage"));

            reservationDAO.insert(new Reservation(1, "30/April/2019 02:00:PM", 1,
                    2, "", false, false, false, "Maverick"));

            reservationDAO.insert(new Reservation(2, "30/April/2019 03:00:PM", 2,
                    2, "", false, false, false,"Davis"));

            reservationDAO.insert(new Reservation(3, "30/April/2019 04:00:PM", 2,
                    2, "", false, false, false,"West Garage"));

            reservationDAO.insert(new Reservation(4, "30/April/2019 05:00:PM", 2,
                    2, "", false, false, false,"Maverick"));

            reservationDAO.insert(new Reservation(5, "30/April/2019 06:00:PM", 2,
                    2, "", false, false, false,"Nedderman"));

            reservationDAO.insert(new Reservation(6, "30/April/2019 07:00:PM", 2,
                    2, "", false, false, false,"West Garage"));

            reservationDAO.insert(new Reservation(7, "30/April/2019 08:00:PM", 3,
                    2, "", false, false, false,"Maverick"));

            reservationDAO.insert(new Reservation(8, "30/April/2019 09:00:PM", 3,
                    2, "", false, false, false,"Davis"));

            reservationDAO.insert(new Reservation(9, "30/April/2019 10:00:PM", 4,
                    2, "", false, false, false,"Nedderman"));

            reservationDAO.insert(new Reservation(10, "30/April/2019 11:00:PM", 4,
                    2, "", false, false, false,"Davis"));

        }

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                username = usernameText.getText().toString();
                password = passwordText.getText().toString();

                UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                        UserDatabase.class, "database-name").allowMainThreadQueries().build();

                UserDao itemDAO = rb.userDao();
                User user = itemDAO.getUser(username);

                if (password.equals(user.getPassword())) {
                    Toast.makeText(getApplicationContext(), "Login Success",
                            Toast.LENGTH_SHORT).show();

                    if (user.getRole() == 1) {
                        Intent intent = new Intent(v.getContext(), UserHomeActivity.class);
                        intent.putExtra("key", user.getUsername());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(v.getContext(), AdminHomeActivity.class);
                        intent.putExtra("key", user.getUsername());
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

