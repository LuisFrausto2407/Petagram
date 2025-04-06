package com.example.petagram;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;
    SharedPreferences prefs;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity, SharedPreferences prefs) {
        this.mascotas = mascotas;
        this.activity = activity;
        this.prefs = prefs;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRating.setText(String.valueOf(mascota.getRating()));

        holder.btnLike.setOnClickListener(v -> {
            mascota.darLike();
            holder.tvRating.setText(String.valueOf(mascota.getRating()));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(mascota.getNombre(), mascota.getRating());
            editor.apply();
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView tvNombre;
        TextView tvRating;
        ImageView btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRating = itemView.findViewById(R.id.tvRating);
            btnLike = itemView.findViewById(R.id.btnLike);
        }
    }
}