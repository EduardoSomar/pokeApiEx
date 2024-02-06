package com.example.apipokee.modelos;

import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("back_default")
    private String backDefault;

    @SerializedName("back_female")
    private Object backFemale;

    @SerializedName("back_shiny")
    private String backShiny;

    @SerializedName("back_shiny_female")
    private Object backShinyFemale;

    @SerializedName("front_default")
    private String frontDefault;

    @SerializedName("front_female")
    private Object frontFemale;

    @SerializedName("front_shiny")
    private String frontShiny;

    @SerializedName("front_shiny_female")
    private Object frontShinyFemale;

    @SerializedName("")
    private Sprites animated;

    public String getBackDefault() { return backDefault; }
    public void setBackDefault(String value) { this.backDefault = value; }

    public Object getBackFemale() { return backFemale; }
    public void setBackFemale(Object value) { this.backFemale = value; }

    public String getBackShiny() { return backShiny; }
    public void setBackShiny(String value) { this.backShiny = value; }

    public Object getBackShinyFemale() { return backShinyFemale; }
    public void setBackShinyFemale(Object value) { this.backShinyFemale = value; }

    public String getFrontDefault() { return frontDefault; }
    public void setFrontDefault(String value) { this.frontDefault = value; }

    public Object getFrontFemale() { return frontFemale; }
    public void setFrontFemale(Object value) { this.frontFemale = value; }

    public String getFrontShiny() { return frontShiny; }
    public void setFrontShiny(String value) { this.frontShiny = value; }

    public Object getFrontShinyFemale() { return frontShinyFemale; }
    public void setFrontShinyFemale(Object value) { this.frontShinyFemale = value; }

    public Sprites getAnimated() { return animated; }
    public void setAnimated(Sprites value) { this.animated = value; }
}
