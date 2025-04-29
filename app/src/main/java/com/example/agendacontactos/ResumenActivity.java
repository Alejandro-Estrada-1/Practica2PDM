package com.example.agendacontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ResumenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvNombre, tvCorreo, tvTelefono, tvGenero, tvAcepto;
    private Button btnVolver;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        // Configurar Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Enlazar vistas
        tvNombre = findViewById(R.id.tvNombre);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvGenero = findViewById(R.id.tvGenero);
        tvAcepto = findViewById(R.id.tvAcepto);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener datos enviados desde RegistroActivity
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String correo = intent.getStringExtra("correo");
            String telefono = intent.getStringExtra("telefono");
            String genero = intent.getStringExtra("genero");
            boolean acepto = intent.getBooleanExtra("acepto", false);

            tvNombre.setText("Nombre: " + nombre);
            tvCorreo.setText("Correo: " + correo);
            tvTelefono.setText("Teléfono: " + telefono);
            tvGenero.setText("Género: " + genero);
            tvAcepto.setText("Aceptó los términos: " + (acepto ? "Sí" : "No"));
        }

        // Botón Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Crear el menú de los tres puntitos (ActionBar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resumen, menu);
        return true;
    }

    // Manejar clicks en el menú de los tres puntitos
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_modificar) {
            Log.i("MenuAction", "Modificar contacto");
            return true;
        } else if (id == R.id.menu_eliminar) {
            Log.i("MenuAction", "Eliminar contacto");
            return true;
        } else if (id == R.id.menu_compartir) {
            Log.i("MenuAction", "Compartir contacto");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Manejar clicks en el Navigation Drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_nuevo) {
            Log.i("DrawerAction", "Inicio (volver)");
            finish(); // Regresa a la actividad anterior
        } else if (id == R.id.nav_lista) {
            Log.i("DrawerAction", "Lista de contactos");

        } else if (id == R.id.nav_ajustes) {
            Log.i("DrawerAction", "Abrir ajustes");

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
