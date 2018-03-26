package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class Compte extends AppCompatActivity {
    Freelance f;
    Intent intent;
    BottomNavigationView navigation;
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
                startActivity(intent);
            }
        });
    }
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


    private void onCompte(){
        Intent i=new Intent(this, Compte.class);
        i.putExtra("myObject", new Gson().toJson(f));
        startActivity(i);
    }


    private void onRechercher() {
        Intent intent = new Intent(this, Candidatures.class);
        startActivity(intent);
    }

}
