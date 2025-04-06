package com.example.petagram;

public class Mascota {
    private String nombre;
    private int foto;
    private int rating;

    public Mascota(String nombre, int foto, int rating) {
        this.nombre = nombre;
        this.foto = foto;
        this.rating = rating;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFoto() {
        return foto;
    }

    public int getRating() {
        return rating;
    }

    public void darLike() {
        this.rating++;
    }
}