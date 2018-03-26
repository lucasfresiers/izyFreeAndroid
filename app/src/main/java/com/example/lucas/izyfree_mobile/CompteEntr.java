package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bertins on 22/03/18.
 */

public class CompteEntr extends AppCompatActivity {

    Entreprise e;
    Intent intent;
    BottomNavigationView navigation;
    private String urlPut = "http://10.0.2.2:8080/v1/entreprise/id/2";
    String response = "";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationEntr_home:
                    onMain();
                    return true;
             /*   case R.id.navigation_candidatures:
                    onRechercher();
                    return true;*/
                case R.id.navigationEntr_compte:
                    onCompteEntr();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_compteentr);
        navigation =  findViewById(R.id.navigationEntr);
        intent = new Intent(this, Authent.class);
        super.onCreate(savedInstanceState);
        String jsonMyObject= "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("myObject");
        }
        e = new Gson().fromJson(jsonMyObject,Entreprise.class);

        EditText nomentr = findViewById(R.id.resNomentr);
        nomentr.setText(e.getName());
        EditText nom = findViewById(R.id.resNom);
        nom.setText(e.getNomContact());
        EditText prenom = findViewById(R.id.resPrenom);
        prenom.setText(e.getPrenomContact());
        EditText tel = findViewById(R.id.resTel);
        tel.setText(e.getTel());
        EditText mail = findViewById(R.id.resMail);
        mail.setText(e.getEmail());
        EditText fonctionsContact = findViewById(R.id.resFonctions);
        fonctionsContact.setText(e.getFonctionsContact());
        EditText profilRecherche = findViewById(R.id.resProfilRecherche);
        profilRecherche.setText(e.getProfilRecherche());
        EditText ville = findViewById(R.id.resVille);
        ville.setText(e.getEmail());
        EditText champLibre = findViewById(R.id.resChampLibre);
        champLibre.setText(e.getChampLibre());
        EditText search = findViewById(R.id.resSearch);
        search.setText(e.getSearch());



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationEntr);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


       /* Button angryButton =  findViewById(R.id.angry_btn);
        angryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateInfos();
                //startActivity(intent);
            }
        });*/


    }

    private void onCompteEntr(){
        Intent i=new Intent(this, CompteEntr.class);
        i.putExtra("myObject", new Gson().toJson(e));
        startActivity(i);
    }

    private void onRechercher() {
        Intent intent = new Intent(this, Candidatures.class);
        startActivity(intent);
    }

    private void onMain(){
        Intent intent=new Intent(this, MainActivityEntr.class);
        intent.putExtra("myObject", new Gson().toJson(e));
        startActivity(intent);
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
                String id = e.getId()+"";
                params.put("id",id);
                params.put("email", e.getEmail());
                params.put("name", e.getName());
                params.put("nomContact", e.getNomContact());
                params.put("prenomContact", e.getPrenomContact());
                params.put("phone", e.getTel());

                return params;
            }


        };

        requestQueue.add(putRequest);
    }
}
