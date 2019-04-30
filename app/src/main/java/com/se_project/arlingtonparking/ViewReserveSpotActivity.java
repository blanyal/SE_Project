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

public class ViewReserveSpotActivity extends AppCompatActivity {

    private TextView id, area, datetime, typetext, camera, cart, history;

    Button reserve_button, return_button;

    private String new_username = "";
    private int type_string = 1;
    private int options_string = 1;
    private int role = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_reserve_spot);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> extra = extras.getStringArrayList("key");
        final int type = Integer.parseInt(extra.get(0));
        final int options = Integer.parseInt(extra.get(1));
        final int uid = Integer.parseInt(extra.get(2));
        final String user_name = extra.get(3);

        new_username = user_name;
        type_string = type;
        options_string = options;

        id = findViewById(R.id.id);
        area = findViewById(R.id.area);
        datetime = findViewById(R.id.datetime);
        typetext = findViewById(R.id.type);
        camera = findViewById(R.id.camera);
        cart = findViewById(R.id.cart);
        history = findViewById(R.id.history);

        reserve_button = (Button) findViewById(R.id.reserve_button);
        return_button = (Button) findViewById(R.id.return_button);

        UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        ReservationDao reservationDaoDAO = rb.reservationDao();

        Reservation reservation = reservationDaoDAO.getReservation(uid);
        id.setText("Reservation ID: " + String.valueOf(reservation.getUid()));
        area.setText("Area: " + reservation.getArea());
        datetime.setText("Date Time: " + reservation.getDatetime());

        if (reservation.getType() == 1) {
            typetext.setText("Type: Basic");
        } else if (reservation.getType() == 2) {
            typetext.setText("Type: Premium");
        } else if (reservation.getType() == 3) {
            typetext.setText("Type: Access");
        } else {
            typetext.setText("Type: Midrange");
        }

        if (options_string == 1) {
            camera.setText("Camera: Yes");
        } else if (options_string == 2) {
            cart.setText("Cart: Yes");
        } else {
            history.setText("History: Yes");
        }

        reserve_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                        UserDatabase.class, "database-name").allowMainThreadQueries().build();

                ReservationDao reservationDaoDAO = rb.reservationDao();

                Reservation reservation = reservationDaoDAO.getReservation(uid);

                reservation.setReserved(true);
                reservation.setUsername(user_name);
                reservation.setOptions(options_string);

                reservationDaoDAO.update(reservation);

                Toast.makeText(getApplicationContext(), "Reservation Confirmed",
                        Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, ReserveSpotsListActivity.class);
        ArrayList<String> extra = new ArrayList<>();
        extra.add(String.valueOf(type_string));
        extra.add(String.valueOf(options_string));
        extra.add(new_username);
        intent.putExtra("key", extra);
        startActivity(intent);
    }
}
