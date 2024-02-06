package com.example.apipokee.services;

import com.example.apipokee.modelos.PokemonModel;
import com.example.apipokee.modelos.PokemonPages;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonPages> obtenerListaPokemonPages(
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("pokemon/{pokemon}")
    Call<PokemonModel> obtenerPokemonEspecifico(
            @Path("pokemon") String pokemon
    );
}
