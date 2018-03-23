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

    // Intent intentFrelance = new Intent(this, MainActivity.class);
   // Intent intentEntreprise = new Intent(this, MainActivityEntr.class);

    Intent intentFrelance;


    // Progress dialog
    private ProgressDialog pDialog;
    private Button connect;

    TextView mdp;
    TextView email;
    TextView debug;
    boolean entrepriseValide = false;
    boolean freelanceValide = false;

    // temporary string to show the parsed response
    private String jsonResponse;
    // json object response url

    private String urlJsonObj = "http://10.0.2.2:8080/v1/entreprise/";
    private String urlJsonAllEntreprise = "http://10.0.2.2:8080/v1/entreprise/";
    private String urlJsonAllFreelance = "http://10.0.2.2:8080/v1/freelance/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authent);
        Intent intent = getIntent();
        intentFrelance = new Intent(this, MainActivity.class);
        final String value = intent.getStringExtra("profil");
        mdp = findViewById(R.id.mdp);
        email = findViewById(R.id.textEmail);
        connect = findViewById(R.id.connect);
        debug = findViewById(R.id.debug);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                if (value.equals("entreprise")) makeJsonArrayRequestEntreprise();
                if (value.equals("freelance")) makeJsonArrayRequestFreelance();
            }
        });
    }


    /**
     * Method to make json object request where json response starts wtih {
     */
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
        AppController a = AppController.getInstance();
        a.addToRequestQueue(jsonObjReq);
    }

    private void makeJsonArrayRequestEntreprise() {

        showpDialog();


        JsonArrayRequest req = new JsonArrayRequest(urlJsonAllEntreprise,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject entreprise = (JSONObject) response
                                        .get(i);

                                Entreprise e = new Entreprise();
                                e.setEmail("");

                                if (entreprise.has("id")) e.setId(entreprise.getInt("id"));
                                if (entreprise.has("email")) e.setEmail(entreprise.getString("email"));
                                if (entreprise.has("nom")) e.setName(entreprise.getString("nom"));
                                if (entreprise.has("tel")) e.setTel(entreprise.getString("tel"));



                                jsonResponse = "";
                                jsonResponse += "Name: " + e.getName() + "\n\n";
                                jsonResponse += "Email: " + e.getEmail() + "\n\n";
                                jsonResponse += "Home: " + e.getTel() + "\n\n";
                                debug.setText(jsonResponse);


                                if (e.getEmail().equals(email.getText().toString())) {
                                    entrepriseValide = true;
                                    break;
                                }

                            }
                            if (entrepriseValide) {
                                launchWelcomeEntreprise();
                            } else {
                                debug.setText("Entreprise inconnu");
                            }


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
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    private void makeJsonArrayRequestFreelance() {

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonAllFreelance,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject freelance = (JSONObject) response
                                        .get(i);

                                Freelance f = new Freelance();
                                f.setEmail("");

                                if (freelance.has("id")) f.setId(freelance.getInt("id"));
                                if (freelance.has("email")) f.setEmail(freelance.getString("email"));
                                if (freelance.has("name")) f.setName(freelance.getString("name"));
                                if (freelance.has("tel")) f.setPhone(freelance.getString("tel"));



                                jsonResponse = "";
                                jsonResponse += "Name: " + f.getName() + "\n\n";
                                jsonResponse += "Email: " + f.getEmail() + "\n\n";
                                jsonResponse += "Home: " + f.getPhone() + "\n\n";
                                debug.setText(jsonResponse);


                                if (f.getEmail().equals(email.getText().toString())) {
                                    freelanceValide = true;
                                    intentFrelance.putExtra("email",f.getEmail());

                                    break;
                                }

                            }
                            if (freelanceValide) {

                                launchWelcomeFreelance();
                            } else {
                                debug.setText("Freelance inconnu");
                            }


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
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void launchWelcomeEntreprise () {
        //startActivity(intentEntreprise);
    }

    public void launchWelcomeFreelance (){
        startActivity(intentFrelance);
    }
}
