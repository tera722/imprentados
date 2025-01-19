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

    // ArrayList para almacenar registros
    private ArrayList<Registro> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);

        // Inicializar ArrayList
        registros = new ArrayList<>();

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

            // Crear nuevo objeto Registro
            Registro registro = new Registro(costo, cantidad, color, fechaRecibo,
                    fechaEntrega, cliente, producto,
                    empleado, detalles);

            // Agregar al ArrayList
            registros.add(registro);

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

    // Clase Registro para almacenar datos individuales
    private static class Registro {
        private double costo;
        private int cantidad;
        private String color;
        private String fechaRecibo;
        private String fechaEntrega;
        private String cliente;
        private String producto;
        private String empleado;
        private String detalles;

        public Registro(double costo, int cantidad, String color, String fechaRecibo,
                        String fechaEntrega, String cliente, String producto,
                        String empleado, String detalles) {
            this.costo = costo;
            this.cantidad = cantidad;
            this.color = color;
            this.fechaRecibo = fechaRecibo;
            this.fechaEntrega = fechaEntrega;
            this.cliente = cliente;
            this.producto = producto;
            this.empleado = empleado;
            this.detalles = detalles;
        }

        // Métodos getter
        public double getCosto() { return costo; }
        public int getCantidad() { return cantidad; }
        public String getColor() { return color; }
        public String getFechaRecibo() { return fechaRecibo; }
        public String getFechaEntrega() { return fechaEntrega; }
        public String getCliente() { return cliente; }
        public String getProducto() { return producto; }
        public String getEmpleado() { return empleado; }
        public String getDetalles() { return detalles; }
    }
}