package com.example.ta_pam;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnDaftar){
            Intent i = new Intent(getApplicationContext(), formpendaftaran.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    //deklarasi variabel untuk button
    Button btnLogin;

    //deklarasi variabel untuk EditText
    private EditText edemail, edpassword;

    //deklarasi variabel untuk menyimpan email dan password
    String nama, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //menghubungkan variabel edemail dengan component pada layout
        edemail = findViewById(R.id.edEmail);
        //menghubungkan variabel edpassword dengan component pada layout
        edpassword = findViewById(R.id.edPassword);
        //menghubungkan variabel btnLogin dengan component pada layout
        Button btnLogin = findViewById(R.id.tLogin);

        btnLogin.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //menyimpan input user di edittext email kedalam variabel nama
                nama = edemail.getText().toString();
                //menyimpan input user di edittext password kedalam variabel password
                password = edpassword.getText().toString();

                String email = "admin";

                String pass = "12345";

                if (nama.equals(email) || password.equals(pass)) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "login berhasil", Toast.LENGTH_LONG);
                    t.show();
                    Bundle b = new Bundle();
                    b.putString("a",nama.trim());

                    b.putString("b",password.trim());

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.putExtras(b);
                    startActivity(i);

                } else {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "username dan password anda salah", Toast.LENGTH_LONG);
                    t.show();
                }

            }
        });
    }
}