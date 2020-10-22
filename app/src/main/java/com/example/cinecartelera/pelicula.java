package com.example.cinecartelera;
public class pelicula {

    private String titulo, categoria, duracion, precioTicket, trailerUrl,fechaEstreno;
    private int imagen;

    public pelicula(String titulo, String categoria, String fechaEstreno, String duracion, String precioTicket, int imagen, String trailerUrl){
        this.titulo = titulo;
        this.categoria = categoria;
        this.fechaEstreno = fechaEstreno;
        this.duracion = duracion;
        this.precioTicket = precioTicket;
        this.imagen = imagen;
        this.trailerUrl = trailerUrl;
    }

    public String getTitulo() {return titulo;}
    public String getCategoria() { return categoria;}
    public String getDuracion() { return duracion;}
    public String getPrecioTicket() {return precioTicket;}
    public int getImagen() {return imagen;}
    public String getTrailerUrl() {return trailerUrl;}
    public String getFechaEstreno() {return fechaEstreno;}

    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setCategoria(String categoria) { this.categoria = categoria;}
    public void setDuracion(String duracion) { this.duracion = duracion;}
    public void setPrecioTicket(String precioTicket) {this.precioTicket = precioTicket;}
    public void setImagen(int imagen) {this.imagen = imagen;}
    public void setTrailerUrl(String trailerUrl) {this.trailerUrl = trailerUrl;}
    public void setFechaEstreno(String fechaEstreno) {this.fechaEstreno = fechaEstreno;}

}
