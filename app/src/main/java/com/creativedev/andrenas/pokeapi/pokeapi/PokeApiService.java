package com.creativedev.andrenas.pokeapi.pokeapi;

import com.creativedev.andrenas.pokeapi.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService {

    /* */

    @GET("pokemon")
    Call<PokemonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
