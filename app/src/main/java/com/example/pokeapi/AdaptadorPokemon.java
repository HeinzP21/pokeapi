package com.example.pokeapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPokemon
        extends RecyclerView.Adapter<AdaptadorPokemon.PokemonViewHolder>
            implements View.OnClickListener{

    ArrayList<Pokemon> listaPokemon;
    private View.OnClickListener listener;


    public AdaptadorPokemon(ArrayList<Pokemon> listaPokemon) {
        this.listaPokemon = listaPokemon;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_poke, parent, false);
        view.setOnClickListener(this);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.id.setText("ID: "  +listaPokemon.get(position).getId().toString());
        holder.nombre.setText("NOMBRE: " +listaPokemon.get(position).getName().toString());
        Picasso.get().load(listaPokemon.get(position).getImageUrl()).into(holder.fotoPokemon);
    }

    @Override
    public int getItemCount() {
        return listaPokemon.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView fotoPokemon;
        TextView id, nombre;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoPokemon = (ImageView) itemView.findViewById(R.id.fotopokemon);
            id = (TextView) itemView.findViewById(R.id.idpoke);
            nombre = (TextView) itemView.findViewById(R.id.nombrepoke);

        }
    }
}
