package com.mbox.administrator.namecardexchange.feature.cards.functionHepler;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardViewMdl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mangkorn Supparphai on 9/1/2018 AD.
 */

public class ManageViewFiles {

    private final static String TAG = ManageViewFiles.class.getSimpleName();
    private final static String FOLDER_FILE_VIEW  = "viewFile";
//    private CheckViewFileListener checkViewFileListener;

//    public interface CheckViewFileListener{
//        void onReadyFile(String result);
//        void onResultViewFile(String result);
//    }
//
//    public CheckViewFileListener getCheckViewFileListener() {
//        return checkViewFileListener;
//    }
//
//    public void setCheckViewFileListener(CheckViewFileListener checkViewFileListener) {
//        this.checkViewFileListener = checkViewFileListener;
//    }

    public CardViewMdl readJsonFiles(String cardViewFile) {
        JsonReader jsonReader = null;
        CardViewMdl cardViewMdl = null;

        String resultCheckFileView = checkViewFile(cardViewFile);
        Log.d(TAG, "readJsonFiles" + resultCheckFileView);


        if (resultCheckFileView != null){
            try {
                jsonReader = new JsonReader(new FileReader(resultCheckFileView));
                Gson gson = new GsonBuilder().serializeNulls().create();
                cardViewMdl = gson.fromJson(jsonReader, CardViewMdl.class);


                Log.d(TAG, "readJsonFiles" + cardViewMdl.getCardId());

            } catch (FileNotFoundException ex) {

                Log.e(TAG, "readJsonFiles" + ex.toString());

            } catch (IOException e) {

                Log.e(TAG, "readJsonFiles" + e.toString());
                e.printStackTrace();
            } finally {

            }
        }

        return cardViewMdl;
    }

    private String checkViewFile(String cardViewFile){

        String fileName = cardViewFile.substring(cardViewFile.lastIndexOf('/') + 1);

//        String dirPath = context.getFilesDir().getAbsoluteFile() + File.separator + FOLDER_FILE_VIEW + File.separator + fileName;
        String dirPath = "/data/data/com.mbox.administrator.namecardexchange/files" + File.separator + FOLDER_FILE_VIEW + File.separator + fileName;

        Log.e(TAG, "checkViewFile = " + dirPath);

        if (!checkFile(dirPath)){

            try {

                String mResult = new DownloadViewFile(cardViewFile, FOLDER_FILE_VIEW).execute().get();

                Log.e(TAG, "checkViewFile = " + dirPath);

                if (!mResult.equals("F")){
                    return mResult;
                }else {
                    return null;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        return dirPath;
    }


    private boolean checkFile(String filePath){
        File dbFile = new File(filePath);
        if (!dbFile.exists()){
            return false;
        }

        return true;
    }

}
