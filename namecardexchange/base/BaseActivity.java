package com.mbox.administrator.namecardexchange.base;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mbox.administrator.namecardexchange.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Administrator on 23/11/2560.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract String getLogTag();

    protected FirebaseUser mFirebaseUser;
    private ProgressDialog mProgressDialog;

    public final String memberID = "memberID-bbbf-4bac-be5d-9e0723002a52";
    public final String memberID2 = "7964efe0-6976-4522-8d82-1de12b36f846";
    private final String BASE_KEY_USER = "SfNrr4RG8MaEIR3FPgNglVD2Ry82";

    public static final String SHARED_VALUE = "SHARED_VALUE";
    /** value 0 = don't add
     *  value 1 = add */
    public static final String SHARED_ADDCARD = "SHARED_ADDCARD";

    /** value 0 = non find
     *  value 1 = find complete*/
    public static final String SHARED_SUGGEST = "SHARED_SUGGEST";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    abstract protected void setUi();

    abstract protected void initInstance();

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getLogTag(), "onStart: Attempting silent sign-in.");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        updateUI(mFirebaseUser);
    }

    protected void updateUI(FirebaseUser user) {
        if (user != null) {
            Log.d(getLogTag(), "UpdateUi Result : user sign in id " + user.getUid());
        } else {
            Log.d(getLogTag(), "UpdateUi Result : user not signin");
        }
    }

    protected final void toastError(@Nullable final String message) {
        if (message != null) {
            Log.e(getLogTag(), message);
            Toast.makeText(getApplicationContext(), "Error: " + message, LENGTH_LONG).show();
        }
    }

    protected final void toastMessage(@Nullable final String message) {
        if (message != null) {
            Log.e(getLogTag(), message);
            Toast.makeText(getApplicationContext(), message, LENGTH_SHORT).show();
        }
    }

    protected static void printHashKey(Context mContext) {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.d("Base Activity", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("Base Activity", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("Base Activity", "printHashKey()", e);
        }
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public FirebaseUser getmFirebaseUser() {
        return mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_slide_to_left);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.anim_slide_from_left, R.anim.anim_slide_to_right);
    }

    // To animate view slide out from bottom to top
    public void slideToTop(View view) {
        // TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
//        animate.setDuration(500);
//        animate.setFillAfter(true);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_top);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public void slideToButtom(View view) {
        // TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
//        animate.setDuration(500);
//        animate.setFillAfter(true);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_bottom);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }

    public String getCardmember() {
        if (getmFirebaseUser().getUid().equals(BASE_KEY_USER)) {
            return memberID;
        } else {
            return memberID2;
        }
    }


    /**
     * Function Intent
     */
    public void intentFacebook(String facebookpath) {
        Intent facebook = getOpenFacebookIntent(this, facebookpath);
        this.startActivity(facebook);
    }

    public static Intent getOpenFacebookIntent(Context context, String url) {
        String facebookuser = "https://www.facebook.com/" + url;
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://facewebmodal/f?href=" + url)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            Log.d("FACEBOOK", "facebook : NO application intent to web");
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(facebookuser)); //catches and opens a url to the desired page
        }
    }

    public void intentTwitter(String twitterUserName) {
        try {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterUserName)));
        } catch (Exception e) {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitterUserName)));
            Log.d(getLogTag(), "twitter : NO application intent to web");
        }
    }

    public void intentLine(String lineID) {
        try {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("line://ti/p/" + lineID)));
        } catch (Exception e) {
            Log.d(getLogTag(), "line : NO application intent to web");
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://line.me/ti/p/@" + lineID)));
        }
    }

    public void intentYoutube(String youtubeChannelURL) {
        String url = "https://www.youtube.com/channel/" + youtubeChannelURL;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(url));
            this.startActivity(intent);
        } catch (Exception e) {
            Log.d(getLogTag(), "line : NO application intent to web");
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    public void intentWebsite(String url) {
        try {
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Log.d(getLogTag(), "INTENT WEBSITE ERROR");
        }

    }

    public void intentGoogleMaps(String address) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.apps.maps");
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            intent.setData(gmmIntentUri);
            startActivity(intent);
        } catch (Exception e) {
            Log.d("Maps error", "Error : " + e.toString());
        }

    }

    public void intentSendEmail(String email) {

        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/email");
            intent.putExtra(Intent.EXTRA_EMAIL
                    , new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT
                    , "");
            intent.putExtra(Intent.EXTRA_TEXT
                    , "");
            startActivity(Intent.createChooser(intent, getString(R.string.intent_detail_email)));
        } catch (Exception e) {
            Log.d(getLogTag(), "Error send Email : " + e.toString());
        }

    }

    /**
     * navigating user to app settings (Permission Runtime)
     */
    public void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.setting_title_permission);
        builder.setMessage(R.string.setting_message);
        builder.setPositiveButton(R.string.setting_goto, (dialogInterface, which) -> {
            dialogInterface.cancel();
            openSettings();
        });
        builder.setNegativeButton(R.string.setting_cancel, (dialogInterface, which) -> {
            dialogInterface.cancel();
        });
        builder.show();

    }
}
