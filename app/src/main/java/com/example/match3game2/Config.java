package com.example.match3game2;

public class Config {

    // ---------------------------------------------------------------------------------------------
    // ---------------------------- /-/ APP SETTINGS /-/ -------------------------------------------
    // ---------------------------------------------------------------------------------------------

    // Type your website url without "/" in the end:
    public static String websiteUrl = "http://localhost/candymatchphp";
    // Your Email Address:
    public static final String emailAddress = "youremail@domain.com";

    // Ads Settings: ------------------------------------------------------------------------------
    // Change the Ads Network to "admob" or "applovin"
    public static final String ADS_NETWORK = "admob";


    // Do Not Change -------------------------------------------------------------------------------
    public static final String websiteHomeUrl = websiteUrl + "/index.php";
    public static final String privacyPolicyUrl = websiteUrl + "/privacy_policy.php";
    public static final String userProfile = websiteUrl + "/api/profile.php?app=";
    public static final String userFeedback = websiteUrl + "/api/feedback.php";
    public static final String userLogin = websiteUrl + "/api/login.php";
    public static final String userWithdrawal = websiteUrl + "/api/withdrawal.php";
    public static final String payLatest = websiteUrl + "/api/latestWithdrawal.json";
    public static final String userRegister = websiteUrl + "/api/register.php";
    public static final String userPoints = websiteUrl + "/api/points.php" ;
    public static final String appSettingsUrl = websiteUrl + "/api/App-Settings.php";
    public static final String appUpdate = websiteUrl + "/api/appUpdate.php";
    public static final String userLevelUrl = websiteUrl + "/api/userLevel.php";
    public static final String userAchievementsUrl = websiteUrl + "/api/achievements.php";
}