package com.example.evaluation;




import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {


    @GET("v1/1/categories.php")
    Call<Response> getCategories() ;


}
