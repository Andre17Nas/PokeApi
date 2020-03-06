package com.creativedev.andrenas.pokeapi.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PokemonResponse {

    private ArrayList<Pokemon> results;

    /* GETTERS and SETTERS */

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
