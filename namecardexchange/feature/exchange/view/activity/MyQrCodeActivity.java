package com.mbox.administrator.namecardexchange.feature.exchange.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.QrCodeCardModel;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.MyQrCodeActivityViewmodel;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;
import java.util.List;

public class MyQrCodeActivity extends BaseActivity {

    private static final String TAG = MyQrCodeActivity.class.getSimpleName();
    private ImageView imageView;

    private static final String CARD_RESULT = "CARD_RESULT";

    private MyQrCodeActivityViewmodel viewmodel;

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);
        setUi();
        initInstance();
    }

    @Override
    protected void setUi() {
        imageView = findViewById(R.id.img_qrCode);
    }

    @Override
    protected void initInstance() {

        handdleQrResult();

        viewmodel = ViewModelProviders.of(this).get(MyQrCodeActivityViewmodel.class);

        viewmodel.getPreviewCard().observe(this, cardMdls -> {

            List<CardMdl> lists = new ArrayList<>();
            for (int a = 0; a < cardMdls.size(); a++) {
                if (cardMdls.get(a).getMemberID().equals(getCardmember())) {
                    lists.add(cardMdls.get(a));
                }
            }
            QrCodeCardModel qrcode = new QrCodeCardModel(lists.get(0).getCardID(), lists.get(0).getCardURLPreview());

            Bitmap myBitmap = QRCode.from(new Gson().toJson(qrcode))
                    .withSize(400, 400)
                    .bitmap();
            imageView.setImageBitmap(myBitmap);
        });
    }

    private void handdleQrResult() {
        Bundle bundle = getIntent().getExtras();
        String cardResult = bundle.getString(CARD_RESULT);

        if (cardResult != null) {
            imageView.setVisibility(View.GONE);
            Log.d(TAG,"Card Result : "+cardResult);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

}
