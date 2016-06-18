package com.pablo.lgapp.Model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.pablo.lgapp.Controller.NoticiasController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pablo on 12/06/2016.
 */
public class AsynTaskNoti extends AsyncTask<Object, Void, ArrayList<Noticia>> {

    private NoticiasController nc;
    private String filtro;

    public AsynTaskNoti(NoticiasController nc, String filtro) {
        this.nc = nc;
        this.filtro = filtro;
    }

    @Override
    protected ArrayList<Noticia> doInBackground(Object... params) {
        Parser parser = new Parser();
        switch (filtro) {
            case "Portada":
                return parser.generarNoticias("Portada");
            case "LecturaNoticia":
                ArrayList<Noticia> noticias = new ArrayList<>();
                noticias.add(parser.ponerCuerpoNoticia((Noticia) params[0]));
                return noticias;
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Noticia> result) {
        super.onPostExecute(result);
        switch (filtro) {
            case "Portada":
                this.nc.cargarListaNoticias(result);
                break;
            case "LecturaNoticia":
                Log.d("Resultados", result.get(0).getCuerpo());
                this.nc.cargarNoticia(result.get(0));
                break;
        }
    }
}
