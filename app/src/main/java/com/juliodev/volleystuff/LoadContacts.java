package com.juliodev.volleystuff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadContacts extends AsyncTask<String, Void, ArrayList<Contact>> {

    public static final String API_URL = "http://pratikbutani.x10.mx/json_data.json";
    public static final String KEY_CONTACTS = "contacts";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_IMAGE = "profile_pic";

    public static final String TAG = MainActivity.class.getSimpleName();

    private Activity activity;
    private ProgressDialog progress;

    public LoadContacts(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(this.activity);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
    }

    ArrayList<Contact> contacts;

    @Override
    protected ArrayList<Contact> doInBackground(String... strings) {

        contacts = new ArrayList<Contact>();
        // Contactos
        JsonObjectRequest getContacts = new JsonObjectRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                GG(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error en peticion", error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(getContacts);
        Log.e("ASDASD", contacts.size() + "");
        return contacts;
    }

    protected void GG(JSONObject a) {
        Log.e("Prueba", a.toString());
        try {
            JSONArray jsonArray = a.getJSONArray(KEY_CONTACTS);
            Log.e("a", jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Log.e("jhb", i + "");
                Contact contact = new Contact();
                contact.setName(json.getString(KEY_NAME));
                contact.setGender(json.getString(KEY_GENDER));
                contact.setImage(json.getString(KEY_IMAGE), activity);//ResourcesCompat.getDrawable(activity.getResources(),R.drawable.asuna2,null));
                contact.setEmail(json.getString(KEY_EMAIL));

                contacts.add(contact);
            }
            Log.e("FOR", contacts.size() + "");
            onPostExecute(contacts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Contact> contacts) {
        Log.e("post", contacts.size() + "");
        ListView ListContacts = this.activity.findViewById(R.id.listViewContacts);
        ContactAdapter adapter = new ContactAdapter(this.activity, contacts);
        ListContacts.setAdapter(adapter);
        progress.dismiss();
    }
}
