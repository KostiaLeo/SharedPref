package com.example.sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText login, password;
    Button login_btn;
    SharedPreferences info, info1;

    final String SAVE_LOGIN = "save login";
    final String SAVE_PASS = "save pass";

    String login_info, pass_info, savedPass, savedLog;


//----------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

        info = getSharedPreferences("password", MODE_PRIVATE);
        savedPass = info.getString(SAVE_PASS, "");
        info1 = getSharedPreferences("login", MODE_PRIVATE);
        savedLog = info1.getString(SAVE_LOGIN, "");

        if (savedLog.length() != 0 && savedPass.length() != 0){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

//-----------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                log_pass();
                break;
        }
    }

//-----------------------------------------------------------------------------------

    private void log_pass() {

        login_info = login.getText().toString();
        pass_info = password.getText().toString();

        if (pass_info.length() != 0 && login_info.length() != 0) {
            info = getSharedPreferences("password", MODE_PRIVATE);
            SharedPreferences.Editor edP = info.edit();
            edP.putString(SAVE_PASS, pass_info);
            edP.commit();

            info1 = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor edL = info1.edit();
            edL.putString(SAVE_LOGIN, login_info);
            edL.commit();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "Пропущен логин или пароль!", Toast.LENGTH_SHORT).show();
        }
    }
}
