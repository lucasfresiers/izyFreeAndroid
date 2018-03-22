package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClickFreelance (View view) {
        Intent intent = new Intent(this, Authent.class);
        intent.putExtra("profil", "freelance");
        startActivity(intent);
    }
    public void onClickEntreprise (View view) {
        Intent intent = new Intent(this, Authent.class);
        intent.putExtra("profil", "entreprise");
        startActivity(intent);
    }
}
