package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Candidatures extends AppCompatActivity {

    private ArrayAdapter adapter;
    List<String> listContents;
    Freelance f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidatures);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String jsonMyObject="";
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            jsonMyObject=extras.getString("offre");
        }else{
            jsonMyObject="Vous n'avez pas encore de candidature";
        }

        String jsonMyObject2="";
        Bundle extras2=getIntent().getExtras();
        if(extras2!=null){
            jsonMyObject2=extras2.getString("myObject");
        }
        f = new Gson().fromJson(jsonMyObject2,Freelance.class);

        ListView myListView = (ListView) findViewById(R.id.candidature);
        listContents = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listContents);
        myListView.setAdapter(adapter);
        adapter.add(jsonMyObject);
    }

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


    private void onCompte(){
        Intent i=new Intent(Candidatures.this, Compte.class);
        i.putExtra("myObject", new Gson().toJson(f));
        startActivity(i);
    }

    private void onMain(){
        Intent i=new Intent(Candidatures.this,MainActivity.class);
        i.putExtra("myObject", new Gson().toJson(f));
        startActivity(i);
    }


    private void onRechercher() {
        Intent intent = new Intent(this, Candidatures.class);
        startActivity(intent);
    }
}