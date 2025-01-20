package com.example.imprentados;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class crear extends AppCompatActivity {
    // Elementos de la interfaz
    private EditText etCosto, etCantidad, etColor, etFechaRecibo, etFechaEntrega;
    private EditText etCliente, etProducto, etEmpleado, etDetalles;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);

        // Inicializar elementos de la interfaz
        inicializarVistas();

        // Configurar el listener del botón
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarRegistro();
            }
        });
    }

    private void inicializarVistas() {
        etCosto = findViewById(R.id.etCosto);
        etCantidad = findViewById(R.id.etCantidad);
        etColor = findViewById(R.id.etColor);
        etFechaRecibo = findViewById(R.id.etFechaRecibo);
        etFechaEntrega = findViewById(R.id.etFechaEntrega);
        etCliente = findViewById(R.id.etCliente);
        etProducto = findViewById(R.id.etProducto);
        etEmpleado = findViewById(R.id.etEmpleado);
        etDetalles = findViewById(R.id.etDetalles);
        btnAgregar = findViewById(R.id.btnAgregar);
    }

    private void agregarRegistro() {
        try {
            // Obtener valores de los campos
            double costo = Double.parseDouble(etCosto.getText().toString());
            int cantidad = Integer.parseInt(etCantidad.getText().toString());
            String color = etColor.getText().toString();
            String fechaRecibo = etFechaRecibo.getText().toString();
            String fechaEntrega = etFechaEntrega.getText().toString();
            String cliente = etCliente.getText().toString();
            String producto = etProducto.getText().toString();
            String empleado = etEmpleado.getText().toString();
            String detalles = etDetalles.getText().toString();

            // Limpiar campos
            limpiarCampos();

            // Mostrar mensaje de éxito
            Toast.makeText(this, "Registro agregado exitosamente", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        etCosto.setText("");
        etCantidad.setText("");
        etColor.setText("");
        etFechaRecibo.setText("");
        etFechaEntrega.setText("");
        etCliente.setText("");
        etProducto.setText("");
        etEmpleado.setText("");
        etDetalles.setText("");
    }
}