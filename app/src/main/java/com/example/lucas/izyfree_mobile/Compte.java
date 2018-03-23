package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Compte extends AppCompatActivity {
    Intent tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tmp = getIntent();
        setContentView(R.layout.activity_compte);
        TextView nom = findViewById(R.id.resNom);
        nom.setText(tmp.getStringExtra("nom"));
        TextView tel = findViewById(R.id.resTel);
        tel.setText(tmp.getStringExtra("tel"));
        TextView mail = findViewById(R.id.resMail);
        mail.setText(tmp.getStringExtra("mail"));
    }

}
