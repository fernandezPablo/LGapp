package com.pablo.lgapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pablo.lgapp.Controller.NoticiasController;
import com.pablo.lgapp.Model.AsynTaskNoti;
import com.pablo.lgapp.Model.Noticia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NoticiasController nc;
    /*
    * Esta variable es usada para controlar el boton back
    * ESTADO:
    * Principal = la app se encuentra en la pantallla principal mostrando listado de noticias
    * Noticia = la app se encuentra mostrando una noticia
    * */
    private String estado;
    private ProgressBar pbCargando;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nc = new NoticiasController(this);
        AsynTaskNoti taskNoti = new AsynTaskNoti(nc, "Portada");
        taskNoti.execute();
    }


    @Override
    public void onBackPressed() {
        if (this.estado.equals("Principal")) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } else if (this.estado.equals("Noticia")) {
            AsynTaskNoti taskNoti = new AsynTaskNoti(this.nc, "Portada");
            taskNoti.execute();
            this.pbCargando = new ProgressBar(this);
            ((LinearLayout) findViewById(R.id.main_layout)).removeAllViews();
            ((LinearLayout) findViewById(R.id.main_layout)).addView(this.pbCargando);
            this.estado = "Principal";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_portada) {
            AsynTaskNoti taskNoti = new AsynTaskNoti(this.nc, "Portada");
            taskNoti.execute();
            this.pbCargando = new ProgressBar(this);
            ((LinearLayout) findViewById(R.id.main_layout)).removeAllViews();
            ((LinearLayout) findViewById(R.id.main_layout)).addView(this.pbCargando);
            Log.d("Resultados","Probando git...");
        } else if (id == R.id.nav_policiales) {
            Toast.makeText(this, "Policiales", Toast.LENGTH_LONG);
        } else if (id == R.id.nav_economia) {
            Toast.makeText(this, "Economia", Toast.LENGTH_LONG);
        } else if (id == R.id.nav_deportes) {
            Toast.makeText(this, "Deportes", Toast.LENGTH_LONG);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
