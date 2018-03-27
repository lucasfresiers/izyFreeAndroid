package com.example.lucas.izyfree_mobile;

/**
 * Created by bertins on 22/03/18.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityEntr extends AppCompatActivity {

    private ProgressDialog pDialog;
    private TextView mTextMessage;
    private ListView mListView;
    private String jsonResponse;
    private String TAG = MainActivity.class.getSimpleName();
    private static String urlJsonAllObj = "http://10.0.2.2:8080/v1/freelance";
    private ArrayAdapter adapter;
    Entreprise e;
    JSONObject freelance;
    List<String> listContents;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationEntr_home:
                    return true;
                case R.id.navigationEntr_compte:
                    onCompteEntr();
                    return true;
            }
            return false;
        }
    };

    private void makeJsonObjectRequest() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonAllObj,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                freelance = response.getJSONObject(i);
                                // Parsing json object response
                                // response will be a json object
                                Freelance f = new Freelance();


                                if(freelance.has("id"))f.setId(freelance.getInt("id"));
                                if(freelance.has("job"))f.setJob(freelance.getString("job"));
                                if(freelance.has("localisation"))f.setLocalisation(freelance.getString("localisation"));
                                if(freelance.has("tarif"))f.setTarif(freelance.getString("tarif"));
                                if(freelance.has("phone"))f.setPhone(freelance.getString("phone"));

                                int length = response.length();

                                    adapter.add("Poste: " + f.getJob() + "\nTarif: " + f.getTarif() + "\nLocalisation: " + f.getLocalisation() + "\nTéléphone: " +
                                            f.getPhone());



                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        // AppController a = AppController.getInstance();
        //   a.addToRequestQueue(req);
        requestQueue.add(req);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainentr);

        String jsonMyObject= "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        e = new Gson().fromJson(jsonMyObject,Entreprise.class);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationEntr);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        ListView myListView = (ListView) findViewById(R.id.listView);
        listContents = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listContents);

        myListView.setAdapter(adapter);
        makeJsonObjectRequest();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

                Intent intent=new Intent(MainActivityEntr.this, CandidatView.class);
                intent.putExtra("candidat",selectedText);
                startActivity(intent);

            }
        });
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void onCompteEntr(){
        Intent intent=new Intent(this, CompteEntr.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("myObject", new Gson().toJson(e));
        startActivity(intent);
    }






}
