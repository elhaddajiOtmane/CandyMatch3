package com.example.match3game2.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.example.match3game2.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.concurrent.TimeUnit;

public class AdsManager implements MaxAdListener, MaxRewardedAdListener {
    static InterstitialAd mInterstitialAd;
    RewardedAd mRewardedAd;
    MaxRewardedAd rewardedAd;
    static MaxInterstitialAd maxInterstitialAd;
    int retryAttempt;
    Activity activity;

    public AdsManager(Activity activity)
    {
        this.activity = activity;
    }

    /**
     * Show Banner Ads from Admob
     */
    public void showBannerAdmobAds()
    {
        MobileAds.initialize(activity, initializationStatus -> {
        });
        AdView mAdView = activity.findViewById(R.id.adView);
        @SuppressLint("VisibleForTests")
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    /**
     * Load Interstitial Ads for Admob
     */
    public void setInterAds()
    {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(activity, activity.getString(R.string.admob_interstitial_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("Admob", "onAdLoaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("Admob", "The ad was dismissed.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("Admob", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                                Log.d("Admob", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("Admob", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }


    /**
     * Show Interstitial Ads for Admob
     */
    public void showInterstitial() {

        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            setInterAds();
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    /**
     * Load Rewarded Ads for Admob
     */
    public void setRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(activity, activity.getString(R.string.admob_reward_id),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("Admob", loadAdError.getMessage());
                        mRewardedAd = null;
                    }
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d("Admob", "Ad was loaded.");

                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d("Admob", "Ad was shown.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                // Called when ad fails to show.
                                Log.d("Admob", "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d("Admob", "Ad was dismissed.");
                                mRewardedAd = null;
                            }
                        });
                    }
                });
    }


    /**
     * Show Rewarded Ads for Admob
     */
    public void showRewardsAs()
    {
        if (mRewardedAd != null) {
            mRewardedAd.show(activity, rewardItem -> {
                // Handle the reward.
                Log.d("Admob", "The user earned the reward.");
                setRewardedAd();
            });
        } else {
            Log.d("Admob", "The rewarded ad wasn't ready yet.");
            setRewardedAd();
        }
    }



    // Applovin Ads - MaxAds -----------------------------------------------------
    /**
     * Initialize Applovin Ads
     */
    public void initApplovinAds()
    {
        AppLovinSdk.getInstance( activity ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( activity, configuration -> {
            // AppLovin SDK is initialized, start loading ads
            showMaxBannerAd();
        });
    }

    /**
     * Show Applovin Banner Ads
     */
    public void showMaxBannerAd()
    {
        MaxAdView maxBannerAdView = activity.findViewById(R.id.MaxAdView);
        maxBannerAdView.loadAd();
    }

    /**
     * Load Applovin Interstitial Ads
     */
    public void loadMaxInterstitialAd(){
        maxInterstitialAd = new MaxInterstitialAd( activity.getString(R.string.appLovin_interstitial_id), activity );
        maxInterstitialAd.setListener( this );

        maxInterstitialAd.loadAd();
    }

    /**
     * Show Applovin Interstitial Ads
     */
    public void showMaxInterstitialAd()
    {
        if ( maxInterstitialAd.isReady() )
        {
            maxInterstitialAd.showAd();
        }else{
            Log.d("Applovin", "The interstitial ad wasn't ready yet.");
            loadMaxInterstitialAd();
        }
    }

    /**
     * Ad Listener for Applovin Interstitial Ads
     */
    @Override
    public void onAdLoaded(@NonNull final MaxAd maxAd)
    {
        retryAttempt = 0;
    }

    @Override
    public void onAdLoadFailed(@NonNull final String adUnitId, @NonNull final MaxError error)
    {
        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

        new Handler().postDelayed(() -> {
            maxInterstitialAd.loadAd();
        }, delayMillis );
    }

    @Override
    public void onAdDisplayFailed(@NonNull final MaxAd maxAd, @NonNull final MaxError error)
    {
        // Toast.makeText(getApplicationContext(), "onAdLoadFailed: " + error.getMessage(), Toast.LENGTH_LONG).show();
        // ad failed to display. We recommend loading the next ad
        maxInterstitialAd.loadAd();
        rewardedAd.loadAd();
    }

    @Override
    public void onAdDisplayed(@NonNull final MaxAd maxAd) {
    }

    @Override
    public void onAdClicked(@NonNull final MaxAd maxAd) {}

    @Override
    public void onAdHidden(@NonNull final MaxAd maxAd)
    {
        // ad is hidden. Pre-load the next ad
        maxInterstitialAd.loadAd();
        rewardedAd.loadAd();
    }


    /**
     * Load Rewards Ads for Applovin
     */
    public void loadMaxRewardsAd()
    {
        rewardedAd = MaxRewardedAd.getInstance( activity.getString(R.string.appLovin_rewards_id), activity );
        rewardedAd.setListener( this );

        rewardedAd.loadAd();
    }

    /**
     * Rewards Ad Listener for Applovin
     */
    @Override
    public void onRewardedVideoStarted(@NonNull final MaxAd maxAd) {}

    @Override
    public void onRewardedVideoCompleted(@NonNull final MaxAd maxAd) {

    }

    @Override
    public void onUserRewarded(@NonNull final MaxAd maxAd, @NonNull final MaxReward maxReward)
    {
        // Rewarded ad was displayed and user should receive the reward
    }

    public void showMaxVideoAds()
    {
        if ( rewardedAd.isReady() )
        {
            rewardedAd.showAd();

        }else{
            Log.d("Applovin", "The rewarded ad wasn't ready yet.");
        }
    }
}
