package com.example.lucas.izyfree_mobile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class OffreView extends AppCompatActivity {

    AlertDialog.Builder builder;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre_view);
        String jsonMyObject="";
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            jsonMyObject=extras.getString("offre");
        }
        tv=(TextView) findViewById(R.id.offreview);
        tv.setText(jsonMyObject);


    }
    public void onPostuler(View view){
        onCreateDialog().show();
    }

    public Dialog onCreateDialog() {
        builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Postuler?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent=new Intent(OffreView.this, Candidatures.class);
                        intent.putExtra("offre", tv.getText());
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
