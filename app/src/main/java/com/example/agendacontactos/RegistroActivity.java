package com.example.agendacontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {
    private EditText etNombre, etCorreo, etTelefono;
    private RadioGroup rgGenero;
    private CheckBox cbAcepto;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Enlazar vistas con IDs del XML
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        rgGenero = findViewById(R.id.rgGenero);
        cbAcepto = findViewById(R.id.cbAcepto);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Enviar datos al hacer clic en el botón
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
    }

    private void validarDatos() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        boolean acepto = cbAcepto.isChecked();

        // Validar que el nombre tenga al menos 3 caracteres
        if (nombre.length() < 3) {
            Toast.makeText(this, getString(R.string.error_nombre), Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar correo con un patrón estándar
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, getString(R.string.error_correo), Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que el teléfono tenga exactamente 10 dígitos
        if (telefono.length() != 10 || !telefono.matches("[0-9]+")) {
            Toast.makeText(this, getString(R.string.error_telefono), Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar que el usuario haya seleccionado un género
        int selectedId = rgGenero.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, getString(R.string.error_genero), Toast.LENGTH_SHORT).show();
            return;
        }
        String genero = ((RadioButton) findViewById(selectedId)).getText().toString();

        // Validar que haya aceptado los términos
        if (!acepto) {
            Toast.makeText(this, getString(R.string.error_terminos), Toast.LENGTH_SHORT).show();
            return;
        }

        // Enviar los datos a la pantalla de resumen
        Intent intent = new Intent(RegistroActivity.this, ResumenActivity.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("correo", correo);
        intent.putExtra("telefono", telefono);
        intent.putExtra("genero", genero);
        intent.putExtra("acepto", acepto);
        startActivity(intent);
    }
}
