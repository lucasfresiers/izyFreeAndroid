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
import java.util.concurrent.ExecutionException;

public class Compte extends AppCompatActivity {
    Freelance f;
    BottomNavigationView navigation;
    private String urlPut;
    String response = "";
    EditText nom;
    EditText tel;
    EditText mail;
    EditText prenom;
    EditText poste;
    EditText tarif;
    EditText dispo;
    EditText localisation;
    Intent intent;
    String v;

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
        setContentView(R.layout.activity_compte);
        localisation = findViewById(R.id.resLocalisation);
        intent = new Intent(this, Authent.class);
        super.onCreate(savedInstanceState);
        String jsonMyObject= "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        nom = findViewById(R.id.resNom);
        navigation =  findViewById(R.id.navigation);
        tel = findViewById(R.id.resTel);
        mail = findViewById(R.id.resMail);
        prenom = findViewById(R.id.resPrenom);
        poste = findViewById(R.id.resPoste);
        tarif = findViewById(R.id.resTarif);
        dispo = findViewById(R.id.resDispo);

        f = new Gson().fromJson(jsonMyObject,Freelance.class);

        try {
            nom.setText(f.getName());
            tel.setText(f.getPhone()+"");
            mail.setText(f.getEmail());
            prenom.setText(f.getFirstName());
            poste.setText(f.getJob());
            tarif.setText(f.getTarif());
            dispo.setText(f.getConditions());
            localisation.setText(f.getLocalisation());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            urlPut = "http://5.135.83.124/v1/freelance/id/"+f.getId();
        }
       catch (Exception e) {
            e.printStackTrace();
       }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Button modif =  findViewById(R.id.modif);
        modif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateInfos();
                //startActivity(intent);
            }
        });



        intent = getIntent();
        final String value = intent.getStringExtra("profil");
        v = value;
        Button deco =  findViewById(R.id.deco);
        deco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Compte.this,Welcome.class);
                i.putExtra("profil",""+v);
                startActivity(i);
            }
        });

    }

    private void onCompte(){
        Intent i=new Intent(this, Compte.class);
        i.putExtra("myObject", new Gson().toJson(f));
        i.putExtra("profil",v);
        startActivity(i);
    }

    private void onRechercher() {
        Intent intent = new Intent(this, Candidatures.class);
        intent.putExtra("myObject", new Gson().toJson(f));
        intent.putExtra("profil",v);
        //startActivity(intent);
    }
    private void onMain() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("myObject", new Gson().toJson(f));
        i.putExtra("profil",v);
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


        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.PUT, urlPut, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Toast.makeText(Compte.this, "OK CA MARCHE", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Compte.this, "NON CA MARCHE PAS", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonobj);



        f.setName(nom.getText().toString());
        nom.setText(f.getName());
        f.setPhone(tel.getText().toString());
        tel.setText(f.getPhone());
        f.setEmail(mail.getText().toString());
        mail.setText(f.getEmail());
        f.setFirstName(prenom.getText().toString());
        prenom.setText(f.getFirstName());
        f.setJob(poste.getText().toString());
        poste.setText(f.getJob());
        f.setTarif(tarif.getText().toString());
        tarif.setText(f.getTarif());
        f.setConditions(dispo.getText().toString());
        dispo.setText(f.getConditions());
        f.setLocalisation(localisation.getText().toString());
        localisation.setText(f.getLocalisation());

    }


}
