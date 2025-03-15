package com.example.agendacontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResumenActivity extends AppCompatActivity {
    private TextView tvNombre, tvCorreo, tvTelefono, tvGenero, tvAcepto;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        // Enlazar vistas con IDs del XML
        tvNombre = findViewById(R.id.tvNombre);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvTelefono = findViewById(R.id.tvTelefono); // ✅ Nuevo campo
        tvGenero = findViewById(R.id.tvGenero);
        tvAcepto = findViewById(R.id.tvAcepto);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener datos pasados desde RegistroActivity
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String correo = intent.getStringExtra("correo");
            String telefono = intent.getStringExtra("telefono"); // ✅ Nuevo campo
            String genero = intent.getStringExtra("genero");
            boolean acepto = intent.getBooleanExtra("acepto", false);

            // Mostrar los datos en los TextView
            tvNombre.setText("Nombre: " + nombre);
            tvCorreo.setText("Correo: " + correo);
            tvTelefono.setText("Teléfono: " + telefono); // ✅ Mostrar el número
            tvGenero.setText("Género: " + genero);
            tvAcepto.setText("Aceptó los términos: " + (acepto ? "Sí" : "No"));
        }

        // Botón para regresar a la pantalla de registro
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
