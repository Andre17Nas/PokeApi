package com.creativedev.andrenas.pokeapi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.creativedev.andrenas.pokeapi.models.Pokemon;
import com.creativedev.andrenas.pokeapi.models.PokemonResponse;
import com.creativedev.andrenas.pokeapi.pokeapi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /* Here a create retrofit instance global */

    public Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListPokemonAdapter listPokemonAdapter;

    private int offset;
    private boolean acceptLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listPokeView);
        listPokemonAdapter = new ListPokemonAdapter(this);
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    int visibleitemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (acceptLoad){
                        if ((visibleitemCount + pastVisibleItem ) >= totalItemCount ){
                            acceptLoad = false;
                            offset += 20;
                            getDataAPI(offset);
                        }
                    }

                }

            }
        });




        /* now i set the baseurl and i converter all datas for gson */

        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();

        acceptLoad = true;
        offset = 0;
        getDataAPI(offset);

    }

    /* this method will do call assync from API */
    private void getDataAPI(int offset){
        PokeApiService pokeApiService = retrofit.create(PokeApiService.class);
        Call<PokemonResponse> pokemonResponseCall = pokeApiService.getListPokemon(20, offset);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                acceptLoad = true;
                    if (response.isSuccessful()){
                        PokemonResponse pokemonResponse = response.body();
                        ArrayList<Pokemon> listPokemons = pokemonResponse.getResults();

                        listPokemonAdapter.addListPokemon(listPokemons);

                    }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
        });

    }

}
