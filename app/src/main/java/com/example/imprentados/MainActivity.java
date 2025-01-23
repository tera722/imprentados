package com.example.imprentados;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado;
    private RequestQueue requestQueue;
    private final String URL_API = "http://10.0.2.2/loginBase.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
        requestQueue = Volley.newRequestQueue(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button ButtonAcept = findViewById(R.id.aceptar);
        ButtonAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText TextUser = findViewById(R.id.User);
                String UserInput = TextUser.getText().toString();

                EditText TextPass = findViewById(R.id.Password);
                String PassInput = TextPass.getText().toString();

                if (PassInput.isEmpty() || UserInput.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Hay campos sin llenar", Toast.LENGTH_SHORT).show();
                } else
                {
                    // Limpiar todos los campos
                    TextUser.setText("");
                    TextPass.setText("");

                    realizarLogin(UserInput, PassInput);

                    Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, menu.class);
                    startActivity(intent);
                }
            }
        });

        Button ButtonRegister = findViewById(R.id.registrar);
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, registro.class);
                    startActivity(intent);
            }
        });
    }

    private void realizarLogin(String usuario, String contraseña) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("usuario", usuario);
            jsonBody.put("contraseña", contraseña);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    URL_API,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean success = response.getBoolean("success");
                                String message = response.getString("message");
                                if (success) {
                                    tvResultado.setText("Login exitoso: " + message);
                                } else {
                                    tvResultado.setText("Error: " + message);
                                }
                            } catch (Exception e) {
                                tvResultado.setText("Error al procesar la respuesta: " + e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            tvResultado.setText("Error de conexión: " + error.getMessage());
                        }
                    }
            );

            requestQueue.add(request);
        } catch (Exception e) {
            tvResultado.setText("Error al crear la solicitud: " + e.getMessage());
        }
    }

}
