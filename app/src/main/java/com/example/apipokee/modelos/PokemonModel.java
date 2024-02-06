package com.example.apipokee.modelos;

import java.util.ArrayList;
import java.util.List;

public class PokemonModel {

    private ArrayList<Ability> abilities;
    private long height;
    private ArrayList<Object> heldItems;
    private long id;
    private String name;
    private long order;
    private Sprites sprites;
    private ArrayList<Stat> stats;
    private ArrayList<Type> types;


    public ArrayList<Ability> getAbilities() { return abilities; }
    public void setAbilities(ArrayList<Ability> value) { this.abilities = value; }

    public long getHeight() { return height; }
    public void setHeight(long value) { this.height = value; }

    public ArrayList<Object> getHeldItems() { return heldItems; }
    public void setHeldItems(ArrayList<Object> value) { this.heldItems = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getOrder() { return order; }
    public void setOrder(long value) { this.order = value; }

    public Sprites getSprites() { return sprites; }
    public void setSprites(Sprites value) { this.sprites = value; }

    public ArrayList<Stat> getStats() { return stats; }
    public void setStats(ArrayList<Stat> value) { this.stats = value; }

    public ArrayList<Type> getTypes() { return types; }
    public void setTypes(ArrayList<Type> value) { this.types = value; }

}




