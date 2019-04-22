package com.example.sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login;
    private Button exit;
    private SharedPreferences info;
    private String log;
    private final String SAVE_LOGIN = "save login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login_output);
        exit = findViewById(R.id.exit);

        exit.setOnClickListener(this);

        info = getSharedPreferences("login", MODE_PRIVATE);
        log = info.getString(SAVE_LOGIN, "");

        login.setText("Your login: " + log);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:

                getSharedPreferences("login", 0).edit().clear().commit();
                getSharedPreferences("password", 0).edit().clear().commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
}
