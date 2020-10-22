package com.example.cinecartelera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class pelicula_detalle extends AppCompatActivity {

    private TextView tvTitulo, tvCat, tvFecha, tvDuracion, tvPrecio;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_detalle);

        Bundle datos = this.getIntent().getExtras();
        String titulo = datos.getString("titulo");
        String categoria = datos.getString("categoria");
        String duracion = datos.getString("duracion");
        String fecha = datos.getString("fecha");
        String precio = datos.getString("precio");
        String trailer = datos.getString("trailer");

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvCat = (TextView) findViewById(R.id.tvCat);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvDuracion = (TextView) findViewById(R.id.tvDuracion);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        webView = (WebView) findViewById(R.id.web);

        tvTitulo.setText(titulo);
        tvCat.setText(categoria);
        tvDuracion.setText(duracion);
        tvFecha.setText(fecha);
        tvPrecio.setText(precio);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(trailer);

    }

    public void onClickVolver(View v){
        finish();
    }
}