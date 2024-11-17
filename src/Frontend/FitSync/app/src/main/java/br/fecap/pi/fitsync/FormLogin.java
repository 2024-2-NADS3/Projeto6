package br.fecap.pi.fitsync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.app.AppCompatActivity;

import br.fecap.pi.fitsync.Api.ApiService;
import br.fecap.pi.fitsync.Api.RetrofitClient;
import br.fecap.pi.fitsync.models.User;
import br.fecap.pi.fitsync.models.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormLogin extends AppCompatActivity {

    private TextView text_register, text_convidado;
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

        // Listener para o botão de entrar como convidado
        text_convidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redireciona para a HomeFragment sem exigir login
                startActivity(new Intent(FormLogin.this, MainActivity.class));
                finish();
            }
        });
    }

    private void IniciarComponentes() {
        text_register = findViewById(R.id.text_register);
        bt_entrar = findViewById(R.id.bt_entrar);
        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);
        text_convidado = findViewById(R.id.text_convidado); // Inicializa o botão de convidado
    }

    private void loginUser() {
        String email = editEmail.getText().toString().trim();
        String password = editSenha.getText().toString().trim();

        User user = new User(null, email, password);

        ApiService apiService = RetrofitClient.getApiService();
        Call<UserResponse> call = apiService.loginUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FormLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FormLogin.this, MainActivity.class));
                    finish();
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
