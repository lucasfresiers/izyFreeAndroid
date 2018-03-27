package com.example.lucas.izyfree_mobile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bertins on 27/03/18.
 */

public class CandidatView extends AppCompatActivity {

    AlertDialog.Builder builder;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidat_view);
        String jsonMyObject = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("candidat");
        }
        tv = (TextView) findViewById(R.id.candidatview);
        tv.setText(jsonMyObject);


    }

    public void onContacter(View view) {
        onCreateDialog().show();
    }

    public Dialog onCreateDialog() {
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Contacter le freelance?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(CandidatView.this, MainActivityEntr.class);
                        intent.putExtra("candidat", tv.getText());
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public Context getActivity() {
        return this;
    }
}