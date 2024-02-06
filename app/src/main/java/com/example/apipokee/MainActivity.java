package com.example.apipokee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.apipokee.adapters.ListaPokemonAdapter;
import com.example.apipokee.modelos.PokemonPages;
import com.example.apipokee.modelos.PokemonPokedex;
import com.example.apipokee.services.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;

    private SearchView txtBuscar;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    private int offset;
    private boolean carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBuscar = findViewById(R.id.txtBuscar);
        recyclerView = findViewById(R.id.recyclerViewPokedex);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (carga) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            carga = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });

        txtBuscar.setOnQueryTextListener(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        carga = true;
        offset = 0;
        obtenerDatos(offset);


    }

    private void obtenerDatos(int offset) {
        PokeApiService pokeApiService = retrofit.create(PokeApiService.class);
        Call<PokemonPages> pokemonPagesCall = pokeApiService.obtenerListaPokemonPages(1302, offset);

        pokemonPagesCall.enqueue(new Callback<PokemonPages>() {
            @Override
            public void onResponse(Call<PokemonPages> call, Response<PokemonPages> response) {
                carga = true;
                if (response.isSuccessful()) {
                    PokemonPages pokemonPages = response.body();
                    ArrayList<PokemonPokedex> lista = pokemonPages.getResults();

                    listaPokemonAdapter.adicionarListaPokemon(lista);
                } else {
                    Toast.makeText(getApplicationContext(), "Error en la consulta:" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonPages> call, Throwable t) {
                carga = false;
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        listaPokemonAdapter.filtrado(s);
        return false;
    }
}