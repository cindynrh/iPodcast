package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class formpendaftaran extends AppCompatActivity {

    EditText edtNama,edtEmail,edtPassword,edtrepass;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formpendaftaran);

        edtNama =findViewById(R.id.edNama);
        edtEmail =findViewById(R.id.edEmail);
        edtPassword =findViewById(R.id.edPassword);
        edtrepass =findViewById(R.id.edrepas);

        fab =findViewById(R.id.febSimpan);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Membuat kondisi untuk mengecek apakah EditText kosong atau tidak
                if (edtNama.getText().toString().isEmpty() ||
                        edtEmail.getText().toString().isEmpty() ||
                        edtrepass.getText().toString().isEmpty()) {
                    //Menampilkan pesan notifikasi jika seluruh Edittext wajib diisi
                    Snackbar.make(view, "wajib isi seluruh data !!!", Snackbar.LENGTH_LONG).show();
                } else {
                    //membuat kondisi untuk mengecek apakah isi dari EditText password dan edittext repassword
                    //sama atau tidak
                    if (edtPassword.getText().toString().equals(edtrepass.getText().toString())) {
                        //Menampilkan pesan notifikasi jika pendaftaran berhasil
                        Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil . . .",
                                Toast.LENGTH_LONG).show();
                        //method untuk kembali ke activity Main
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        //Menampilkan pesan bahwa isi dari Edittext Password dan editText repassword
                        //tidak sama
                        Snackbar.make(view, "Password dan Repassword harus sama!!!!",
                                Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}