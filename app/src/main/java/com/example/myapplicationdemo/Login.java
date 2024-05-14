package com.example.myapplicationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                    String role = null;

                if (user.equals("client") && pass.equals("1234") /*&& role.equals("client")*/) {
                    Toast.makeText(Login.this, "Login Successful as Client!", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(Login.this, Home_Menu.class));
                    finish();
                } else if (user.equals("seller") && pass.equals("1234") /*&& role.equals("seller")*/) {
                    Toast.makeText(Login.this, "Login Successful as Seller!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Home_Menu.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

/*
    @SuppressLint("NewApi")
    public Connection connectionClass() {
        Connection con = null;
        String ip = "172.1.0.1", port = "1433", username = "OMAR", password = "", databaseName = "Annonces_DB";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databaseName=" + databaseName + ";User=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception exception) {
            Log.e("Error", Objects.requireNonNull(exception.getMessage()));
        }
        return con;
    }
*/

