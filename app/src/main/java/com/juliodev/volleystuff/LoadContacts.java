package com.juliodev.volleystuff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadContacts extends AsyncTask<String, Void, ArrayList<Contacto>>  {

    public static final String API_URL = "http://pratikbutani.x10.mx/json_data.json";
    public static final String KEY_CONTACTS = "contacts";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_IMAGE = "profile_pic";

    public static final String TAG = MainActivity.class.getSimpleName();

    private Activity activity;
    private ProgressDialog progress;

    public LoadContacts(Activity activity) { this.activity = activity;}



    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    protected ArrayList<Contacto> doInBackground(String... strings) {
        // Contactos
        final ArrayList<Contacto> contacts = new ArrayList<Contacto>();
        JsonArrayRequest getContacts = new JsonArrayRequest(API_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // parsing json
                for (int i = 0; 1 < response.length(); i++) {
                    try {
                        JSONObject json = response.getJSONObject(i);
                        Contacto contact = new Contacto();
                        contact.setName(json.getString(KEY_NAME));
                        contact.setGender(json.getString(KEY_GENDER));
                        contact.setImage(json.getString(KEY_IMAGE));
                        contact.setEmail(json.getString(KEY_EMAIL));

                        contacts.add(contact);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error en peticion" + error.getMessage());
                    }
                };
            }
        });
        return contacts;
    }

    @Override
    protected void onPostExecute(ArrayList<Contacto> contactos) {
        ListView ListContacts = this.activity.findViewById(R.id.listViewContacts);
        ContactAdapter adapter = new ContactAdapter(this.activity, contactos);
        ListContacts.setAdapter(adapter);
        progress.dismiss();
    }
}
