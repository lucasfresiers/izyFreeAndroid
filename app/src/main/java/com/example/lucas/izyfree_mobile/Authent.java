package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Authent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authent);
        Intent intent = getIntent();
        // On suppose que tu as mis un String dans l'Intent via le putExtra()
        String value = intent.getStringExtra("chaine");



    }
}
