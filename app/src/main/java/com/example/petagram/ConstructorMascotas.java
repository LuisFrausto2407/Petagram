package com.example.petagram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ConstructorMascotas {

    private BaseDatosHelper dbHelper;

    public ConstructorMascotas(Context context) {
        dbHelper = new BaseDatosHelper(context);
    }

    public void insertarMascota(Mascota mascota) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BaseDatosHelper.TABLE_MASCOTAS + " WHERE nombre = ?", new String[]{mascota.getNombre()});

        if (cursor.moveToFirst()) {
            int rating = cursor.getInt(cursor.getColumnIndexOrThrow(BaseDatosHelper.COLUMN_RATING)) + 1;
            ContentValues values = new ContentValues();
            values.put(BaseDatosHelper.COLUMN_RATING, rating);
            db.update(BaseDatosHelper.TABLE_MASCOTAS, values, "nombre = ?", new String[]{mascota.getNombre()});
        } else {
            ContentValues values = new ContentValues();
            values.put(BaseDatosHelper.COLUMN_NOMBRE, mascota.getNombre());
            values.put(BaseDatosHelper.COLUMN_RATING, 1);
            values.put(BaseDatosHelper.COLUMN_FOTO, mascota.getFoto());
            db.insert(BaseDatosHelper.TABLE_MASCOTAS, null, values);
        }

        cursor.close();
        db.close();
    }

    public int obtenerRating(String nombre) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + BaseDatosHelper.COLUMN_RATING +
                " FROM " + BaseDatosHelper.TABLE_MASCOTAS +
                " WHERE nombre = ?", new String[]{nombre});
        int rating = 0;
        if (cursor.moveToFirst()) {
            rating = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return rating;
    }

    public ArrayList<Mascota> obtenerTopMascotas() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(BaseDatosHelper.TABLE_MASCOTAS,
                null, null, null, null, null,
                BaseDatosHelper.COLUMN_RATING + " DESC", "5");

        ArrayList<Mascota> favoritas = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(BaseDatosHelper.COLUMN_NOMBRE));
                int rating = cursor.getInt(cursor.getColumnIndexOrThrow(BaseDatosHelper.COLUMN_RATING));
                int foto = cursor.getInt(cursor.getColumnIndexOrThrow(BaseDatosHelper.COLUMN_FOTO));
                favoritas.add(new Mascota(nombre, foto, rating));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return favoritas;
    }
}