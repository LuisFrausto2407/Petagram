package com.example.petagram;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FavoritasActivity extends AppCompatActivity {

    RecyclerView rvFavoritas;
    ArrayList<Mascota> favoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        androidx.appcompat.widget.Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvFavoritas = findViewById(R.id.rvFavoritas);
        rvFavoritas.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = getSharedPreferences("petagram", Context.MODE_PRIVATE);
        favoritas = new ArrayList<>();
        favoritas.add(new Mascota("Bobby", R.drawable.mascota1, prefs.getInt("Bobby", 0)));
        favoritas.add(new Mascota("Max", R.drawable.mascota2, prefs.getInt("Max", 0)));
        favoritas.add(new Mascota("Luna", R.drawable.mascota3, prefs.getInt("Luna", 0)));
        favoritas.add(new Mascota("Rocky", R.drawable.mascota4, prefs.getInt("Rocky", 0)));
        favoritas.add(new Mascota("Daisy", R.drawable.mascota5, prefs.getInt("Daisy", 0)));

        Collections.sort(favoritas, (a, b) -> Integer.compare(b.getRating(), a.getRating()));
        if (favoritas.size() > 5) {
            favoritas = new ArrayList<>(favoritas.subList(0, 5));
        }

        MascotaAdapter adaptador = new MascotaAdapter(favoritas, this, prefs);
        rvFavoritas.setAdapter(adaptador);
    }
}