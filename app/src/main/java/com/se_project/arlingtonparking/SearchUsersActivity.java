package com.se_project.arlingtonparking;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchUsersActivity extends AppCompatActivity {
    private EditText lastn;
    Button submit_button, return_button;
    private String new_username = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_users);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        final String user_name = extras.getString("key");

        lastn = (EditText) findViewById(R.id.lastn);
        submit_button = (Button) findViewById(R.id.submit_button);
        return_button = (Button) findViewById(R.id.return_button);

        new_username = user_name;

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchUsersResultsActivity.class);
                ArrayList<String> extra = new ArrayList<>();
                extra.add(lastn.getText().toString());
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
            Intent intent = new Intent(this, AdminHomeActivity.class);
            intent.putExtra("key", new_username);
            startActivity(intent);
    }

}
