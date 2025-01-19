package com.example.imprentados;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

                    Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}