package com.example.lucas.izyfree_mobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Authent extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();


    // Progress dialog
    private ProgressDialog pDialog;
    private Button connect;

    TextView  mdp;
    // temporary string to show the parsed response
    private String jsonResponse;
    // json object response url

    private String urlJsonObj = "http://10.0.2.2:8080/v1/entreprise/a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authent);
        Intent intent = getIntent();
        // On suppose que tu as mis un String dans l'Intent via le putExtra()
        String value = intent.getStringExtra("profil");
        mdp = findViewById(R.id.mdp);
        connect = findViewById(R.id.connect);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                makeJsonObjectRequest();
            }
        });
    }



    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("nom");
                    String email = response.getString("email");
                    String tel = response.getString("tel");

                    jsonResponse = "";
                    jsonResponse += "Name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "Home: " + tel + "\n\n";

                   mdp.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController a =  AppController.getInstance();
        a.addToRequestQueue(jsonObjReq);
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
