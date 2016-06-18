package com.pablo.lgapp.Controller;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.pablo.lgapp.MainActivity;
import com.pablo.lgapp.Model.AsynTaskNoti;
import com.pablo.lgapp.Model.Noticia;
import com.pablo.lgapp.R;

import java.util.ArrayList;

/**
 * Created by pablo on 12/06/2016.
 */
public class NoticiasController implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MainActivity mainView;
    private ArrayList<Noticia> news;

    public NoticiasController(MainActivity mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onClick(View v) {
        Log.d("Resultados", "click");
    }

    public void cargarListaNoticias(ArrayList<Noticia> noticias) {
        Log.d("Resultados", "Cargando " + noticias.size() + " Noticias ");
        String[] titulos = new String[noticias.size()];
        int i = 0;
        for (Noticia noti : noticias) {
            titulos[i] = noti.getCabecera().getTitulo();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainView,
                android.R.layout.simple_list_item_1,
                titulos);

        TextView tvHead = new TextView(mainView);
        tvHead.setText("Noticias LA GACETEA");
        tvHead.setTextSize(25);
        Typeface tfhead = Typeface.create("Arial", Typeface.BOLD);
        tvHead.setTypeface(tfhead);
        tvHead.setTextColor(Color.WHITE);
        tvHead.setBackgroundColor(Color.BLUE);
        tvHead.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ListView lvNoticias = new ListView(mainView);
        lvNoticias.setAdapter(adapter);
        lvNoticias.setOnItemClickListener(this);
        LinearLayout linearLayout = (LinearLayout) mainView.findViewById(R.id.main_layout);
        linearLayout.removeAllViews();
        linearLayout.addView(tvHead);
        linearLayout.addView(lvNoticias);
        this.news = noticias;
        this.mainView.setEstado("Principal");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Toast.makeText(mainView, this.news.get(position).getCabecera().getUrl(), Toast.LENGTH_SHORT).show();
        LinearLayout linearLayout = (LinearLayout) mainView.findViewById(R.id.main_layout);
        linearLayout.removeAllViews();
        ProgressBar pbCargandoNoticia = new ProgressBar(mainView);
        linearLayout.addView(pbCargandoNoticia);
        AsynTaskNoti taskNoti = new AsynTaskNoti(this, "LecturaNoticia");
        taskNoti.execute(news.get(position));

    }

    public void cargarNoticia(Noticia noticia) {
        Log.d("Resultados", "Cargando noticia...");
        LinearLayout linearLayout = (LinearLayout) mainView.findViewById(R.id.main_layout);
        linearLayout.removeAllViews();
        TextView tvTitulo = new TextView(mainView);
        Typeface tfTitulo = Typeface.create("Arial", Typeface.BOLD);
        tvTitulo.setTypeface(tfTitulo);
        tvTitulo.setTextSize(20);
        tvTitulo.setText(noticia.getCabecera().getTitulo());
        tvTitulo.setTextColor(Color.BLACK);
        linearLayout.addView(tvTitulo);
        TextView tvCuerpo = new TextView(mainView);
        Log.d("Resultados", "Noticia: " + noticia.getCuerpo());
        tvCuerpo.setText(noticia.getCuerpo());
        tvCuerpo.setTextSize(15);
        ScrollView svNoti = new ScrollView(mainView);
        linearLayout.addView(svNoti);
        svNoti.addView(tvCuerpo);
        this.mainView.setEstado("Noticia");
    }

}
