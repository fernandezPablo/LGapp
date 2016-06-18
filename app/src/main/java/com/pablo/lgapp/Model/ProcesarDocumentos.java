package com.pablo.lgapp.Model;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by pablo on 12/06/2016.
 */
public class ProcesarDocumentos {

    public static ArrayList<Cabecera> extraerCabeceras(Document documento) {
        ArrayList<Cabecera> cabeceras = new ArrayList<>();
        Cabecera cab;
        String clase;
        Set<String> classnames;
        Iterator<String> ite;
        //Headers de nivel 1
        Elements h1s = documento.getElementsByTag("h1");
        for (Element h1 : h1s) {
            //se extraen todas las clases que implementa el header
            classnames = h1.classNames();
            //se crea un iterador con todos los nombres de clases extraidas
            ite = classnames.iterator();
            while (ite.hasNext()) {
                //una de las clases extraidas
                clase = ite.next();
                //se verifica si los headers son noticias
                if (clase.equals("frob") || clase.equals("tit")) {
                    cab = new Cabecera(h1.text(), h1.child(0).attr("href"));
                    cabeceras.add(cab);
                }
            }
        }
        return cabeceras;
    }

    public static String extraerCuerpo(Document documento) {
        Elements div = documento.getElementsByClass("nttext");
        Elements parrafos = div.get(0).getElementsByTag("p");
        String cuerpo = "";
        for (Element parrafo : parrafos) {
            Log.d("Resultados", parrafo.text());
            cuerpo += parrafo.text() + "\n";
        }
        Log.d("Resultados", "Cuerpo: " + cuerpo);
        return cuerpo;
    }
}
