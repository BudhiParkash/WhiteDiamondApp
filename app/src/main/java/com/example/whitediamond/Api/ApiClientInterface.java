package com.example.whitediamond.Api;

import com.example.whitediamond.model.BookingPojo;
import com.example.whitediamond.model.ChangePasswordPojo;
import com.example.whitediamond.model.GamePojo;
import com.example.whitediamond.model.LoginPojo;
import com.example.whitediamond.model.ResultPojo;
import com.example.whitediamond.model.UserPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ApiClientInterface {


    //192.168.29.43
    //192.168.1.5
    //192.168.36.72
    private static final String url = "http://192.168.1.5:3001/";

    public static WhiteDiamondApiService WDApiService = null;

    public  static WhiteDiamondApiService getWDApiService() {
        if (WDApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WDApiService = retrofit.create(WhiteDiamondApiService.class);
        }
        return WDApiService;
    }

    public  interface WhiteDiamondApiService {

        @POST("/v1/users")
        Call<LoginPojo> login(@Body UserPojo log);

        @GET("/v1/game")
        Call<List<GamePojo>>  getGames(@Header("Authorization")  String header);

        @POST("/v1/booking")
        Call<Void>  postbooking(@Header("Authorization")  String header , @Body BookingPojo bookingPojo);

        @GET("/v1/winner")
        Call<List<ResultPojo>>  getResult(@Header("Authorization")  String header , @Query("gname") String gameName);


        @POST("/v1/changepass")
        Call<Void>  changePass(@Header("Authorization")  String header ,  @Body ChangePasswordPojo changePasswordPojo);

        @GET("/v1/me")
        Call<UserPojo> getUser(@Header("Authorization")  String header);

        @GET("/v1/mybooking")
        Call<List<BookingPojo>>   getBooking(@Header("Authorization")  String header , @Query("uid") String userId);

    }
}
