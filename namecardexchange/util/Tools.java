package com.mbox.administrator.namecardexchange.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 30/11/2560.
 */

public class Tools {

    private static final String TAG = "insert LOG TAG";
    String FACEBOOK_PROFILE = "insert facebook profile";

    public void intentFacebook(Context mContext,String facebookpath) {
        Intent facebook = getOpenFacebookIntent(mContext, facebookpath);
        mContext.startActivity(facebook);
    }

    public static Intent getOpenFacebookIntent(Context context, String url) {
        String facebookuser = "https://www.facebook.com/" + url;
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://facewebmodal/f?href=" + url)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            Log.d(TAG, "facebook : NO application intent to web");
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(facebookuser)); //catches and opens a url to the desired page
        }
    }

    public void intentTwitter(Context mContext,String twitterUserName) {
        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterUserName)));
        } catch (Exception e) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitterUserName)));
            Log.d(TAG, "twitter : NO application intent to web");
        }
    }

    public void intentLine(Context mContext,String lineID) {
        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("line://ti/p/" + lineID)));
        } catch (Exception e) {
            Log.d(TAG, "line : NO application intent to web");
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://line.me/ti/p/@" + lineID)));
        }
    }

    public void intentYoutube(Context mContext,String youtubeChannelURL) {
        String url = "https://www.youtube.com/channel/" + youtubeChannelURL;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(url));
            mContext.startActivity(intent);
        } catch (Exception e) {
            Log.d(TAG, "line : NO application intent to web");
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    public void intentWebsite(Context mContext,String url){
        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }catch (Exception e){
            Log.d(TAG,"INTENT WEBSITE ERROR");
        }

    }

    //FCM subscribe topic
    public void subscribe() {
        // สับตะไคร้หัวข้อ news
        FirebaseMessaging.getInstance().subscribeToTopic("news");
       // txtResult.setText("sub news");
    }

    public void unsubscribe() {
        // ยกเลิกสับตะไคร้หัวข้อ news
        FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
    //    txtResult.setText("unsub");
    }

    private void writeToFile(CardMdl cardMdl) {
        // Get the directory for the user's public pictures directory.
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DCIM + "/YourFolder/"
                        );

        // Make sure the path directory exists.
        if (!path.exists()) {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "textcard.txt");

        // Save your stream, don't forget to flush() it before closing it.

        try {
            String data = new Gson().toJson(cardMdl);
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
            Log.d("FLUKE","path : "+file.getPath());
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
