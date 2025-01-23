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

public class registro extends AppCompatActivity {

    private EditText TextUserReg;
    private EditText TextPassReg;
    private Button ButtonAcept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        // Inicializar vistas
        TextUserReg = findViewById(R.id.UserReg);
        TextPassReg = findViewById(R.id.PasswordReg);
        ButtonAcept = findViewById(R.id.aceptar);

        ButtonAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener usuario y contraseña
                String UserIn = TextUserReg.getText().toString().trim();
                String PassIn = TextPassReg.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (UserIn.isEmpty() || PassIn.isEmpty()) {
                    Toast.makeText(registro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ejecutar tarea asíncrona de registro
                new RegistroTask().execute(UserIn, PassIn);
            }
        });
    }

    // Tarea asíncrona para registrar usuario
    private class RegistroTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String contraseña = params[1];

            try {
                // URL de tu script PHP de registro
                URL url = new URL("http://10.0.2.2/registrar_usuario.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Crear objeto JSON con datos de usuario
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("usuario", usuario);
                jsonParam.put("contraseña", contraseña);

                // Enviar datos
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();
                os.close();

                // Leer respuesta
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
                Toast.makeText(registro.this, message, Toast.LENGTH_SHORT).show();

                // Si el registro es exitoso, puedes redirigir a otra pantalla
                if (success) {
                    Intent intent = new Intent(registro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(registro.this, "Error al procesar respuesta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}