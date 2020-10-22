package com.example.cinecartelera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptadorList extends BaseAdapter {

    private Context context;
    private ArrayList<pelicula> listPeliculas;
    public adaptadorList(Context context, ArrayList<pelicula> listPeliculas){
        this.context = context;
        this.listPeliculas = listPeliculas;
    }

    @Override
    public int getCount() {
        return listPeliculas.size();
    }

    @Override
    public Object getItem(int i) {
        return listPeliculas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       pelicula peli = (pelicula) getItem(i);

       view = LayoutInflater.from(context).inflate(R.layout.pelicula_item_lista,null);
       TextView tvTitulo_list = (TextView) view.findViewById(R.id.tvTitulo);
       TextView tvCat_list = (TextView) view.findViewById(R.id.tvCategoria);
       ImageView imagen_list = (ImageView) view.findViewById(R.id.imageViewPelicula);
       tvTitulo_list.setText(peli.getTitulo());
       tvCat_list.setText(peli.getCategoria());
       imagen_list.setImageResource(peli.getImagen());
       return view;
    }
}