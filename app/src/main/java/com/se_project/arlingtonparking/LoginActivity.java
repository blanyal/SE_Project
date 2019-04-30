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

