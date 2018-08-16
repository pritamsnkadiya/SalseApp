package com.example.pritam.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pritam.api.ApiClient;
import com.example.pritam.model.LandingPage;
import com.example.pritam.salseapp.R;
import com.example.pritam.utils.Constant;
import com.example.pritam.utils.Methods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    String accessToken = "";
    LandingPage landingPage;
    private View rootView;
    String TAG = Home.class.getName ();
    TextView tv_bronze, silver_stores, tv_gold, total_store, tv_unverified, tv_store_added, tv_explorer_rank,
            tv_payment_earn;
    Button verify_now;
    CardView card;
    ImageView imageFlip;

    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_home, container, false);

        tv_bronze = (TextView) rootView.findViewById (R.id.tv_bronze);
        silver_stores = (TextView) rootView.findViewById (R.id.silver_stores);
        tv_gold = (TextView) rootView.findViewById (R.id.tv_gold);
        total_store = (TextView) rootView.findViewById (R.id.total_store);
        tv_unverified = (TextView) rootView.findViewById (R.id.tv_unverified);
        tv_store_added = (TextView) rootView.findViewById (R.id.tv_store_added);
        tv_explorer_rank = (TextView) rootView.findViewById (R.id.tv_explorer_rank);
        tv_payment_earn = (TextView) rootView.findViewById (R.id.tv_payment_earn);
        verify_now = (Button) rootView.findViewById (R.id.verify_now);
        card = (CardView) rootView.findViewById (R.id.card);
        imageFlip = (ImageView) rootView.findViewById (R.id.imageFlip);

        verify_now.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (getContext (), "Verify !", Toast.LENGTH_SHORT).show ();
            }
        });
        card.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat (card, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat (card, "scaleX", 0f, 1f);
                oa1.setInterpolator (new DecelerateInterpolator ());
                oa2.setInterpolator (new AccelerateDecelerateInterpolator ());
                oa1.addListener (new AnimatorListenerAdapter () {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd (animation);
                        oa1.setDuration (1000);
                        oa2.setDuration (1000);
                        oa2.start ();
                    }
                });
                oa1.start ();
            }
        });
        callApiGetLandingDetails (accessToken);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void initilizeLandingPageDetails() {
        tv_bronze.setText (landingPage.getIncentive ().getBronze () + " Stores");
        silver_stores.setText (landingPage.getIncentive ().getSilver () + " Stores");
        tv_gold.setText (landingPage.getIncentive ().getGold () + " Stores");
        total_store.setText (landingPage.getStoresCount () + " Stores to be Gold Explore !");
        tv_unverified.setText (landingPage.getUnverified () + " Unverified Entries");
        tv_store_added.setText (landingPage.getStoresAdded ());
        tv_explorer_rank.setText (landingPage.getExplorerRank ());
        tv_payment_earn.setText (landingPage.getPaytmEarned ());
    }

    private void callApiGetLandingDetails(String token) {
        try {
            ApiClient.getSingletonApiClient ().landingPageDetailCall (token, new Callback<LandingPage> () {
                @Override
                public void onResponse(Call<LandingPage> call, Response<LandingPage> response) {

                    Log.d (TAG, "landingPageDetails Data : " + response.body ());
                    landingPage = response.body ();
                    if (landingPage != null) {
                        // Store here
                        initilizeLandingPageDetails ();

                    } else {
                        Methods.showPromptMessage (Constant.ORDER_DETAIL);
                        getActivity ().finish ();
                    }
                }

                @Override
                public void onFailure(Call<LandingPage> call, Throwable t) {
                    Log.d (TAG, "Fetching Data Error : " + t.getMessage ());
                }
            });
        } catch (Exception e) {
            Log.d (TAG, "Error msg : " + e.getMessage ());
        }
    }

    private void swapFragment() {
        Home newHome = new Home ();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction ();
        fragmentTransaction.replace (R.id.frame_container, newHome);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ();
    }
}
