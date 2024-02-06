package com.example.apipokee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.apipokee.modelos.PokemonModel;
import com.example.apipokee.modelos.Stat;
import com.example.apipokee.modelos.Type;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detalle extends AppCompatActivity {

    private PokemonModel pokemonModel;
    private Gson gson = new Gson();
    private TextView lblTipo1;
    private TextView txtStat;
    private ArrayList<Integer> textIds = new ArrayList<>();
    private ArrayList<Integer> txtEstadisticas = new ArrayList<>();
    Map<String, Integer> colorTypes = new HashMap<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        context = getApplicationContext();

        textIds.add(R.id.idTipo1);
        textIds.add(R.id.idTipo2);

        txtEstadisticas.add(R.id.txtVida);
        txtEstadisticas.add(R.id.txtAtaque);
        txtEstadisticas.add(R.id.txtDefensa);
        txtEstadisticas.add(R.id.txtAtqEspecial);
        txtEstadisticas.add(R.id.txtDefEspecial);
        txtEstadisticas.add(R.id.txtVelocidad);

        llenarColorTypes();

        pokemonModel = gson.fromJson(value, PokemonModel.class);
        ArrayList<Type> tipo = pokemonModel.getTypes();

        for (Integer i = 0; i < tipo.size(); i++) {
            lblTipo1 = findViewById(textIds.get(i));
            lblTipo1.setText(tipo.get(i).getType().getName());
            lblTipo1.setVisibility(View.VISIBLE);
            Drawable background = lblTipo1.getBackground();
            GradientDrawable shapeDrawable = (GradientDrawable) background;
            shapeDrawable.setColor(colorTypes.get(tipo.get(i).getType().getName()));
        }
        ArrayList<Stat> estadisticas = pokemonModel.getStats();
        for (Integer i = 0; i < estadisticas.size(); i++) {
            txtStat = findViewById(txtEstadisticas.get(i));
            txtStat.setText(String.valueOf(estadisticas.get(i).getBaseStat()));
        }

        ImageView img = findViewById(R.id.imageSprite);

        Glide.with(context)
                .load(pokemonModel.getSprites().getFrontDefault())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
    }

    public void llenarColorTypes() {
        colorTypes.put("fire", Color.RED);
        colorTypes.put("water", Color.rgb(102,194,255));
        colorTypes.put("normal", Color.WHITE);
        colorTypes.put("grass", Color.GREEN);
        colorTypes.put("ghost", Color.rgb(204,204,204));
        colorTypes.put("rock", Color.rgb(153,51,0));
        colorTypes.put("poison", Color.rgb(153,0,255));
        colorTypes.put("dragon", Color.rgb(51,51,153));
        colorTypes.put("dark", Color.rgb(51,51,51));
        colorTypes.put("ground", Color.rgb(255,102,0));
        colorTypes.put("bug", Color.rgb(128,128,0));
        colorTypes.put("ice", Color.rgb(204,255,255));
        colorTypes.put("steel", Color.rgb(115,115,115));
        colorTypes.put("electric", Color.YELLOW);
        colorTypes.put("psychic", Color.rgb(255,153,204));
        colorTypes.put("fighting", Color.rgb(255,83,26));
        colorTypes.put("fairy", Color.rgb(255,204,153));
        colorTypes.put("flying", Color.rgb(179,255,255));
    }
}