package br.fecap.pi.fitsync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import br.fecap.pi.fitsync.Api.ApiService;
import br.fecap.pi.fitsync.Api.RetrofitClient;
import br.fecap.pi.fitsync.models.User;
import br.fecap.pi.fitsync.models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNome = findViewById(R.id.editar_nome);
        editEmail = findViewById(R.id.editar_email);
        editSenha = findViewById(R.id.editar_senha);

        findViewById(R.id.cadastrar_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = editNome.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editSenha.getText().toString().trim();

        User user = new User(name, email, password);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<UserResponse> call = apiService.registerUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Register.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    // Redirecionar para a tela de login
                    Intent intent = new Intent(Register.this, FormLogin.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Register.this, "Erro ao cadastrar. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Register.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
