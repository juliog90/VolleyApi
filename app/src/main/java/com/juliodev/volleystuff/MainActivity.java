package com.juliodev.volleystuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listviewid;

    TextView txtName,txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView
        listviewid = (ListView)findViewById(R.id.listViewContacts);

        //Texview
        txtName = (TextView)findViewById(R.id.txtNombre);
        txtEmail = (TextView)findViewById(R.id.txtEmail);
        //load data in the main
        new LoadContacts(MainActivity.this).execute();
    }
}
