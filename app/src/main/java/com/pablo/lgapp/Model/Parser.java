package com.pablo.lgapp.Model;


import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pablo on 11/06/2016.
 */
public class Parser {

    private Document documento;
    private ArrayList<Noticia> noticias;

    public Parser() {
        //Constructor vacio
    }

    public Parser(Document documento, ArrayList<Noticia> noticias) {
        this.documento = documento;
        this.noticias = noticias;
    }

    public Document getDocumento() {
        return documento;
    }

    public void setDocumento(Document documento) {
        this.documento = documento;
    }

    public ArrayList<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticia(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    public ArrayList<Noticia> generarNoticias(String categoria) {
        ArrayList<Noticia> noticias = new ArrayList<>();
        Log.d("Resultados", "Parseando...");
        switch (categoria) {
            case "Portada":
                Log.d("Resultados", "Buscando la portada...");
                try {
                    this.documento = Jsoup.connect("http://www.lagaceta.com.ar")
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
                            .timeout(60 * 1000)
                            .get();
                    Log.d("Resultados", this.documento.title());
                    ArrayList<Cabecera> cabeceras = ProcesarDocumentos.extraerCabeceras(this.documento);
                    for (Cabecera cabecera : cabeceras) {
                        noticias.add(new Noticia(cabecera));
                    }
                } catch (IOException e) {
                    Log.d("Excepciones", e.getMessage());
                }
                break;
            case "Policiales":
                break;
            case "Economia":
                break;
            case "Deportes":
                break;
        }
        return noticias;
    }

    public Noticia ponerCuerpoNoticia(Noticia noticia) {
        //Noticia noti = noticia;
        try {
            this.documento = Jsoup.connect(noticia.getCabecera().getUrl())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
                    .timeout(60 * 1000)
                    .get();
            noticia.setCuerpo(ProcesarDocumentos.extraerCuerpo(this.documento));
            Log.d("Resultados", noticia.getCuerpo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noticia;
    }


}
