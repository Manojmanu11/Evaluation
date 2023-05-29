package com.example.evaluation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi2 {

    @GET("v1/1/filter.php?i")
    Call<Response2> getMeals() ;
}
