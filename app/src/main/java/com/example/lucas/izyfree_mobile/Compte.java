package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Compte extends AppCompatActivity {
    Freelance f;
    Intent intent;
    BottomNavigationView navigation;
    private String urlPut = "http://10.0.2.2:8080/v1/freelance/id/1";
    String response = "";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    onMain();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        navigation =  findViewById(R.id.navigation);
        intent = new Intent(this, Authent.class);
        super.onCreate(savedInstanceState);
        String jsonMyObject= "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        f = new Gson().fromJson(jsonMyObject,Freelance.class);
        setContentView(R.layout.activity_compte);
        TextView nom = findViewById(R.id.resNom);
        nom.setText(f.getName());
        TextView tel = findViewById(R.id.resTel);
        tel.setText(f.getPhone());
        TextView mail = findViewById(R.id.resMail);
        mail.setText(f.getEmail());
        TextView prenom = findViewById(R.id.resPrenom);
        prenom.setText(f.getFirstName());
        TextView poste = findViewById(R.id.resPoste);
        poste.setText(f.getJob());
        TextView tarif = findViewById(R.id.resTarif);
        tarif.setText(f.getTarif());
        TextView dispo = findViewById(R.id.resDispo);
        dispo.setText(f.getConditions());
        TextView localisation = findViewById(R.id.resLocalisation);
        localisation.setText(f.getLocalisation());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Button angryButton =  findViewById(R.id.angry_btn);
        angryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateInfos2();
                //startActivity(intent);
            }
        });


    }

    private void onCompte(){
        Intent i=new Intent(this, Compte.class);
        i.putExtra("myObject", new Gson().toJson(f));
        startActivity(i);
    }

    private void onRechercher() {
        Intent intent = new Intent(this, Candidatures.class);
        startActivity(intent);
    }
    private void onMain() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("myObject", new Gson().toJson(f));
        startActivity(i);
    }


    private void updateInfos() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest putRequest = new StringRequest(Request.Method.PUT, urlPut,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
// response
                        Log.d("Response", response);
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
// error
                Log.d("Error.Response", response);

            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                String id = f.getId()+"";
                params.put("id",id);
                params.put("email", f.getEmail());
                params.put("name", f.getName());
                params.put("firstname", f.getFirstName());
                params.put("phone", f.getPhone());
                params.put("job", f.getJob());
                // on va tester le tarif
                params.put("tarif", "8000€/mois");
                params.put("localisation", f.getLocalisation());
                params.put("conditions", f.getConditions());

                return params;
            }
            /*
            @Override
            public String getBodyContentType() {
                return "application/json";
            }*/


        };
        requestQueue.add(putRequest);
    }

    private void updateInfos2 () {
        RequestQueue queue = Volley.newRequestQueue(this); // this = context
    StringRequest putRequest = new StringRequest(Request.Method.PUT, urlPut,
            new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    // response
                    Log.d("Response", response.toString());
                }
            },
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error
                    Log.d("Error.Response", error.toString());
                }
            }
    ) {

        @Override
        public Map<String, String> getHeaders()
        {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            //or try with this:
            //headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            return headers;
        }
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            String id = f.getId()+"";
            params.put("id",id);
            params.put("email", f.getEmail());
            params.put("name", f.getName());
            params.put("firstname", f.getFirstName());
            params.put("phone", f.getPhone());
            params.put("job", f.getJob());
            // on va tester le tarif
            params.put("tarif", "8000€/mois");
            params.put("localisation", f.getLocalisation());
            params.put("conditions", f.getConditions());
            return params;
        }
    };


        queue.add(putRequest);
}



}
