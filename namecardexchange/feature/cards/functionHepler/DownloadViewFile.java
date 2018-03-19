package com.mbox.administrator.namecardexchange.feature.cards.functionHepler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// usually, subclasses of AsyncTask are declared inside the activity class.
// that way, you can easily modify the UI thread from here
public class DownloadViewFile extends AsyncTask<String, Integer, String>{

    private final static String TAG = "DownloadTask";
    private final static String DOWNLOAD_FAIL = "F";
    private final static String DOWNLOAD_SUCCESS = "S";
    private String UriDownload, saveFolderName;

    public DownloadViewFile(String UriDownload, String saveFolderName) {
        this.UriDownload = UriDownload;
        this.saveFolderName = saveFolderName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... sUrl) {
        Log.d("download ",  "url = "+ UriDownload);
        String fileName = UriDownload.substring(UriDownload.lastIndexOf('/') + 1);

        String dirFolder = "/data/data/com.mbox.administrator.namecardexchange/files" + File.separator + saveFolderName;
        File dbFolder = new File(dirFolder);

        Log.d("downloadProcess ",  "url = "+ dirFolder);

        if (!dbFolder.exists())
            dbFolder.mkdirs();

        String dirFile = dirFolder + File.separator + fileName;
        File dbFile = new File(dirFile);



        Log.d("downloadProcess ",  "url = "+ dirFile);

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        int FileSize;
        try {
            URL url = new URL(UriDownload);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return DOWNLOAD_FAIL;
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();
            FileSize = fileLength;

            // check file exit and check size
            if (dbFile.exists()) {
                int check_file = Integer.parseInt(String.valueOf(dbFile.length()));
                if (check_file == FileSize) {
                    publishProgress(100);
                    return dirFile;
                } else {
                    dbFile.delete();
                }
            }


            Log.d("downloadProcess ",  "will download");

            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(dbFile);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    output.flush();
                    output.close();
//                    return null;
                    return "F";
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

        } catch (Exception e) {

            Log.e("downloadProcess",  "catch error " + e.toString());
            return DOWNLOAD_FAIL;
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }

        int file_size = Integer.parseInt(String.valueOf(dbFile.length()));
        if (dbFile.exists()){
            if (file_size == FileSize){
                return dirFile;
            }
        }

        return DOWNLOAD_FAIL;
    }

}
