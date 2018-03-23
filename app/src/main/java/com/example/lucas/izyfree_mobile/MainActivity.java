package com.example.lucas.izyfree_mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
curl -XPOST -H "Content-type: application/json" -d '{"name":"a","nomContact":"b","prenomContact":"c","password":"d","tel":"e","email":"f","id":"1","photo":"g"}' http://localhost:8080/v1/entreprise
curl -i -X DELETE http://localhost:8080/v1/entreprise/6


 */

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private TextView mTextMessage;
    private ListView mListView;
    private String jsonResponse;
    private String TAG = MainActivity.class.getSimpleName();
    private static String urlJsonAllObj = "http://10.0.2.2:8080/v1/offre";
    private ArrayAdapter adapter;
    Freelance f;



    List<String> listContents;


    Intent tmp;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_candidatures:
                    onRechercher();
                    return true;
                case R.id.navigation_compte:
                    onCompte();
                    return true;
            }
            return false;
        }
    };

    private void makeJsonObjectRequest() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        showpDialog();

/*        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());*/
        JsonArrayRequest req = new JsonArrayRequest(urlJsonAllObj,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject offre = response.getJSONObject(i);
                                // Parsing json object response
                                // response will be a json object
                                Offre o = new Offre();

                                if(offre.has("id"))o.setId(offre.getInt("id"));
                                if(offre.has("intitule"))o.setIntitule(offre.getString("intitule"));
                                if(offre.has("dateDeb"))o.setDateDeb(offre.getString("dateDeb"));
                                if(offre.has("dateFin"))o.setDateFin(offre.getString("dateFin"));
                                if(offre.has("listeMots"))o.setListeMots(offre.getString("listeMots"));
                                if(offre.has("idEntreprise"))o.setIdEntreprise(offre.getInt("idEntreprise"));

                                int length = response.length();

                                adapter.add("Offre: "+o.getIntitule()+"\nDébut de mission : "+o.getDateDeb()+"\nFin de mission: "+o.getDateFin()+"\nTags "+
                                    o.getListeMots());


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
        setContentView(R.layout.activity_main);
        String jsonMyObject= "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        f = new Gson().fromJson(jsonMyObject,Freelance.class);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        ListView myListView = (ListView) findViewById(R.id.listView);
        listContents = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listContents);
        myListView.setAdapter(adapter);
        makeJsonObjectRequest();

    }


    private void onCompte(){
        Intent i=new Intent(MainActivity.this, Compte.class);
        i.putExtra("myObject", new Gson().toJson(f));
        startActivity(i);
    }


    private void onRechercher() {
        Intent intent = new Intent(this, Candidatures.class);
        startActivity(intent);
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}





