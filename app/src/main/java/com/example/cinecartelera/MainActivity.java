package com.example.cinecartelera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText)findViewById(R.id.etNombre);

        llenarBD();
    }

    public void onActionIngresar(View view){
        if(etNombre.getText().length() <= 0){
            Toast.makeText(this, getString(R.string.ingreseNombre), Toast.LENGTH_SHORT).show();
            return;
        }
        String nombre = etNombre.getText().toString();
        etNombre.setText("");
        Intent i = new Intent(this, cartelera.class);
        i.putExtra("nombrePersona", nombre);
        startActivity(i);
    }

    public void llenarBD(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        //primero truncamos la tabla
        baseDeDatos.delete("cartelera", null, null);


        ContentValues registro1 = new ContentValues();
        registro1.put("titulo","Counter Strike: La Película"); registro1.put("categoria","Acción"); registro1.put("fechaEstreno", "20/10/2021");
        registro1.put("duracion", "2:40 hs"); registro1.put("precioTicket", "$310"); registro1.put("imagen", R.drawable.counter); registro1.put("trailerUrl", "https://www.youtube.com/embed/0IF7-WD3TPY?autoplay=1&vq=small");
        baseDeDatos.insert("cartelera",null,registro1);

        ContentValues registro2 = new ContentValues();
        registro2.put("titulo","President Evil"); registro2.put("categoria","Thriller"); registro2.put("fechaEstreno", "10/6/2021");
        registro2.put("duracion", "2:10 hs"); registro2.put("precioTicket", "$290"); registro2.put("imagen", R.drawable.president); registro2.put("trailerUrl", "https://www.youtube.com/embed/lAvv-zPLurs?autoplay=1&vq=small");
        baseDeDatos.insert("cartelera",null,registro2);

        ContentValues registro3 = new ContentValues();
        registro3.put("titulo","Star Wars: Terminator Contraataca"); registro3.put("categoria","Ciencia Ficción"); registro3.put("fechaEstreno", "15/10/2022");
        registro3.put("duracion", "4:20 hs"); registro3.put("precioTicket", "$850"); registro3.put("imagen", R.drawable.starwars); registro3.put("trailerUrl", "https://www.youtube.com/embed/o56ubcfwSzY?autoplay=1&vq=small");
        baseDeDatos.insert("cartelera",null,registro3);

        ContentValues registro4 = new ContentValues();
        registro4.put("titulo","El Robo del Siglo"); registro4.put("categoria","Drama"); registro4.put("fechaEstreno", "20/4/2021");
        registro4.put("duracion", "1:30 hs"); registro4.put("precioTicket", "$300"); registro4.put("imagen", R.drawable.robo); registro4.put("trailerUrl", "https://www.youtube.com/embed/rLk1KkEul3I?autoplay=1&vq=small");
        baseDeDatos.insert("cartelera",null,registro4);

        ContentValues registro5 = new ContentValues();
        registro5.put("titulo","Sonic: La Película"); registro5.put("categoria","Acción/Animada"); registro5.put("fechaEstreno", "10/9/2019");
        registro5.put("duracion", "1:30 hs"); registro5.put("precioTicket", "$15"); registro5.put("imagen", R.drawable.sonic); registro5.put("trailerUrl", "https://www.youtube.com/embed/mIgGCaIwdXU?autoplay=1&vq=small");
        baseDeDatos.insert("cartelera",null,registro5);

        baseDeDatos.close();

    }
}