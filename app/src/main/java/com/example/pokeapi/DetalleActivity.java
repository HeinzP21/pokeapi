package com.example.pokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    ImageView fotoDetalle;
    TextView tvId, tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        fotoDetalle = (ImageView) findViewById(R.id.fotoDetalle);
        tvId = (TextView) findViewById(R.id.id_detalle);
        tvNombre = (TextView) findViewById(R.id.nombre_detalle);

        String id = getIntent().getStringExtra("id");
        String nombre = getIntent().getStringExtra("nombre");
        String foto = getIntent().getStringExtra("foto");

        //  Por si es entero
        //int id = getIntent().getIntExtra("ide",0);

        Picasso.get().load(foto).into(fotoDetalle);
        tvId.setText(id);
        tvNombre.setText(nombre);


    }
}
