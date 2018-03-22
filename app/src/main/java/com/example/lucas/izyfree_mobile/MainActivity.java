package com.example.lucas.izyfree_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
/*
curl -XPOST -H "Content-type: application/json" -d '{"name":"a","nomContact":"b","prenomContact":"c","password":"d","tel":"e","email":"f","id":"1","photo":"g"}' http://localhost:8080/v1/entreprise
curl -i -X DELETE http://localhost:8080/v1/entreprise/6


 */

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_rechercher:
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    private void onCompte(){
        Intent i=new Intent(this, Compte.class);
        startActivity(i);
    }


}
