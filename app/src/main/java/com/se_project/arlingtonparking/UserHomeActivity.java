package com.se_project.arlingtonparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserHomeActivity extends AppCompatActivity {
    Button view_profile_button, request_reservation_button, view_reserved_button,
            modify_reserved_button, cancel_reservation_button, view_no_shows_button, logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_home);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("key");

        view_profile_button = (Button) findViewById(R.id.view_profile_button);
        request_reservation_button = (Button) findViewById(R.id.request_reservation_button);
        view_reserved_button = (Button) findViewById(R.id.view_reserved_button);
        modify_reserved_button = (Button) findViewById(R.id.modify_reserved_button);
        cancel_reservation_button = (Button) findViewById(R.id.cancel_reservation_button);
        view_no_shows_button = (Button) findViewById(R.id.view_no_shows_button);
        logout_button = (Button) findViewById(R.id.logout_button);

        view_profile_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewProfileActivity.class);
                intent.putExtra("key", username);
                startActivity(intent);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logout",
                        Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
