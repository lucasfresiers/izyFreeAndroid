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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bertins on 22/03/18.
 */

public class CompteEntr extends AppCompatActivity {

    Entreprise e;
    Intent intent;
    BottomNavigationView navigation;
    private String urlPut = "http://10.0.2.2:8080/v1/entreprise/id/1";
    String response = "";
    EditText nomentr;
    EditText nom;
    EditText prenom;
    EditText tel;
    EditText mail;
    EditText fonctionsContact;
    EditText profilRecherche;
    EditText ville;
    EditText champLibre;
    EditText search;

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

        nomentr = findViewById(R.id.resNomentr);
        nomentr.setText(e.getName());
        nom = findViewById(R.id.resNom);
        nom.setText(e.getNomContact());
        prenom = findViewById(R.id.resPrenom);
        prenom.setText(e.getPrenomContact());
        tel = findViewById(R.id.resTel);
        tel.setText(e.getTel());
        mail = findViewById(R.id.resMail);
        mail.setText(e.getEmail());
        fonctionsContact = findViewById(R.id.resFonctions);
        fonctionsContact.setText(e.getFonctionsContact());
        profilRecherche = findViewById(R.id.resProfilRecherche);
        profilRecherche.setText(e.getProfilRecherche());
        ville = findViewById(R.id.resVille);
        ville.setText(e.getEmail());
        champLibre = findViewById(R.id.resChampLibre);
        champLibre.setText(e.getChampLibre());
        search = findViewById(R.id.resSearch);
        search.setText(e.getSearch());



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationEntr);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button modif =  findViewById(R.id.modif);
        modif.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateInfos();
                //startActivity(intent);
            }
        });

        Button deco =  findViewById(R.id.deco);
        deco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CompteEntr.this,Welcome.class);
                startActivity(intent);
            }
        });


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


    private void updateInfos () {

        RequestQueue queue = Volley.newRequestQueue(this); // this = context
        Map<String, String> params = new HashMap<String, String>();
        String id = e.getId() + "";
        params.put("id", id);
        params.put("email", mail.getText().toString());
        params.put("nomContact", nom.getText().toString());
        params.put("prenomContact", prenom.getText().toString());
        params.put("phone", tel.getText().toString());
        params.put("name", nomentr.getText().toString());
        params.put("fonctionsContact", fonctionsContact.getText().toString());
        params.put("profilRecherche", profilRecherche.getText().toString());
        params.put("ville", ville.getText().toString());
        params.put("champLibre", champLibre.getText().toString());
        params.put("search", search.getText().toString());


        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.PUT, urlPut, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Toast.makeText(CompteEntr.this, "OK CA MARCHE", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CompteEntr.this, "NON CA MARCHE PAS", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonobj);



        e.setName(nomentr.getText().toString());
        nomentr.setText(e.getName());
        e.setTel(tel.getText().toString());
        tel.setText(e.getTel());
        e.setEmail(mail.getText().toString());
        mail.setText(e.getEmail());
        e.setNomContact(nom.getText().toString());
        prenom.setText(e.getNomContact());
        e.setPrenomContact(prenom.getText().toString());
        prenom.setText(e.getPrenomContact());
        e.setFonctionsContact(fonctionsContact.getText().toString());
        fonctionsContact.setText(e.getFonctionsContact());
        e.setProfilRecherche(profilRecherche.getText().toString());
        profilRecherche.setText(e.getProfilRecherche());
        e.setVille(ville.getText().toString());
        ville.setText(e.getVille());
        e.setChampLibre(champLibre.getText().toString());
        champLibre.setText(e.getChampLibre());
        e.setSearch(search.getText().toString());
        search.setText(e.getSearch());
    }

}
