package com.example.cinecartelera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class cartelera extends AppCompatActivity {

    private ListView listPeliculas;
    private TextView tv;
    private adaptadorList adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartelera);

        tv = (TextView) findViewById(R.id.tvDuracion);
        listPeliculas = (ListView) findViewById(R.id.lv);
        Bundle datos = this.getIntent().getExtras();
        String nombre = datos.getString("nombrePersona");
        tv.setText(nombre+", aquí tiene el listado de películas.");
        llenarListaCartelera();

        listPeliculas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                irAlDetalle(position);
            }
        });

       listPeliculas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(cartelera.this);
                dialogo1.setTitle(getString(R.string.atencion));
                dialogo1.setMessage(getString(R.string.eliminarPelicula));
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton(getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        eliminarPelicula(position);
                    }
                });

                dialogo1.setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        //lo q quiera hacer
    }

    public void eliminarPelicula(int i){
        pelicula pelicula = (pelicula) (listPeliculas.getItemAtPosition(i));
        String titulo = pelicula.getTitulo();
        String categoria = pelicula.getCategoria();
        String duracion = pelicula.getDuracion();
        String precioTicket = pelicula.getPrecioTicket();
        int imagen = pelicula.getImagen();
        String trailerUrl = pelicula.getTrailerUrl();
        String fechaEstreno = pelicula.getFechaEstreno();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        baseDeDatos.delete("cartelera", "titulo = '"+titulo+"' and categoria = '"+categoria+"' and duracion = '"+duracion
                        +"' and precioTicket = '"+precioTicket+"'and imagen = "+imagen+" and trailerUrl = '"+trailerUrl+"'and fechaEstreno = '"+fechaEstreno+"'", null);
        llenarListaCartelera();
    }

    public void llenarListaCartelera(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        Cursor filas = baseDeDatos.rawQuery("select titulo, categoria, duracion, precioTicket, imagen, trailerUrl, fechaEstreno, id from cartelera", null);

        ArrayList<pelicula> peliculas = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

        for (int i = 0 ; i < filas.getCount(); i++) {
            filas.moveToPosition(i);
            String tmp_titulo = filas.getString(0);
            String tmp_categoria = filas.getString(1);
            String tmp_duracion = filas.getString(2);
            String tmp_precioTicket = filas.getString(3);
            int tmp_imagen = filas.getInt(4);
            String tmp_trailerUrl = filas.getString(5);
            String tmp_fechaEstreno = filas.getString(6);

            pelicula peli = new pelicula(tmp_titulo,tmp_categoria,tmp_fechaEstreno,tmp_duracion,tmp_precioTicket,tmp_imagen,tmp_trailerUrl);
            peliculas.add(peli);
        }
        baseDeDatos.close();
        adaptador = new adaptadorList(this, peliculas);
        listPeliculas.setAdapter(adaptador);
    }

    public void onActionMasInfo(View view){
        Intent i = new Intent(this, informacionAPP.class);
        startActivity(i);
    }

    public void onActionCerrarSesion(View view){
        finish();
    }

    public void irAlDetalle(int position){
        pelicula pelicula = (pelicula) (listPeliculas.getItemAtPosition(position));
        Intent i = new Intent(this, pelicula_detalle.class);
        String titulo = pelicula.getTitulo();
        String categoria = pelicula.getCategoria();
        String duracion = pelicula.getDuracion();
        String fecha = pelicula.getFechaEstreno();
        String precio = pelicula.getPrecioTicket();
        String trailer = pelicula.getTrailerUrl();

        i.putExtra("titulo", titulo);
        i.putExtra("categoria", categoria);
        i.putExtra("duracion", duracion);
        i.putExtra("fecha", fecha);
        i.putExtra("precio", precio);
        i.putExtra("trailer", trailer);
        startActivity(i);
    }

}