package com.example.match3game2.util;

import static com.example.match3game2.Config.emailAddress;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.match3game2.R;

public class Tools {

    public static void goToActivity(Context context, Class activity)
    {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void setAnimationBtn(Context context, Button button, int type)
    {
        Animation myAnim;

        switch (type)
        {
            case 1:
                myAnim = AnimationUtils.loadAnimation(context, R.anim.button_pulse);
                break;
            case 2:
                myAnim = AnimationUtils.loadAnimation(context, R.anim.floating_up);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        button.startAnimation(myAnim);
    }

    public static String getVersionName(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return ctx.getString(R.string.app_version) + " " + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return ctx.getString(R.string.version_unknown);
        }
    }

    public static void rateAction(Activity activity) {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }

    public static void shareAction(Activity activity)
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, activity.getString(R.string.Share_Text));
        activity.startActivity(Intent.createChooser(sharingIntent, "share"));
    }

    public static void contactAction(Activity activity)
    {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        email.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.app_name));
        email.putExtra(Intent.EXTRA_TEXT, "Text");
        email.setType("message/rfc822");
        activity.startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    public static void copyToClipboard(Context context, String data) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clipboard", data);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, context.getString(R.string.copied), Toast.LENGTH_SHORT).show();
    }
}
