package com.creativedev.andrenas.pokeapi.models;

public class Pokemon {

    private int number;
    private String name;
    private String url;

   /* GETTERS and SETTERS */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlpartes = url.split("/");
        return Integer.parseInt(urlpartes[urlpartes.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
