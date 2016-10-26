package com.zenbarrier.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    boolean isLogInMode = true;
    EditText editUsername, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ParseUser.getCurrentUser() != null){
            userListActivity();
        }

        Utilities.setKeyboardHideOnClick(this, findViewById(R.id.activity_main));

        editUsername = (EditText) findViewById(R.id.editTextUsername);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void logInOrSignUp(View view){
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        if(isLogInMode){
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e!=null){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userListActivity();
                    }
                }
            });
        }
        else{
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e!=null){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userListActivity();
                    }
                }
            });
        }
    }

    public void switchModeLog(View view){
        Button buttonMode = (Button) findViewById(R.id.buttonModeLog);
        Button buttonLogIn = (Button) findViewById(R.id.buttonLogIn);

        if(isLogInMode){
            isLogInMode = false;
            buttonLogIn.setText(getString(R.string.sign_up_button));
            buttonMode.setText(getString(R.string.log_in_button));
        }
        else{
            isLogInMode = true;
            buttonLogIn.setText(getString(R.string.log_in_button));
            buttonMode.setText(getString(R.string.sign_up_button));
        }
    }

    void userListActivity(){
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }

}
