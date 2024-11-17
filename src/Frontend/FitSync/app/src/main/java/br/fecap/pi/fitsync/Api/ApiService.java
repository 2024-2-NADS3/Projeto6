package br.fecap.pi.fitsync.Api;

import br.fecap.pi.fitsync.models.User;
import br.fecap.pi.fitsync.models.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/users/register")
    Call<UserResponse> registerUser(@Body User user);

    @POST("api/users/login")
    Call<UserResponse> loginUser(@Body User user);

    @POST("api/users/reset-password")
    Call<UserResponse> resetPassword(@Body User user);
}
