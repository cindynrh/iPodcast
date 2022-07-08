package com.example.ta_pam;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;



import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class ListLagu extends AppCompatActivity {
    private String[] itemsAll;
    private ListView mSongsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSongsList =findViewById(R.id.songList);
    }
    public void appExternalStorageStoragePermisision() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        display();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }



    public ArrayList<File> readData(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] allFiles  =file.listFiles();
        for (File individualFile : allFiles)
        {
            if(individualFile.isDirectory() && !individualFile.isHidden())
            {
                arrayList.addAll(readData(individualFile));
            }
            else
            {
                if (individualFile.getName().endsWith(".aac") || individualFile.getName().endsWith(".wav")|| individualFile.getName().endsWith(".mp3"))
                {
                    arrayList.add(individualFile);
                }
            }

        }
        return arrayList;
    }
    private void display()
    {
        final ArrayList<File> audio = readData(getExternalStorageDirectory());

        itemsAll = new String[audio.size()];
        for (int songCounter=0;songCounter<audio.size();songCounter++){
            itemsAll[songCounter]=audio.get(songCounter).getName();

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListLagu.this, R.layout.activity_list_lagu, fileList());
        Intent intent= new Intent( ListLagu.this,Podcast.class);
        mSongsList.setAdapter(arrayAdapter);
        mSongsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l)
            {
                String songName = mSongsList.getItemAtPosition(i).toString();
                Intent intent = new Intent(ListLagu.this, Podcast.class);
                intent.putExtra("song", audio );
                intent.putExtra("name", songName);
                intent.putExtra("posisi", i);
                startActivity(intent);
            }
        });
    }
}