package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity
        implements Response.Listener<JSONObject> , Response.ErrorListener{

    TextView tvID, tvNombre, tvStatus, tvSpecie;
    EditText txtBuscar;
    Button btnBuscar, pasarPantalla;
    ImageView foto;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvID = (TextView) findViewById(R.id.id);
        tvNombre = (TextView) findViewById(R.id.nombre);
        tvStatus = (TextView) findViewById(R.id.status);
        tvSpecie = (TextView) findViewById(R.id.specie);
        txtBuscar= (EditText) findViewById(R.id.txtID);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        foto = (ImageView) findViewById(R.id.foto);
        pasarPantalla = (Button) findViewById(R.id.btnPasarPantalla);

        requestQueue = Volley.newRequestQueue(this);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar();
            }
        });

        pasarPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArrayActivity.class);
                startActivity(intent);
            }
        });

    }

    private void buscar() {
        String id = txtBuscar.getText().toString();
        String url = "https://rickandmortyapi.com/api/character/" +id;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            tvID.setText("ID: " + response.getInt("id"));
            tvNombre.setText("nombre: " + response.getString("name"));
            tvSpecie.setText("Especie: " +response.getString("species"));
            tvStatus.setText("Estado " +response.getString("status"));
            Picasso.get().load(response.getString("image")).into(foto);
        }catch (Exception e){

        }
    }
}
