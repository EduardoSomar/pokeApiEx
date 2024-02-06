package com.example.apipokee.modelos;

public class PokemonPokedex {
    private int number;
    private String name;
    private String url;

    public int getNumber() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
