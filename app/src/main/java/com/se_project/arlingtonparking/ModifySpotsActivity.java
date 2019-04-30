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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ModifySpotsActivity extends AppCompatActivity {
    private EditText datetime;
    Button submit_button, return_button;
    private String new_username = "";
    private int id = 0;

    private String username;
    private String password;
    private UserDatabase ub;
    private int type;
    private int options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_spots);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> extra = extras.getStringArrayList("key");

        final int uid = Integer.parseInt(extra.get(0));
        final String user_name = extra.get(1);

        id = uid;
        new_username = user_name;

        datetime = (EditText) findViewById(R.id.date);

        submit_button = (Button) findViewById(R.id.submit_button);
        return_button = (Button) findViewById(R.id.return_button);

        RadioButton radio_basic = (RadioButton) findViewById(R.id.radio_basic);
        RadioButton radio_premium = (RadioButton) findViewById(R.id.radio_premium);
        RadioButton radio_access = (RadioButton) findViewById(R.id.radio_access);
        RadioButton radio_midrange = (RadioButton) findViewById(R.id.radio_midrange);

        RadioButton radio_cart = (RadioButton) findViewById(R.id.radio_cart);
        RadioButton radio_camera = (RadioButton) findViewById(R.id.radio_camera);
        RadioButton radio_history = (RadioButton) findViewById(R.id.radio_history);

        UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        ReservationDao reservationDaoDAO = rb.reservationDao();

        Reservation reservation = reservationDaoDAO.getReservation(uid);

        datetime.setText(reservation.getDatetime());

        if (reservation.getType() == 1) {
            radio_basic.setChecked(true);
        } else if (reservation.getType() == 2) {
            radio_premium.setChecked(true);
        } else if (reservation.getType() == 3) {
            radio_access.setChecked(true);
        } else {
            radio_midrange.setChecked(true);
        }

        if (reservation.getOptions() == 1) {
            radio_cart.setChecked(true);
        } else if (reservation.getOptions() == 2) {
            radio_camera.setChecked(true);
        } else {
            radio_history.setChecked(true);
        }

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                        UserDatabase.class, "database-name").allowMainThreadQueries().build();

                ReservationDao reservationDaoDAO = rb.reservationDao();

                Reservation reservation = reservationDaoDAO.getReservation(uid);

                reservation.setDatetime(datetime.getText().toString());
                reservation.setType(type);
                reservation.setOptions(options);

                reservationDaoDAO.update(reservation);

                Toast.makeText(getApplicationContext(), "Reservation Updated",
                        Toast.LENGTH_SHORT).show();


                onBackPressed();
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ViewReservedActivity.class);
        ArrayList<String> extra = new ArrayList<>();
        extra.add(String.valueOf(id));
        extra.add(new_username);

        intent.putExtra("key", extra);
        startActivity(intent);
    }


    public void onRadioButtonClicked1(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_basic:
                if (checked) {
                    type = 1;
                    break;
                }

            case R.id.radio_premium:
                if (checked) {
                    type = 2;
                    break;
                }

            case R.id.radio_access:
                if (checked) {
                    type = 3;
                    break;
                }

            case R.id.radio_midrange:
                if (checked) {
                    type = 4;
                    break;
                }
        }
    }

    public void onRadioButtonClicked2(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_cart:
                if (checked) {
                    options = 1;
                    break;
                }

            case R.id.radio_camera:
                if (checked) {
                    options = 2;
                    break;
                }

            case R.id.radio_history:
                if (checked) {
                    options = 3;
                    break;
                }
        }
    }
}

