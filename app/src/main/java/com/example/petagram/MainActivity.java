package com.example.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotas;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar miActionBar = findViewById(R.id.miActionBar);
        //setSupportActionBar(miActionBar);

        prefs = getSharedPreferences("petagram", Context.MODE_PRIVATE);

        rvMascotas = findViewById(R.id.rvMascotas);
        rvMascotas.setLayoutManager(new LinearLayoutManager(this));

        inicializarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Bobby", R.drawable.mascota1, prefs.getInt("Bobby", 0)));
        mascotas.add(new Mascota("Max", R.drawable.mascota2, prefs.getInt("Max", 0)));
        mascotas.add(new Mascota("Luna", R.drawable.mascota3, prefs.getInt("Luna", 0)));
        mascotas.add(new Mascota("Rocky", R.drawable.mascota4, prefs.getInt("Rocky", 0)));
        mascotas.add(new Mascota("Daisy", R.drawable.mascota5, prefs.getInt("Daisy", 0)));
    }

    public void inicializarAdaptador() {
        MascotaAdapter adaptador = new MascotaAdapter(mascotas, this, prefs);
        rvMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.action_favorites) {
            startActivity(new Intent(this, FavoritasActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}