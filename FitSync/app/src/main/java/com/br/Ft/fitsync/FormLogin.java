package com.br.Ft.fitsync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.app.AppCompatActivity;

import com.br.Ft.fitsync.Api.ApiService;
import com.br.Ft.fitsync.Api.RetrofitClient;
import com.br.Ft.fitsync.models.User;
import com.br.Ft.fitsync.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormLogin extends AppCompatActivity {

    private TextView text_register;
    private AppCompatButton bt_entrar;
    private EditText editEmail, editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        IniciarComponentes();

        // Listener para o botão de registro
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this, Register.class);
                startActivity(intent);
            }
        });

        // Listener para o botão de entrar
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void IniciarComponentes() {
        text_register = findViewById(R.id.text_register);
        bt_entrar = findViewById(R.id.bt_entrar);
        editEmail = findViewById(R.id.edit_email); // Adicionei a referência ao EditText de email
        editSenha = findViewById(R.id.edit_senha); // Adicionei a referência ao EditText de senha
    }

    private void loginUser() {
        String email = editEmail.getText().toString().trim();
        String password = editSenha.getText().toString().trim();

        User user = new User(null, email, password); // O nome não é necessário para o login

        ApiService apiService = RetrofitClient.getApiService();
        Call<UserResponse> call = apiService.loginUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FormLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Redirecionar para a tela principal ou onde você quiser
                    startActivity(new Intent(FormLogin.this, MainActivity.class));
                    finish(); // Finaliza a atividade de login
                } else {
                    Toast.makeText(FormLogin.this, "Erro ao fazer login. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(FormLogin.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
