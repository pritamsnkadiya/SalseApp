package com.example.pritam.api;

import android.content.Context;
import android.util.Log;

import com.example.pritam.model.LandingPage;
import com.example.pritam.model.OtpReceived;
import com.example.pritam.utils.Constant;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient implements Serializable {

    private static final String TAG = ApiClient.class.getSimpleName();

    private static final boolean production = false;//BuildConfig.DEBUG;


    public static final String BASE_URL = production ? "http://ipl-api-access-layer.eu-gb.mybluemix.net/api/v1/" : "http://ipl-api-access-layer.eu-gb.mybluemix.net/api/v1/";


    public static boolean isProduction() {
        return production;
    }

    private static Retrofit retrofit = null;

    private static ApiClient apiClient;

    private Context context;

    private static final Object mLock = new Object();

    public ApiClient() {
    }

    public ApiClient(Context context) {
        this.context = context;
    }

    public static ApiClient getSingletonApiClient() {
        synchronized (mLock) {
            if (apiClient == null)
                apiClient = new ApiClient();

            return apiClient;
        }
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            //OkHttpClient.Builder client = new OkHttpClient.Builder();

            OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60 * 5, TimeUnit.SECONDS)
                    .readTimeout(60 * 5, TimeUnit.SECONDS)
                    .writeTimeout(60 * 5, TimeUnit.SECONDS);

            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient.build())
                    .addConverterFactory(gsonConverterFactory)
                    .build();

        }
        return retrofit;
    }
/*
    public void login(LoginRequest request, Callback<LoginResponse> callback) {
        Call<LoginResponse> call = null;
        try {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Log.d("Login Request API Call", "Login Request calling");
            Log.d("Login Request API Call", "URL :: " + BASE_URL);
            call = apiService.loginDriver(request);
            Log.d("Login Request", "Peeru Request" + request.toString());
            call.enqueue(callback);
        } catch (Throwable e) {
            Log.e(TAG, e.toString(), e);
            Log.d("API Failure", e.getMessage());
            callback.onFailure(call, e);
        }
    }*/
    public void otpCall(String token, Callback<OtpReceived> callback) {
        Call<OtpReceived> call = null;
        try {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.d(TAG, "OTP Detail Request URL : " + Constant.DUMMY_OTP_RECEIVED);
        //Log.d(TAG, "OrderDetail Request URL : "+BASE_URL + Constant.API_ORDERS+id);
        call = apiService.getOtp (Constant.DUMMY_OTP_RECEIVED,token);
        call.enqueue(callback);
    } catch (Exception e) {
        Log.e(TAG, e.toString(), e);
        callback.onFailure(call, e);
    }
}

    public void landingPageDetailCall(String token, Callback<LandingPage> callback) {
        Call<LandingPage> call = null;
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Log.d(TAG, "Landing page Detail Request URL : " + Constant.DUMMY_LANDING_PAGE_DETAIL);
            //Log.d(TAG, "OrderDetail Request URL : "+BASE_URL + Constant.API_ORDERS+id);
            call = apiService.getLandingPage(Constant.DUMMY_LANDING_PAGE_DETAIL,token);
            call.enqueue(callback);
        } catch (Exception e) {
            Log.e(TAG, e.toString(), e);
            callback.onFailure(call, e);
        }
    }
}