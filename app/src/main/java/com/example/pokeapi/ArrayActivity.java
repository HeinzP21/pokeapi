package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArrayActivity extends AppCompatActivity
        implements Response.Listener<JSONObject>, Response.ErrorListener {

    RecyclerView recyclerPokemon;
    ArrayList<Pokemon> listaPokemon;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        recyclerPokemon = (RecyclerView) findViewById(R.id.recyclerPokemon);
        listaPokemon = new ArrayList<>();

        recyclerPokemon.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerPokemon.setHasFixedSize(true);

        request = Volley.newRequestQueue(getApplicationContext());

        cargarApi();


    }

    private void cargarApi() {
        String url = "https://api.pokemontcg.io/v1/cards";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Error 404", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        Log.e("array", String.valueOf(response));

        JSONArray jsonArray = response.optJSONArray("cards");

        try {
            for (int i= 0; i < jsonArray.length(); i++){
                Pokemon pokemon = new Pokemon();
                JSONObject objeto = jsonArray.getJSONObject(i);
                pokemon.setId(objeto.getString("id"));
                pokemon.setName(objeto.getString("name"));
                pokemon.setImageUrl(objeto.getString("imageUrl"));

                listaPokemon.add(pokemon);
            }

            AdaptadorPokemon adaptadorPokemon = new AdaptadorPokemon(listaPokemon);
            recyclerPokemon.setAdapter(adaptadorPokemon);

            adaptadorPokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ArrayActivity.this, DetalleActivity.class);
                    intent.putExtra("id", listaPokemon.get(recyclerPokemon.getChildAdapterPosition(view)).getId());
                    intent.putExtra("nombre", listaPokemon.get(recyclerPokemon.getChildAdapterPosition(view)).getName());
                    intent.putExtra("foto", listaPokemon.get(recyclerPokemon.getChildAdapterPosition(view)).getImageUrl());
                    startActivity(intent);
                }
            });


        }catch (Exception e){

        }
    }
}
