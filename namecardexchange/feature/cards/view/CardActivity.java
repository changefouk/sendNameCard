package com.mbox.administrator.namecardexchange.feature.cards.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Rect;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.models.BUSIAuthMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardViewMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardThemeMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardViewPositionMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;
import com.mbox.administrator.namecardexchange.feature.cards.viewmodel.CardActivityViewModel;
import com.mbox.administrator.namecardexchange.feature.cards.CustomView;
import com.mbox.administrator.namecardexchange.util.ZoomLayout;


import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Mangkorn Supparphai on 12/14/2017 AD.
 */

public class CardActivity extends BaseActivity implements CardAttributeDefault {

    private static final String TAG = CardActivity.class.getSimpleName();
    ZoomLayout relative_layout;
    RelativeLayout ry_card;
//    List<CustomView> customViewList;
    ImageView img_theme_front, img_busi_auth_1;
    LinearLayout ly_busi_auth;

    LinearLayout ly_menu_bar;
    ImageView img_reverse_side, img_show_menu_bar;

    boolean isCardMdl;
    boolean isCardViewMdl;

    private String idPreviewCard;

    //Class support
    private CardActivityViewModel cardActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ac_card);

        setUi();
        initInstance();
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected void setUi() {
        relative_layout = findViewById(R.id.relative_layout);
        img_reverse_side = findViewById(R.id.img_reverse_side);
        ly_menu_bar = findViewById(R.id.ly_menu_bar);
        img_show_menu_bar = findViewById(R.id.img_show_menu_bar);

        img_reverse_side.setOnTouchListener(onTouchReverseSide);
        img_show_menu_bar.setOnTouchListener(onTouchShowMenuBar);

        countDownTimer.start();
    }

    @Override
    protected void initInstance() {

        idPreviewCard = getIntent().getExtras().getString("card_id");

        cardActivityViewModel = ViewModelProviders.of(this).get(CardActivityViewModel.class);
        Log.d("FLUKE","Create Viewmodel card complete");
        cardActivityViewModel.setUpDataBegin(idPreviewCard);

        cardActivityViewModel.getCardMdl().observe(this, cardMdl -> {
//            Log.d("observe","cardMdl <M");
            Log.d("FLUKE","Observe CardData Model");
            if (cardMdl != null && (cardMdl.getCardViewFile() != null || TextUtils.isEmpty(cardMdl.getCardViewFile()))) {
                cardActivityViewModel.setCardDatamodel(cardMdl);
                cardActivityViewModel.setValueUpCardViewMdl(cardMdl);
                setOrientationView(cardMdl.getCardOrientation());
//                setThemeFront("http://192.168.0.3/card/img/theme_front.jpg");
//                setThemeFront(cardMdl.getCardThemeMdl());
                setThemeCard(cardMdl.getCardThemeMdl(), cardActivityViewModel.getCardSideInteger());
                setViewBUSIAuth(cardMdl.getBUSIAuthMdl());

                isCardMdl = true;
            }
            /**TODO: CARD UPLOAD IMAGE*/
//            new CountDownTimer(2000, 1000) {
//                public void onTick(long millisUntilFinished) {
//                    String strTime = String.format("%.1f"
//                            , (double) millisUntilFinished / 1000);
//                    Log.d(TAG, "timer " + strTime);
//                }
//
//                public void onFinish() {
//                    UploadCard(cardMdl);
//                }
//            }.start();
        });

        cardActivityViewModel.getCardViewMdl().observe(this, cardViewMdl -> {
            Log.d("FLUKE","CardCreateViewModel : "+new Gson().toJson(cardViewMdl));
                if (cardViewMdl != null) {
                    cardActivityViewModel.setCardDataViewmodel(cardViewMdl);
                    Log.d("observe", "cardViewMdl <M = ");
                      createLayoutForCard(this,
                            cardActivityViewModel.getCardDatamodel().getCardDataMdlArrayList(),
                            cardViewMdl.getCardViewPositionMdlList(),
                            cardActivityViewModel.getCardDatamodel().getCardOrientation(),
                            cardActivityViewModel.getCardSideInteger());

                    isCardViewMdl = true;
                }
        });

        cardActivityViewModel.getCardSide().observe(this, integer -> {
            Log.d("observe","cardSide observe <M = ");
            if (integer != null && !isCardMdl && !isCardViewMdl) {
                relative_layout.removeAllViews();
                setOrientationView(cardActivityViewModel.getCardDatamodel().getCardOrientation());
                setThemeCard(cardActivityViewModel.getCardDatamodel().getCardThemeMdl(), integer);
                setViewBUSIAuth(cardActivityViewModel.getCardDatamodel().getBUSIAuthMdl());

                createLayoutForCard(this,
                        cardActivityViewModel.getCardDatamodel().getCardDataMdlArrayList(),
                        cardActivityViewModel.getCardDataViewmodel().getCardViewPositionMdlList(),
                        cardActivityViewModel.getCardDatamodel().getCardOrientation(),
                        integer);
            }

            isCardMdl = false;
            isCardViewMdl = false;
        });

//        examineValueCard();
    }

    private void examineValueCard() {
        if (cardActivityViewModel.getCardMdl().getValue() == null || cardActivityViewModel.getCardViewMdl().getValue() == null){
            cardActivityViewModel.setUpDataBegin(idPreviewCard);
        }
    }

    private void createLayoutForCard(Context context, List<CardDataMdl> cardDataMdlList, List<CardViewPositionMdl> cardViewPositionMdlList, int cardOrientation, int sideCard){
        for (int i = 0; i < cardViewPositionMdlList.size(); i++) {
            if (cardViewPositionMdlList.get(i).getSideView() == sideCard && cardViewPositionMdlList.get(i).getCardDataCode() != null && !TextUtils.isEmpty(cardViewPositionMdlList.get(i).getCardDataCode())) {
                CardDataMdl cardDataMdl = findCardData(cardDataMdlList, cardViewPositionMdlList.get(i).getCardDataCode());
                if (cardDataMdl != null) {
                    setCustomView(new CustomView(context, cardDataMdl, cardViewPositionMdlList.get(i), cardOrientation));
                }
            }
        }
    }

    private CardDataMdl findCardData(List<CardDataMdl> cardDataMdlList, String cardDataCode){
        for (int i = 0; i < cardDataMdlList.size(); i++) {
            if (cardDataCode.equals(cardDataMdlList.get(i).getCardDataCode()))
                return cardDataMdlList.get(i);
        }

        return null;
    }

    private void setCustomView(CustomView customView) {
        ry_card.addView(customView);
    }

    private void setThemeFront(String URLTheme) {
        Glide.with(this).load(URLTheme).into(img_theme_front);
    }

    private void setThemeFront(CardThemeMdl cardThemeMdl) {
        Glide.with(this).load(cardThemeMdl.getFrontTheme()).into(img_theme_front);
    }

    private void setThemeCard(CardThemeMdl cardThemeMdl, int sideCard){
        if (sideCard == FRONT_SIDE){
            Glide.with(this).load(cardThemeMdl.getFrontTheme()).into(img_theme_front);
        }else {
            Glide.with(this).load(cardThemeMdl.getBackTheme()).into(img_theme_front);
        }
    }

    private void setViewBUSIAuth(BUSIAuthMdl busiAuthMdl){
        if (busiAuthMdl != null){
            ly_busi_auth.setVisibility(View.VISIBLE);
            Glide.with(this).load(busiAuthMdl.getPicPath()).into(img_busi_auth_1);

        } else {
            ly_busi_auth.setVisibility(View.GONE);
        }
    }

    private void setOrientationView(int cardOrientation) {

        if (cardOrientation == ORIENTATION_LANDSCAPE){
            getLayoutInflater().inflate(R.layout.item_card_preview_land, relative_layout, true);
        }else {
            getLayoutInflater().inflate(R.layout.item_card_preview_port, relative_layout, true);
        }

        setUiCardView();
    }

    private void setUiCardView() {
        ry_card = findViewById(R.id.ry_card);
        img_theme_front = findViewById(R.id.img_theme_front);
        ly_busi_auth = findViewById(R.id.ly_busi_auth);
        img_busi_auth_1 = findViewById(R.id.img_busi_auth_1);
    }


    View.OnTouchListener onTouchReverseSide = new View.OnTouchListener() {
        private Rect rect;
        private boolean ignore = false;
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if(ignore && motionEvent.getAction() != MotionEvent.ACTION_UP)
                return false;

            if (view == img_reverse_side) {


                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha((float) 0.2);
                        rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());

                        break;
                    case MotionEvent.ACTION_UP:
                        view.setAlpha((float) 1.0);
                        if (!ignore) {
                            countDownTimer.cancel();
                            countDownTimer.start();
                            setReverseSide(cardActivityViewModel.getCardSide().getValue());
                        }

                        ignore = false;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        view.setAlpha((float) 0.2);

                        if (!rect.contains((int) motionEvent.getX()  + view.getLeft(), (int) motionEvent.getY() + view.getTop())){
                            //out side
                            Log.d("ontouch" , "if actionmove" +motionEvent.getX() + " " +  view.getLeft() + " " + view.getRight() + "\n" +
                                    motionEvent.getY() + " " +rect.top + " " + rect.bottom );
                            ignore = true;
                            view.setAlpha((float) 1.0);
                        }

                        break;

                    case MotionEvent.ACTION_CANCEL:
                        view.setAlpha((float) 1.0);

                        break;
                }

            }
            return true;
        }
    };


    View.OnTouchListener onTouchShowMenuBar = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (view == img_show_menu_bar) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        menuBarManage();
                        break;
                }

            }
            return true;
        }
    };

    private void menuBarManage(){
        if (ly_menu_bar.getVisibility() == View.GONE){
            ly_menu_bar.setVisibility(View.VISIBLE);

            countDownTimer.start();
        }else
            countDownTimer.onFinish();
    }

    CountDownTimer countDownTimer  = new CountDownTimer(10000, 10000) {

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            ly_menu_bar.setVisibility(View.GONE);
        }
    };

    private void setReverseSide(int sideCard){
        if (sideCard == FRONT_SIDE){
            cardActivityViewModel.updateValueCardSide(BACK_SIDE);

        }else {
            cardActivityViewModel.updateValueCardSide(FRONT_SIDE);
        }
    }

}