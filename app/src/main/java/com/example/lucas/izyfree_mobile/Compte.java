package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class Compte extends AppCompatActivity {
    Freelance f;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        Button angryButton =  findViewById(R.id.angry_btn);
        angryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

}
