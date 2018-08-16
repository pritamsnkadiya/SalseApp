package com.example.pritam.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.pritam.init.SalseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Methods {
    private static final String TAG = Methods.class.getSimpleName();


    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) SalseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showPromptMessage(String msg) {
        Toast.makeText(SalseApplication.getAppContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static String dateCurrentDiffernce(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String number_of_days = "0";
        try {
            Date orderDate = sdf.parse(date);
            Date currentDate = sdf.parse("04/07/2018");

            number_of_days = Long.toString(printDifference(currentDate,orderDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

//1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400

        return number_of_days;
    }
    public static long printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        Log.d("Date","startDate : " + startDate);
        Log.d("Date","endDate : "+ endDate);
        Log.d("Date","different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return elapsedDays;
    }

}
