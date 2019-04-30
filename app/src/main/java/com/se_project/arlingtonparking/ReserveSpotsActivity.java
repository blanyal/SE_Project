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

public class ReserveSpotsActivity extends AppCompatActivity {
    private EditText datetime;
    Button submit_button, return_button;
    private String new_username = "";

    private String username;
    private String password;
    private UserDatabase ub;
    private int type;
    private int options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_spots);

        Bundle extras = getIntent().getExtras();
        final String user_name = extras.getString("key");
        new_username = user_name;

        datetime = (EditText) findViewById(R.id.date);

        submit_button = (Button) findViewById(R.id.submit_button);
        return_button = (Button) findViewById(R.id.return_button);

        DateFormat df = new SimpleDateFormat("dd/MMMM/YYYY hh:mm:a");
        String date_string = df.format(Calendar.getInstance().getTime());

        datetime.setText(date_string);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReserveSpotsListActivity.class);
                ArrayList<String> extra = new ArrayList<>();
                extra.add(String.valueOf(type));
                extra.add(String.valueOf(options));
                extra.add(new_username);
                intent.putExtra("key", extra);
                startActivity(intent);
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
        Intent intent = new Intent(this, UserHomeActivity.class);
        intent.putExtra("key", new_username);
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

