package com.br.Ft.fitsync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FormLogin extends AppCompatActivity {

    private TextView text_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        IniciarComponentes();

        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void IniciarComponentes() {
        text_register = findViewById(R.id.text_register);
    }
}