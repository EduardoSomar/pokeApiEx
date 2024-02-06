package com.example.apipokee.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.apipokee.Detalle;
import com.example.apipokee.R;
import com.example.apipokee.modelos.PokemonModel;
import com.example.apipokee.modelos.PokemonPokedex;
import com.example.apipokee.services.PokeApiService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<PokemonPokedex> dataNew;
    private ArrayList<PokemonPokedex> data;
    private PokemonModel pokemonModel;
    private Context context;
    private Retrofit retrofit;
    private Gson gson = new Gson();

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
        dataNew = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonPokedex p = data.get(position);
        holder.nombreTextView.setText(p.getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPokemonEspecifico(p.getName());
            }
        });
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void adicionarListaPokemon(ArrayList<PokemonPokedex> list) {
        data.addAll(list);
        dataNew.addAll(list);
        notifyDataSetChanged();
    }

    public void filtrado(String cadenaTexto) {
        int longitud = cadenaTexto.length();
        if (longitud == 0) {
            data.clear();
            data.addAll(dataNew);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<PokemonPokedex> listaPokemon = data.stream()
                        .filter(i -> i.getName().toLowerCase().contains(cadenaTexto.toLowerCase()))
                        .collect(Collectors.toList());
                data.clear();
                data.addAll(listaPokemon);

            } else {
                for (PokemonPokedex pp: dataNew) {
                    if (pp.getName().toLowerCase().contains(cadenaTexto.toLowerCase())) {
                        data.add(pp);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView fotoImageView;
        public TextView nombreTextView;
        public LinearLayout item;

        public ViewHolder(View itemView) {
            super (itemView);

            fotoImageView = itemView.findViewById(R.id.fotoImagePokedex);
            nombreTextView = itemView.findViewById(R.id.nombrePokedex);

            item = itemView.findViewById(R.id.item);
        }
    }

    public void getPokemonEspecifico(String nombre) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService pokeApiService = retrofit.create(PokeApiService.class);

        Call<PokemonModel> pokemonEspecificoCall = pokeApiService.obtenerPokemonEspecifico(nombre);
        pokemonEspecificoCall.enqueue(new Callback<PokemonModel>() {
            @Override
            public void onResponse(Call<PokemonModel> call, Response<PokemonModel> response) {
                if (response.isSuccessful()) {
                    pokemonModel = response.body();
                    Intent intentDetalle = new Intent(context, Detalle.class);
                    String jsonString = gson.toJson(pokemonModel);
                    intentDetalle.putExtra("key", jsonString);
                    context.startActivity(intentDetalle);
                } else {
                    Toast.makeText(context.getApplicationContext(), "Error en la consulta:" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonModel> call, Throwable t) {

            }
        });
    }
}
