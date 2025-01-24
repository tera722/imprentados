package com.example.imprentados;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class crear extends AppCompatActivity {

    private EditText etCosto, etCantidad, etColor, etFechaRecibo, etFechaEntrega;
    private EditText etCliente, etProducto, etEmpleado, etDetalles;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);

        // Inicializar vistas
        inicializarVistas();

        // Configurar el listener del botón
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener datos de los campos
                String costo = etCosto.getText().toString().trim();
                String cantidad = etCantidad.getText().toString().trim();
                String color = etColor.getText().toString().trim();
                String fechaRecibo = etFechaRecibo.getText().toString().trim();
                String fechaEntrega = etFechaEntrega.getText().toString().trim();
                String cliente = etCliente.getText().toString().trim();
                String producto = etProducto.getText().toString().trim();
                String empleado = etEmpleado.getText().toString().trim();
                String detalles = etDetalles.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (costo.isEmpty() || cantidad.isEmpty() || color.isEmpty() || fechaRecibo.isEmpty() ||
                        fechaEntrega.isEmpty() || cliente.isEmpty() || producto.isEmpty() || empleado.isEmpty()) {
                    Toast.makeText(crear.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ejecutar tarea asíncrona para agregar el registro
                new AgregarRegistroTask().execute(costo, cantidad, color, fechaRecibo, fechaEntrega, cliente, producto, empleado, detalles);
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

    // Tarea asíncrona para agregar el registro
    private class AgregarRegistroTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // URL de tu archivo PHP que maneja el registro
                URL url = new URL("http://10.0.2.2/agregar.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Crear objeto JSON con los datos del registro
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("costo", params[0]);
                jsonParam.put("cantidad", params[1]);
                jsonParam.put("color", params[2]);
                jsonParam.put("fechaRecibo", params[3]);
                jsonParam.put("fechaEntrega", params[4]);
                jsonParam.put("cliente", params[5]);
                jsonParam.put("producto", params[6]);
                jsonParam.put("empleado", params[7]);
                jsonParam.put("detalles", params[8]);

                // Enviar los datos
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();
                os.close();

                // Obtener la respuesta del servidor
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                } else {
                    return "Error en la conexión";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                boolean success = jsonResponse.getBoolean("success");
                String message = jsonResponse.getString("message");

                // Mostrar mensaje de respuesta
                Toast.makeText(crear.this, message, Toast.LENGTH_SHORT).show();

                // Si el registro fue exitoso, redirigir a otra actividad
                if (success) {
                    Intent intent = new Intent(crear.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(crear.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
