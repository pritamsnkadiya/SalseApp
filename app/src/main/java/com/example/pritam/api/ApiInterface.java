package com.example.pritam.api;

import com.example.pritam.model.LandingPage;
import com.example.pritam.model.OtpReceived;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface ApiInterface {

    public static final int DEFAULT_PAGE_SIZE = 10;
/*

    @Headers({"Accept: application/json"})
    @POST("/driver/login")
    Call<LoginResponse> loginDriver(@Body LoginRequest request);

    @Headers({"Accept: application/json"})
    @POST("/driver/signup")
    Call<CommonResponse> driverSignUp(@Body SignupRequest request);

    @Headers({"Accept: application/json"})
    @GET("/driver/{driverId}/profile")
    Call<ProfileResponse> getProfile(@Path("driverId") Integer driverId, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @GET("/driver/{driverId}/trips")
    Call<TripListResponse> getTripList(@Path("driverId") Integer driverId, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @GET("/driver/{driverId}/trips/{tripId}")
    Call<ProfileResponse> getTripInfo(@Path("driverId") Integer driverId,@Path("tripId") Integer tripId, @Header("Authorization") String tokenId);


    @Headers({"Accept: application/json"})
    @POST("/driver/task")
    Call<TripListHomeResponse> getTaskList(@Body CommonRequest request, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @POST("/driver/taskAction")
    Call<TaskActionResponse> getTaskAction(@Body TaskActionRequest request, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @POST("/driver/tripStart")
    Call<CommonResponse> startTrip(@Body TripStartRequest request, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @POST("/driver/tripEnd")
    Call<CommonResponse> endTrip(@Body TripEndRequest request, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @POST("/driver/endTask")
    Call<CommonResponse> completeTrip(@Body EndTaskRequest request, @Header("Authorization") String tokenId);
*/
    @Headers({"Accept: application/json"})
    @GET
    Call<OtpReceived> getOtp(@Url String url, @Header("Authorization") String tokenId);

    @Headers({"Accept: application/json"})
    @GET
    Call<LandingPage> getLandingPage(@Url String url, @Header("Authorization") String tokenId);
}
