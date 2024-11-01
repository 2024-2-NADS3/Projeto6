package com.br.Ft.fitsync.Api;

import com.br.Ft.fitsync.models.User;
import com.br.Ft.fitsync.models.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/users/register")
    Call<UserResponse> registerUser(@Body User user); // Aceita User para registro

    @POST("api/users/login")
    Call<UserResponse> loginUser(@Body User user); // Aceita User para login

    @POST("api/users/reset-password")
    Call<UserResponse> resetPassword(@Body User user); // Aceita User para redefinição de senha
}
