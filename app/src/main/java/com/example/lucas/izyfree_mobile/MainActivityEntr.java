package com.example.lucas.izyfree_mobile;

/**
 * Created by bertins on 22/03/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivityEntr extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationEntr_home:
                    return true;
                case R.id.navigationEntr_rechercher:
                    // onRechercherEntr();
                    return true;
                case R.id.navigationEntr_compte:
                    onCompteEntr();
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

    private void onCompteEntr(){
        Intent intent=new Intent(this, CompteEntr.class);
        startActivity(intent);
    }

/*    private void onRechercherEntr(){
        Intent intent=new Intent(this, RechercherEntr.class);
        startActivity(intent);
    }*/



}
