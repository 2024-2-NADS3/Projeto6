package br.fecap.pi.fitsync;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Certifique-se de que este é o layout correto

        // Usar Handler para atrasar a transição para a próxima atividade
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia a FormLogin após 3 segundos
                Intent intent = new Intent(SplashActivity.this, FormLogin.class);
                startActivity(intent);
                finish(); // Finaliza a SplashActivity para que não possa voltar
            }
        }, 3000); // 3000 milissegundos = 3 segundos
    }
}
