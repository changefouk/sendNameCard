package com.mbox.administrator.namecardexchange.feature.cards.functionHepler;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by Mangkorn Supparphai on 12/27/2017 AD.
 */

public class FunctionHelp implements CardAttributeDefault {

    private static final String TAG = FunctionHelp.class.getSimpleName();

//    private Context context;
//    public FunctionHelp(Context context){
//        this.context = context;
//    }
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        Log.d("convertPixelsToDp ", "dp = " + dp + " - dp");
        return dp;
    }

//    public static float convertPixelsToSp(Context context, float sizeTextPx, int cardOrientation){
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        float dp = sizeTextPx / ((float)metrics.scaledDensity);
//        return dp;
//    }


//    public int convertViewSizeToPX(Context context, int sizeViewPx, int cardOrientation){
//        String vName = "_"+ sizeViewPx + "sdp";
//        String rName = "dimen";
//        int sizePX = (int) context.getResources().getDimension(getResourceId(context, vName , rName, context.getPackageName())) / 2;
//
//        return getSizeSupportSmallView(context, sizePX, cardOrientation);
//    }

//    private int convertTextSizeToPX(Context context, int sizeText, int cardOrientation){
//        String vName = "_"+ sizeText + "ssp";
//        String rName = "dimen";
//        int sizePX = (int) context.getResources().getDimension(getResourceId(context, vName , rName, context.getPackageName())) / 2;
//        return getSizeSupportSmallView(context, sizePX, cardOrientation);
//    }

//    private int getSizeSupportSmallView(Context context, int mSize, int cardOrientation){
//        double sizeSupport = 0;
//        if (cardOrientation == ORIENTATION_PORTRAIT && context.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ||
//                cardOrientation == ORIENTATION_LANDSCAPE && context.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
//            sizeSupport = (mSize / 1.5);
//
//        }else {
//            return mSize;
//        }
//
//        Log.d(TAG,"supportSize = " + sizeSupport + " " + (int) Math.round(sizeSupport));
//
//        return (int) Math.ceil(sizeSupport);
//    }

    private int getResourceId(Context context, String pVariableName, String pResourceName, String pPackageName) {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourceName, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int convertViewSizeToPX(int cardOrientation, int sizeView, Context context){
        String vName = "_"+ (sizeView / 2) + "sdp";
        String rName = "dimen";
        int sizePX = (int) context.getResources().getDimension(getResourceId(context, vName , rName, context.getPackageName()));

        return getSizeSupportSmallView(context, sizePX, cardOrientation);
    }

    private int convertTextSizeToPX(int cardOrientation, int sizeText, Context context){
        String vName = "_"+ (sizeText / 2) + "ssp";
        String rName = "dimen";
        int sizePX = (int) context.getResources().getDimension(getResourceId(context, vName , rName, context.getPackageName()));
        return getSizeSupportSmallView(context, sizePX, cardOrientation);
    }

    private int getSizeSupportSmallView(Context context, int mSize, int cardOrientation){
        double sizeSupport = 0;
        if (cardOrientation == ORIENTATION_PORTRAIT && context.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ||
                cardOrientation == ORIENTATION_LANDSCAPE && context.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            sizeSupport = (mSize / 1.5);

        }else {
            return mSize;
        }
        return (int) Math.round(sizeSupport);
    }
}
