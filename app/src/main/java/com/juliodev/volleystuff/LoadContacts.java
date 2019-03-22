package com.juliodev.volleystuff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

public class LoadContacts extends AsyncTask<String, Void, ArrayList<Contacto>>  {

    public static final String API_URL = "http://pratikbutani.x10.mx/json_data.json";
    public static final String KEY_CONTACTS = "contacts";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";

    private Activity activity;
    private ProgressDialog progress;

    public LoadContacts(Activity activity) { this.activity - activity;}

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected ArrayList<Contacto> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Contacto> contactos) {
        ListView ListContacts = this.activity.findViewById(R.id.listViewContacts);
    }
}
