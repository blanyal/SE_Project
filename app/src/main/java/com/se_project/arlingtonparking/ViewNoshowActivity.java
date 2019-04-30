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

public class ViewNoshowActivity extends AppCompatActivity {

    private TextView id, area, datetime, typetext, camera, cart, history;

    Button return_button;

    private String new_username = "";
    private int uid_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_noshow);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> extra = extras.getStringArrayList("key");

        final int uid = Integer.parseInt(extra.get(0));
        final String user_name = extra.get(1);

        new_username = user_name;
        uid_id = uid;

        id = findViewById(R.id.id);
        area = findViewById(R.id.area);
        datetime = findViewById(R.id.datetime);
        typetext = findViewById(R.id.type);
        camera = findViewById(R.id.camera);
        cart = findViewById(R.id.cart);
        history = findViewById(R.id.history);

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

        if (reservation.getOptions() == 1) {
            camera.setText("Camera: Yes");
        } else if (reservation.getOptions() == 2) {
            cart.setText("Cart: Yes");
        } else {
            history.setText("History: Yes");
        }


        return_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, NoshowListActivity.class);
        intent.putExtra("key", new_username);
        startActivity(intent);
    }
}
