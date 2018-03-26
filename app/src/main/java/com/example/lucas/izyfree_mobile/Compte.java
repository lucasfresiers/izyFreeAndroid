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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Compte extends AppCompatActivity {
    Freelance f;
    Intent intent;
    BottomNavigationView navigation;
    private String urlPut = "http://10.0.2.2:8080/v1/freelance/id/1";
    String response = "";
    EditText nom;
    EditText tel;
    EditText mail;
    EditText prenom;
    EditText poste;
    EditText tarif;
    EditText dispo;
    EditText localisation;

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
        nom = findViewById(R.id.resNom);
        navigation =  findViewById(R.id.navigation);
        tel = findViewById(R.id.resTel);
        mail = findViewById(R.id.resMail);
        prenom = findViewById(R.id.resPrenom);
        poste = findViewById(R.id.resPoste);
        tarif = findViewById(R.id.resTarif);
        dispo = findViewById(R.id.resDispo);
        localisation = findViewById(R.id.resLocalisation);

        intent = new Intent(this, Authent.class);
        super.onCreate(savedInstanceState);
        String jsonMyObject= "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        f = new Gson().fromJson(jsonMyObject,Freelance.class);
        setContentView(R.layout.activity_compte);
        nom.setText(f.getName());
        tel.setText(f.getPhone());
        mail.setText(f.getEmail());
        prenom.setText(f.getFirstName());
        poste.setText(f.getJob());
        tarif.setText(f.getTarif());
        dispo.setText(f.getConditions());
        localisation.setText(f.getLocalisation());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Button angryButton =  findViewById(R.id.angry_btn);
        angryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateInfos();
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



    private void updateInfos () {

        RequestQueue queue = Volley.newRequestQueue(this); // this = context
        Map<String, String> params = new HashMap<String, String>();
        String id = f.getId() + "";
        params.put("id", id);
        params.put("email", mail.getText().toString());
        params.put("name", nom.getText().toString());
        params.put("firstname", prenom.getText().toString());
        params.put("phone", tel.getText().toString());
        params.put("job", poste.getText().toString());
        params.put("tarif", tarif.getText().toString());
        params.put("localisation", localisation.getText().toString());
        params.put("conditions", dispo.getText().toString());

        nom.setText(f.getName());
        f.setName(nom.getText().toString());
        tel.setText(f.getPhone());
        f.setPhone(tel.getText().toString());
        mail.setText(f.getEmail());
        f.setEmail(mail.getText().toString());
        prenom.setText(f.getFirstName());
        f.setFirstName(prenom.getText().toString());
        poste.setText(f.getJob());
        f.setJob(poste.getText().toString());
        tarif.setText(f.getTarif());
        f.setTarif(tarif.getText().toString());
        dispo.setText(f.getConditions());
        f.setConditions(dispo.getText().toString());
        localisation.setText(f.getLocalisation());
        f.setLocalisation(dispo.getText().toString());

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.PUT, urlPut, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Toast.makeText(Compte.this,"OK CA MARCHE",Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Compte.this,"NON CA MARCHE PAS",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonobj);
    }

}
