package com.example.imprentados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class actualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar);


        Button ButtonActu = findViewById(R.id.btn_actualizar);
        ButtonActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText TextCost = findViewById(R.id.etCosto);
                String CostInput = TextCost.getText().toString();

                EditText TextHmuch = findViewById(R.id.etCantidad);
                String HmuchInput = TextHmuch.getText().toString();

                EditText TextColor = findViewById(R.id.etColor);
                String ColorInput = TextColor.getText().toString();

                EditText TextFEn = findViewById(R.id.etFechaEntrega);
                String FEnInput = TextFEn.getText().toString();

                EditText TextFRe = findViewById(R.id.etFechaRecibo);
                String FReInput = TextFRe.getText().toString();

                EditText TextClient = findViewById(R.id.etCliente);
                String ClientInput = TextClient.getText().toString();

                EditText TextProduct = findViewById(R.id.etProducto);
                String ProductInput = TextProduct.getText().toString();

                EditText TextEmp = findViewById(R.id.etEmpleado);
                String EmInput = TextEmp.getText().toString();

                EditText TextDet = findViewById(R.id.etDetalles);
                String DetInput = TextDet.getText().toString();

                if (CostInput.isEmpty() || HmuchInput.isEmpty() || ColorInput.isEmpty() || FEnInput.isEmpty() ||
                        FReInput.isEmpty() || ClientInput.isEmpty() || ProductInput.isEmpty() || EmInput.isEmpty()) {

                    Toast.makeText(actualizar.this, "Hay campos sin llenar", Toast.LENGTH_SHORT).show();

                } else {

                    // Limpiar todos los campos

                    TextCost.setText("");
                    TextHmuch.setText("");
                    TextColor.setText("");
                    TextFEn.setText("");
                    TextFRe.setText("");
                    TextClient.setText("");
                    TextProduct.setText("");
                    TextEmp.setText("");
                    TextColor.setText("");
                    TextDet.setText("");

                    Toast.makeText(actualizar.this, "Ok", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
