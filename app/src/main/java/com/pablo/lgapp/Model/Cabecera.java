package com.pablo.lgapp.Model;

/**
 * Created by pablo on 12/06/2016.
 */
public class Cabecera {
    String titulo;
    String url;

    public Cabecera() {
    }

    public Cabecera(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Cabecera{" +
                "titulo='" + titulo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
