package com.pablo.lgapp.Model;


/**
 * Created by pablo on 11/06/2016.
 */
public class Noticia {

    private Cabecera cabecera;
    private String cuerpo;
    private String fecha;

    public Noticia() {
        //constructor vacio
    }

    public Noticia(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public Noticia(Cabecera cabecera, String cuerpo, String fecha) {
        this.cabecera = cabecera;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
    }

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "Noticia{" +
                "encabezado='" + cabecera.toString()+ '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
